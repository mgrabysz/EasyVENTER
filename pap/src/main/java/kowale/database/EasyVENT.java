package kowale.database;

import kowale.userInterface.*;
import kowale.user.*;
import kowale.event.Event;
import kowale.ticket.Ticket;

import java.lang.Thread;

import javax.swing.JOptionPane;

import java.util.HashMap;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import java.util.regex.Pattern;

public class EasyVENT {
    private static Database database;

    private WelcomeFrame welcomeFrame;
    private RegisterFrame registerFrame;
    private RegisterClientFrame registerClientFrame;
    private RegisterOrganizerFrame registerOrganizerFrame;
    private LoginFrame loginFrame;
    private MainMenuFrame mainMenuFrame;
    private CreateEventFrame createEventFrame;
    private ManageEventsFrame manageEventsFrame;
    private ViewEventsFrame viewEventsFrame;
    private InputSectorDataFrame inputSectorDataFrame;
    private EventDetailsFrame eventDetailsFrame;
    private EventDetailsAfterBuyingFrame eventDetailsAfterBuyingFrame;
    private ModifyEventFrame modifyEventFrame;

    private String nextFrame = "welcome";

    public EasyVENT() throws Exception {
        database = new Database();
        mainLoop();
    }

    /**
     * wait to save CPU time
     */
    private void waiting() throws Exception {
        try {
            Thread.sleep(100);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * check if string is convertable to Double
     */
    public static boolean isNumeric(String string) {
        if (string == null) {
            return false;
        }
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * hash given string using SHA256
     */
    public static String hash(String string) throws Exception {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] hash = sha256.digest(string.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * convert events list to data for GUI table
     */
    private String[][] eventsToData(ArrayList<Event> events) {
        String[][] data = new String[events.size()][4];

        if (events.size() > 0) {
            for (int i = 0; i < events.size(); i++) {
                Event event = events.get(i);
                data[i] = event.getInfo();
            }
        }
        return data;
    }

    /**
     * convert tickets map to data for GUI table
     */
    private String[][] ticketsMapToData(HashMap<String, HashMap<String, String>> ticketsMap) {

        String[][]data = new String[ticketsMap.size()][3];

        if (ticketsMap.size() > 0) {
            int i = 0;
            for (String sector : ticketsMap.keySet()) {
                HashMap<String, String> numberPrice = ticketsMap.get(sector);
                data[i][0] = sector;
                data[i][1] = numberPrice.get("number");
                String price = numberPrice.get("price");
                if (price.substring(price.length()-1).equals("0")) {
                    price += "0";
                }
                data[i][2] = price;
                i++;
            }
        }

        return data;
    }


    /**
     * Opens WelcomeFrame
     */
    private void welcome() throws Exception {
        welcomeFrame = new WelcomeFrame();

        while (welcomeFrame.getOption().equals("")) {
            waiting();
        }

        switch (welcomeFrame.getOption()) {
            case "register":
                nextFrame = "register";
                break;
            case "login":
                nextFrame = "login";
                break;
        }

        welcomeFrame.dispose();
        welcomeFrame = null;
    }

    /**
     * Opens RegiserFrame
     */
    private void register() throws Exception {
        registerFrame = new RegisterFrame();
        boolean isUserCreated = false;

        while (isUserCreated == false) {
            while (registerFrame.getIsReady() == false) {
                waiting();
            }

            // check if user input is correct (all are filled and not filled with spaces only)
            if (
                registerFrame.getUserName().trim().length() > 0 &&
                registerFrame.getUserSurname().trim().length() > 0 &&
                registerFrame.getUserLogin().trim().length() > 0 &&
                registerFrame.getUserPassword().trim().length() > 0
            ) {
                int accountType = registerFrame.getAccountType();
                registerFrame.dispose();

                // call appropraite (depending on account type) function to get additional user info
                if (accountType == 0){
                    HashMap<String, String> additionalInfo = registerClient();

                    LocalDate date = LocalDate.parse(
                        additionalInfo.get("date")
                    );

                    Client new_client = new Client(
                        registerFrame.getUserName(),
                        registerFrame.getUserSurname(),
                        registerFrame.getUserLogin(),
                        hash(registerFrame.getUserPassword()),
                        additionalInfo.get("email"),
                        Integer.parseInt(additionalInfo.get("telephone")),
                        additionalInfo.get("gender"),
                        date
                    );

                    EasyVENT.database.registerUser(new_client);
                } else {
                    HashMap<String, String> additionalInfo = registerOrganizer();

                    EventOrganizer new_organizer = new EventOrganizer(
                        registerFrame.getUserName(),
                        registerFrame.getUserSurname(),
                        registerFrame.getUserLogin(),
                        hash(registerFrame.getUserPassword()),
                        additionalInfo.get("email"),
                        Integer.parseInt(additionalInfo.get("telephone")),
                        additionalInfo.get("company")
                    );

                    EasyVENT.database.registerUser(new_organizer);
                }

                isUserCreated = true;

                registerFrame = null;
                nextFrame = "welcome";
            } else {
                // wrong input
                registerFrame.setIsReady(false);

                JOptionPane.showMessageDialog(
                    null,
                    "Invalid value in one or more fields.",
                    "Invalid user input",
                    JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                );
            }
        }
    }

    /**
     * Opens registerClientFrame
     */
    private HashMap<String, String> registerClient() throws Exception {
        registerClientFrame = new RegisterClientFrame();
        HashMap<String, String> map = new HashMap<String, String>();
        boolean isDataCorrect = false;

        while (isDataCorrect == false) {
            while(registerClientFrame.getIsReady() == false) {
                waiting();
            }

            String telephone = registerClientFrame.getTelephone();
            if (
                registerClientFrame.getEmail().trim().length() > 0 &&
                registerClientFrame.getTelephone().trim().length() > 0 &&
                telephone.length() == 9 &&
                isNumeric(telephone)
            ) {

                isDataCorrect = true;

                map.put("email", registerClientFrame.getEmail());
                map.put("telephone", registerClientFrame.getTelephone());
                String gender = registerClientFrame.getGender().substring(0, 1);
                map.put("gender", gender);
                map.put(
                    "date",
                    registerClientFrame.getDate().format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    )
                );

                registerClientFrame.dispose();
                registerClientFrame = null;
            } else {
                registerClientFrame.setIsReady(false);

                JOptionPane.showMessageDialog(
                    null,
                    "Invalid value in one or more fields.",
                    "Invalid user input",
                    JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                );
            }
        }
        return map;
    }

    /**
     * Opens RegisterOrganizerFrame
     */
    private HashMap<String, String> registerOrganizer() throws Exception {
        registerOrganizerFrame = new RegisterOrganizerFrame();
        HashMap<String, String> map = new HashMap<String, String>();
        boolean isDataCorrect = false;

        while(isDataCorrect == false) {
            while(registerOrganizerFrame.getIsReady() == false) {
                waiting();
            }
            // check if input data is correct
            if (
                registerOrganizerFrame.getEmail().trim().length() > 0 &&
                registerOrganizerFrame.getCompany().trim().length() > 0 &&
                registerOrganizerFrame.getTelephone().length() == 9 &&
                isNumeric(registerOrganizerFrame.getTelephone())
            ) {
                isDataCorrect = true;

                map.put("email", registerOrganizerFrame.getEmail());
                map.put("telephone", registerOrganizerFrame.getTelephone());
                map.put("company", registerOrganizerFrame.getCompany());

                registerOrganizerFrame.dispose();
                registerOrganizerFrame = null;
            } else {
                registerOrganizerFrame.setIsReady(false);

                JOptionPane.showMessageDialog(
                    null,
                    "Invalid value in one or more fields.",
                    "Invalid user input",
                    JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                );
            }
        }
        return map;
    }


    /**
     * Opens LoginFrame
     */
    private void login() throws Exception {
        loginFrame = new LoginFrame();
        boolean isLoginSuccessful = false;

        while (isLoginSuccessful == false) {
            while (loginFrame.getIsReady() == false) {
                waiting();
            }

            // try to log in with provided credentials
            if (
                EasyVENT.database.logIntoDatabase(
                    loginFrame.getUserLogin(),
                    hash(loginFrame.getUserPassword())
                )
            ) {
                isLoginSuccessful = true;

                nextFrame = "mainMenu";
                loginFrame.dispose();
                loginFrame = null;
            } else {
                loginFrame.setIsReady(false);

                JOptionPane.showMessageDialog(
                    null,
                    "Login or password incorrect.",
                    "Invalid user input",
                    JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                );
            }
        }
    }

    /**
     * Opens MainMenuFrame
     */
    private void mainMenu() throws Exception{
        mainMenuFrame = new MainMenuFrame(GlobalVariables.USER_TYPE);

        while (mainMenuFrame.getOption().equals("")) {
            waiting();
        }

        switch (mainMenuFrame.getOption()) {
            case "logout":
                GlobalVariables.USER_LOGIN = null;
                GlobalVariables.USER_TYPE = null;
                nextFrame = "welcome";
                break;
            case "viewEvents":
                nextFrame = "viewEvents";
                break;
            case "manageEvents":
                nextFrame = "manageEvents";
                break;
            case "createEvent":
                nextFrame = "createEvent";
                break;
            case "manageTickets":
                nextFrame = "viewEventsBought";
                break;
        }

        mainMenuFrame.dispose();
        mainMenuFrame = null;
    }

    /**
     * Displays viewEventsFrame
     */
    private void viewEvents() throws Exception{
        ArrayList<Event> availablEvents = database.getEventsButNotOfUser(GlobalVariables.USER_LOGIN);

        String[][] data = eventsToData(availablEvents);
        viewEventsFrame = new ViewEventsFrame(data);

        while (viewEventsFrame.getOption().equals("")) {
            waiting();
        }

        switch (viewEventsFrame.getOption()) {
            case "cancel":
                nextFrame = "mainMenu";
                break;
            case "details":
                GlobalVariables.SELECTED_INDEX = viewEventsFrame.getSelectedIndex();
                nextFrame = "eventDetails";
                break;
        }

        viewEventsFrame.dispose();
        viewEventsFrame = null;
    }

    /**
     * Opens ViewEventsFrame with only this events that currently logged client has at least one ticket for.
     * User can select event and open EventDetailsAfterBuyingFrame.
     */
    private void viewEventsBought() throws Exception {
        ArrayList<Event> userEvents = database.getEventsOfUser(GlobalVariables.USER_LOGIN);

        String[][] data = eventsToData(userEvents);

        viewEventsFrame = new ViewEventsFrame(data);

        while (viewEventsFrame.getOption().equals("")) {
            waiting();
        }

        switch (viewEventsFrame.getOption()) {
            case "cancel":
                nextFrame = "mainMenu";
                break;
            case "details":
                if (userEvents.size()==0){
                    JOptionPane.showMessageDialog(
                        null,
                        "You don't have any tickets to check",
                        "No events in your list",
                        JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                    );
                    nextFrame = "mainMenu";
                    break;
                } else {
                    GlobalVariables.SELECTED_INDEX = viewEventsFrame.getSelectedIndex();
                    nextFrame = "eventDetailsBought";
                    break;
                }
        }

        viewEventsFrame.dispose();
        viewEventsFrame = null;
    }


    /**
     * Opens ManageEventsFrame with events created by currently logged in organizer.
     */
    private void manageEvents() throws Exception{
        ArrayList<Event> organizerEvents = database.getEventsOfOrganizer(GlobalVariables.USER_LOGIN);

        String[][] data = eventsToData(organizerEvents);
        manageEventsFrame = new ManageEventsFrame(data);

        while (manageEventsFrame.getOption().equals("")) {
            waiting();
        }

        switch (manageEventsFrame.getOption()) {
            case "cancel":
                nextFrame = "mainMenu";
                break;
            case "modify":
                if (organizerEvents.size()==0){
                    JOptionPane.showMessageDialog(
                            null,
                            "No data to modify.",
                            "No data to modify",
                            JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                        );
                    nextFrame = "mainMenu";
                    break;
                } else {
                    GlobalVariables.SELECTED_EVENT = organizerEvents.get(manageEventsFrame.getSelectedIndex());
                    nextFrame = "modifyEvent";
                    break;
                }
        }

        manageEventsFrame.dispose();
        manageEventsFrame = null;
    }

    /**
     * Opens CreateEventFrame.
     */
    private void createEvent() throws Exception{
        createEventFrame = new CreateEventFrame();
        boolean isDataCorrect = false;
        boolean isNameUnique = true;

        while (isDataCorrect == false) {
            while (createEventFrame.getOption().equals("")) {
                waiting();
            }

            switch (createEventFrame.getOption()) {
                case "cancel":
                    createEventFrame.dispose();
                    createEventFrame = null;

                    nextFrame = "mainMenu";
                    return;
                case "confirm":
                    isNameUnique = true;
                    String company = database.getCompanyName(GlobalVariables.USER_LOGIN);
                    for (Event event : database.getAllEvents()){
                        if(createEventFrame.getName().equals(event.getName())){
                            isNameUnique = false;
                            break;
                        }
                    }
                    if (createEventFrame.getDateTime().compareTo(LocalDateTime.now()) > 0
                        && !createEventFrame.getName().trim().equals("")
                        && !createEventFrame.getAddress().trim().equals("")
                        && !createEventFrame.getCity().trim().equals("")
                        && !createEventFrame.getCountry().trim().equals("")
                        && isNameUnique){
                        isDataCorrect = true;

                        Event event = new Event(
                            createEventFrame.getName(),
                            company,
                            createEventFrame.getCountry(),
                            createEventFrame.getCity(),
                            createEventFrame.getAddress(),
                            createEventFrame.getDateTime()
                        );

                        int sectorsNumber = createEventFrame.getNumOfSectors();
                        createEventFrame.dispose();
                        createEventFrame = null;

                        ArrayList<Ticket> tickets = inputSectorData(sectorsNumber);

                        if (tickets != null) {
                            event.setTickets(tickets);
                            database.insertEvent(event, GlobalVariables.USER_LOGIN);
                        }

                        nextFrame = "mainMenu";
                    } else {
                        createEventFrame.displayMessageDialog();
                        createEventFrame.setOption("");
                    }

                    break;
            }
        }
    }

    /**
     * Open InputSectorDataFrame to let user (organizer) define tickets
     * after user confirmation program goes back to createEvent to create event
     */
    private ArrayList<Ticket> inputSectorData(int sectorsNumber) throws Exception{
        String[][] sectors = new String[sectorsNumber][3];
        for (int i=0; i<sectorsNumber; ++i) {
            String iStr = String.valueOf(i+1);
            String[] sector = {
                iStr,
                "1",
                String.valueOf((sectorsNumber - i) * 100.0) + "0"// "100.00"
            };
            sectors[i] = sector;
        }

        inputSectorDataFrame = new InputSectorDataFrame(sectors);

        ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        boolean isDataCorrect = false;

        while(isDataCorrect == false) {

            while (inputSectorDataFrame.getOption().equals("")) {
                waiting();
            }

            switch (inputSectorDataFrame.getOption()) {
                case "cancel":
                    inputSectorDataFrame.dispose();
                    inputSectorDataFrame = null;
                    return null;
                case "confirm":
                    String[][] tableData = inputSectorDataFrame.getTableData();

                    isDataCorrect = true;

                    // check if entered prices and seats are valid
                    for (String[] row : tableData) {
                        if (Pattern.matches("\\d+[.]\\d{2}", row[2]) == false
                            || isNumeric(row[1]) == false
                            || Integer.valueOf(row[1]) < 0) {
                            isDataCorrect = false;
                            break;
                        }
                    }

                    if (isDataCorrect) {
                        for (String[] row : tableData) {
                            String sector = row[0];
                            int ticketsNumber = Integer.parseInt(row[1]);
                            float basePriceInPLN = Float.valueOf(row[2]);
                            int basePrice = Math.round(basePriceInPLN * 100);

                            for (int seat = 0; seat < ticketsNumber; seat++) {
                                Ticket ticket = new Ticket(
                                    sector,
                                    seat,
                                    basePrice
                                );
                                tickets.add(ticket);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                            null,
                            "Ivalid price or seat number.",
                            "Invalid user input",
                            JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                        );
                        inputSectorDataFrame.setOption("");
                        break;
                    }

                    inputSectorDataFrame.dispose();
                    inputSectorDataFrame = null;
                    return tickets;
            }
        }

        // for compiler (this function always have to return something)
        return tickets;
    }

    private void modifyEvent() throws Exception {
        Event event = GlobalVariables.SELECTED_EVENT;
        HashMap<String, String> extendedDetails = event.getExtendedDetails();
        modifyEventFrame = new ModifyEventFrame(extendedDetails);
        Boolean correctData = false;
        while (!correctData){
            while (modifyEventFrame.getOption().equals("")) {
                waiting();
            }

            switch (modifyEventFrame.getOption()) {
                case "cancel":
                    nextFrame = "manageEvents";
                    correctData = true;
                    break;
                case "confirm":
                    if (!modifyEventFrame.getAddress().trim().equals("")
                        && !modifyEventFrame.getCity().trim().equals("")){
                        event.setAddress(modifyEventFrame.getAddress());
                        event.setDateTime(modifyEventFrame.getDateTime());
                        event.setCity(modifyEventFrame.getCity());
                        event.setCountry(modifyEventFrame.getCountry());

                        database.editEvent(event);

                        nextFrame = "manageEvents";
                        correctData = true;
                    } else{
                        modifyEventFrame.setOption("");
                        JOptionPane.showMessageDialog(
                        null,
                        "Incorrect data",
                        "Incorrect data",
                        JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                        );
                    }
            }
        }
        modifyEventFrame.dispose();
        modifyEventFrame = null;
    }

    /**
     * Opens EventDetailsFrame to let user see event details and buy tickets.
     */
    private void eventDetails() throws Exception{
        Event event = database.getEventsButNotOfUser(GlobalVariables.USER_LOGIN).get(GlobalVariables.SELECTED_INDEX);

        HashMap<String, String> eventDetails = event.getDetails();
        HashMap<String, HashMap<String, String>> ticketsMap = event.getTicketsMap();

        String[][] ticketsData = ticketsMapToData(ticketsMap);

        if (ticketsData.length == 0) {
            JOptionPane.showMessageDialog(
                null,
                "Tickets sold out",
                "There are no available tickets",
                JOptionPane.ERROR_MESSAGE    // ads red "x" picture
            );
            nextFrame = "viewEvents";
            return;
        }

        eventDetailsFrame = new EventDetailsFrame(eventDetails, ticketsData);

        while (true) {
            while(eventDetailsFrame.getOption().equals("")) {
                waiting();
            }

            switch (eventDetailsFrame.getOption()) {
                case "cancel":
                    nextFrame = "viewEvents";
                    eventDetailsFrame.dispose();
                    eventDetailsFrame = null;
                    return;
                case "buy":
                    int sectorIndex = eventDetailsFrame.getSector() - 1;

                    String sectorName = String.valueOf(ticketsMap.keySet().toArray()[sectorIndex]);

                    int ticketsAvailableInSector = Integer.valueOf(ticketsMap.get(sectorName).get("number"));

                    int numberChildren = eventDetailsFrame.getNumberChildren();
                    int numberAdults = eventDetailsFrame.getNumberAdults();
                    int numberVips = eventDetailsFrame.getNumberVips();

                    int numberOfSelectedTickets = numberChildren + numberAdults + numberVips;


                    if (
                        numberOfSelectedTickets > ticketsAvailableInSector
                    ) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Some of selected tickets are unavailable.",
                            "Invalid user input",
                            JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                        );
                        eventDetailsFrame.setOption("");
                    } else if (numberOfSelectedTickets == 0) {
                        JOptionPane.showMessageDialog(
                            null,
                            "No tickets selcted.",
                            "Invalid user input",
                            JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                        );
                        eventDetailsFrame.setOption("");
                    } else {
                        ArrayList<Ticket> boughtTickets = new ArrayList<Ticket>();

                        ArrayList<Ticket> availableTickets = event.getTickets();

                        int ticketsFound = 0;
                        for (Ticket ticket: availableTickets) {
                            if (ticket.getSector().equals(sectorName)) {
                                if (numberAdults != 0) {
                                    ticket.setCategory("ADULT");
                                    numberAdults--;
                                } else if (numberChildren != 0) {
                                    ticket.setCategory("CHILD");
                                    numberChildren--;
                                } else if (numberVips != 0) {
                                    ticket.setCategory("VIP");
                                    numberVips--;
                                }

                                boughtTickets.add(ticket);
                                ticketsFound += 1;
                                if (ticketsFound == numberOfSelectedTickets) {
                                    break;
                                }
                            }
                        }

                        database.buyTickets(event, GlobalVariables.USER_LOGIN, boughtTickets);
                        nextFrame = "viewEvents";
                        eventDetailsFrame.dispose();
                        eventDetailsFrame = null;
                        return;
                    }
            }


        }
    }

    /**
     * Opens EventDetailsAfterBuyingFrame
     */
    private void eventDetailsBought() throws Exception{
        Event event = database.getEventsOfUser(GlobalVariables.USER_LOGIN).get(GlobalVariables.SELECTED_INDEX);
        HashMap<String, String> eventDetails = event.getDetails();
        ArrayList<Ticket> tickets = database.getTicketsOfUser(GlobalVariables.USER_LOGIN, event);

        event.setTickets(tickets);
        String sectorName = tickets.get(0).getSector();

        HashMap<String, String> ticketHashMap = event.getTicketsMapSpecificUser();
        String[][] ticketsData = new String[ticketHashMap.size()][2];

        int i = 0;
        for (String category : ticketHashMap.keySet()) {
            ticketsData[i][0] = category;
            ticketsData[i][1] = ticketHashMap.get(category);
            ++i;
        }

        eventDetailsAfterBuyingFrame = new EventDetailsAfterBuyingFrame(
            eventDetails,
            ticketsData,
            sectorName
        );

        while(eventDetailsAfterBuyingFrame.getOption().equals("")) {
            waiting();
        }

        switch (eventDetailsAfterBuyingFrame.getOption()) {
            case "cancel":
                nextFrame = "viewEventsBought";
                break;
            case "remove":
                int maxTicketToRemove = Integer.valueOf(ticketsData[eventDetailsAfterBuyingFrame.getSelectedIndex()][1]);
                String category = ticketsData[eventDetailsAfterBuyingFrame.getSelectedIndex()][0];
                SliderFrame slider = new SliderFrame(maxTicketToRemove);
                while(!slider.getReady()) {
                    waiting();
                }
                int numberToRemove = slider.getNumberOfTickets();
                slider.dispose();
                for (Ticket ticket : tickets){
                    if (numberToRemove == 0){break;}
                    if (ticket.getCategory().equals(category)){
                        database.cancelTicket(event.getName(), ticket);
                        numberToRemove--;
                    }
                }

                nextFrame = "viewEventsBought";
                break;
        }

        eventDetailsAfterBuyingFrame.dispose();
        eventDetailsAfterBuyingFrame = null;
    }

    public void mainLoop() throws Exception {
        boolean runLoop = true;
        GlobalVariables.USER_LOGIN = null;
        GlobalVariables.USER_TYPE = null;

        do {
            waiting();

            switch (nextFrame) {
                case "":
                    break;
                case "welcome":
                    welcome();
                    break;
                case "register":
                    register();
                    break;
                case "login":
                    login();
                    break;
                case "mainMenu":
                    mainMenu();
                    break;
                case "viewEvents":
                    viewEvents();
                    break;
                case "viewEventsBought":
                    viewEventsBought();
                    break;
                case "eventDetailsBought":
                    eventDetailsBought();;
                    break;
                case "manageEvents":
                    manageEvents();
                    break;
                case "modifyEvent":
                    modifyEvent();
                    break;
                case "createEvent":
                    createEvent();
                    break;
                case "eventDetails":
                    eventDetails();
                    break;
                default:
                    throw new Exception("not handled GUI option");
            }
        } while(runLoop);
    }
}



