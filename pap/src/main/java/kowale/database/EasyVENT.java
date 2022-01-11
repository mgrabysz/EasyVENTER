package kowale.database;

import kowale.userInterface.*;
import kowale.user.*;
import kowale.event.Event;
import kowale.ticket.Ticket;

import java.lang.Thread;

// import javax.print.event.PrintJobAdapter;
// import javax.sound.midi.Track;
import javax.swing.JOptionPane;

import java.util.HashMap;

import java.security.MessageDigest;
// import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
// import java.nio.file.WatchService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
// import java.awt.Frame;
// import javax.swing.JFrame;

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
    // private String activeFrameType = "";

    // private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private LocalDate date = LocalDate.now();
    private LocalDateTime dateTime = LocalDateTime.now();
    // private String user_type;
    // private JFrame activeFrame;

    public EasyVENT() throws Exception { // Constructor
        database = new Database(); // create database

        // create example clients
        // Client newClient = new Client(
        //     "a",
        //     "a",
        //     "a",
        //     hash("a"),
        //     "email",
        //     123456789,
        //     "N",
        //     date
        // );

        // database.register_new_user(newClient);
        // database.registerUser(newClient);

        // create example organizers
        // EventOrganizer newOrganizer = new EventOrganizer(
        //     "s",
        //     "s",
        //     "s",
        //     hash("s"),
        //     "email",
        //     123456789,
        //     "company"
        // );

        // database.register_new_user(newOrganizer);
        // database.registerUser(newOrganizer);

        // create example events

        // ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        // String[][] data = {
        //     {"A", "100", "99999"},
        //     {"B", "200", "12300"}
        // };
        // for (String[] row : data) {
        //     String sector = row[0];
        //     int ticketsNumber = Integer.parseInt(row[1]);
        //     int basePrice = Integer.parseInt(row[2]);

        //     for (int seat = 0; seat < ticketsNumber; seat++) {
        //         Ticket ticket = new Ticket(
        //             sector,
        //             seat,
        //             basePrice
        //         );
        //         tickets.add(ticket);
        //     }
        // }

        // Event event = new Event(
        //     "Ludzie biegający w kółko",
        //     "Google",
        //     "Polska",
        //     "Warszafka",
        //     "Ulica",
        //     dateTime
        // );
        // event.setTickets(tickets);
        // EasyVENT.database.createEvent(event);

        // event = new Event(
        //     "Meczyk jakiś",
        //     "Firma Krzak",
        //     "Polska",
        //     "Bydgoszcz",
        //     "Ulica",
        //     dateTime
        // );
        // event.setTickets(tickets);
        // EasyVENT.database.createEvent(event);
        // System.out.println(isNumeric("12a"));

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

        // System.out.println(hexString.toString());
        return hexString.toString();
    }

    /**
     * convert events list to data for GUI table
     */
    private String[][] eventsToData(ArrayList<Event> events) {
        String[][] data = new String[events.size()][4];

        if (events.size() > 0) {
            // data = new String[events.size()][4];
            for (int i = 0; i < events.size(); i++) {
                Event event = events.get(i);
                data[i] = event.getInfo();
                // System.out.println(event.getInfo());
            }
        }

        // System.out.println("events:");
        // System.out.println(events);
        // System.out.println("data:");
        // System.out.println(data);

        return data;
    }
    
    /**
     * convert tickets map to data for GUI table
     */
    private String[][] ticketsMapToData(HashMap<String, HashMap<String, String>> ticketsMap) {
        
        String[][]data = new String[ticketsMap.size()][3];

        if (ticketsMap.size() > 0) {
            // data = new String[events.size()][4];
            int i = 0;
            for (String sector : ticketsMap.keySet()) {
                HashMap<String, String> numberPrice = ticketsMap.get(sector);
                data[i][0] = sector;
                data[i][1] = numberPrice.get("number");
                String price = numberPrice.get("price");
                // System.out.println(price);
                if (price.substring(price.length()-1).equals("0")) {
                    price += "0";
                }
                data[i][2] = price;
                i++;
            }
        }

        // System.out.println("events:");
        // System.out.println(events);
        // System.out.println("data:");
        // System.out.println(data);

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
        // TODO: input checking
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
                // DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
                map.put(
                    "date",
                    registerClientFrame.getDate().format(
                        // DateTimeFormatter.ofPattern("dd-MM-yyyy")
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
        // TODO: input checking
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
        // TODO: get only events for which client has at least one ticket

        // ArrayList<Event> allEvents = database.getEvents(); // OLD!!!

        ArrayList<Event> userEvents = database.getEventsOfUser(GlobalVariables.USER_LOGIN);

        // ArrayList<Event> clientEvents = new ArrayList<Event>();
        // for (Event event: allEvents) {
        //     if (event.get)
        // }
        // ArrayList<Event> events = database.getEventsOfUser(user, event)
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
                // System.out.println("details");
                nextFrame = "eventDetailsBought";
                GlobalVariables.SELECTED_INDEX = viewEventsFrame.getSelectedIndex();
                break;
        }

        viewEventsFrame.dispose();
        viewEventsFrame = null;
    }


    /**
     * Opens ManageEventsFrame with events created by currently logged in organizer.
     */
    private void manageEvents() throws Exception{
        // TODO:
        // actually modify events

        ArrayList<Event> allEvents = database.getAllEvents();
        ArrayList<Event> organizerEvents = new ArrayList<Event>();

        // get only events that are created by logged in organizer (event orgnizer == current user login)
        for (Event event: allEvents) {
            // System.out.println(event.getOrganizer());
            if (event.getOrganizer().equals(GlobalVariables.USER_LOGIN)) {
                organizerEvents.add(event);
                // System.out.println(GlobalVariables.USER_LOGIN);
            }
        }
        String[][] data = eventsToData(organizerEvents);
        manageEventsFrame = new ManageEventsFrame(data);

        while (manageEventsFrame.getOption().equals("")) {
            waiting();
        }

        switch (manageEventsFrame.getOption()) {
            case "cancel":
                nextFrame = "mainMenu";
                break;
            // case "remove":
            //     // TODO
            //     GlobalVariables.SELECTED_EVENT = organizerEvents.get(manageEventsFrame.getSelectedIndex());
            //     // GlobalVariables.SELECTED_INDEX = manageEventsFrame.getSelectedIndex();
            //     //Database.removeEvent();
            //     JOptionPane.showMessageDialog(
            //         null,
            //         "TODO",
            //         "TODO",
            //         JOptionPane.ERROR_MESSAGE    // ads red "x" picture
            //     );
            //     nextFrame = "manageEvents";
            //     break;
            case "modify":
                GlobalVariables.SELECTED_EVENT = organizerEvents.get(manageEventsFrame.getSelectedIndex());
                // GlobalVariables.SELECTED_INDEX = manageEventsFrame.getSelectedIndex();
                //Database.modifyEvent();
                nextFrame = "modifyEvent";
                break;
        }

        manageEventsFrame.dispose();
        manageEventsFrame = null;
    }

    /**
     * Opens CreateEventFrame.
     */
    private void createEvent() throws Exception{
        /* TODO:
        input data check -- date check completed, anything else to check?
        */
        createEventFrame = new CreateEventFrame();
        boolean isDataCorrect = false;

        while (isDataCorrect == false) {
            while (createEventFrame.getOption().equals("")) {
                waiting();
            }

            switch (createEventFrame.getOption()) {
                case "cancel":
                    createEventFrame.dispose();
                    createEventFrame = null;

                    nextFrame = "mainMenu";
                    break;
                case "confirm":
                    String company = database.getCompanyName(GlobalVariables.USER_LOGIN);

                    // check if input data si correct
                    if (createEventFrame.getDateTime().compareTo(LocalDateTime.now()) > 0 ){
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
                            // GlobalVariables.SECTORS_NUMBER = createEventFrame.getNumOfSectors();
                            // nextFrame = "InputSectorDataFrame";
                        }

                        nextFrame = "mainMenu";

                        createEventFrame.dispose();
                        createEventFrame = null;
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
        // TODO: input data check:
        // przecinek/kropka
        // dokladnosc do 0.01 PLN
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

                inputSectorDataFrame.dispose();
                inputSectorDataFrame = null;
                return tickets;
        }

        return null;
    }

    private void modifyEvent() throws Exception {
        // TODO:
        // actual event modifying (database)
        // ISSUE: event time and date is not displayed correctly (maybe fixed, I don't know)

        Event event = GlobalVariables.SELECTED_EVENT;
        HashMap<String, String> extendedDetails = event.getExtendedDetails();
        modifyEventFrame = new ModifyEventFrame(extendedDetails);

        while (modifyEventFrame.getOption().equals("")) {
            waiting();
        }

        switch (modifyEventFrame.getOption()) {
            case "cancel":
                nextFrame = "manageEvents";
                break;
            case "confirm":
                // TODO
                JOptionPane.showMessageDialog(
                    null,
                    "TODO",
                    "TODO",
                    JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                );
                nextFrame = "modifyEvent";
                break;
        }

        modifyEventFrame.dispose();
        modifyEventFrame = null;
    }

    private void eventDetails() throws Exception{
        /*
        open EventDetailsFrame to let user see event details and buy tickets
        */

        // TODO:
        // actual ticket buying

        Event event = database.getEventsButNotOfUser(GlobalVariables.USER_LOGIN).get(GlobalVariables.SELECTED_INDEX);


        HashMap<String, String> eventDetails = event.getDetails();
        HashMap<String, HashMap<String, String>> ticketsMap = event.getTicketsMap();

        System.out.println(ticketsMap);

        String[][] ticketsData = ticketsMapToData(ticketsMap);
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

                    // System.out.println(ticketsAvailableInSector);

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
                                System.out.println(event.getName());
                                System.out.println(boughtTickets.get(ticketsFound).getCategory());
                                System.out.println(boughtTickets.get(ticketsFound).getSector());
                                System.out.println(boughtTickets.get(ticketsFound).getSeat());
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

    private void eventDetailsBought() throws Exception{
        /*
        open EventDetailsAfterBuyingFrame
        user can cancel selected ticket here
        */

        // TODO:
        // actual ticket canceling

        Event event = database.getEventsOfUser(GlobalVariables.USER_LOGIN).get(GlobalVariables.SELECTED_INDEX);

        HashMap<String, String> eventDetails = event.getDetails();

        ArrayList<Ticket> tickets = database.getTicketsOfUser(GlobalVariables.USER_LOGIN, event);

        event.setTickets(tickets);
        String sectorName = tickets.get(0).getSector();

        // tu jesteśmy

        // TODO: it should display number of seats bought by particular user, not in general

        HashMap<String, String> ticketHashMap = event.getTicketsMapSpecificUser();
        String[][] ticketsData = new String[ticketHashMap.size()][2];

        int i = 0;
        for (String category : ticketHashMap.keySet()) {
            ticketsData[i][0] = category;
            ticketsData[i][1] = ticketHashMap.get("category");
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
            case "confirm":
                // Database.removeTicket();
                nextFrame = "mainMenu";
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



