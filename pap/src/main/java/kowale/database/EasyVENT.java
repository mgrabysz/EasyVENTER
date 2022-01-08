package kowale.database;

import kowale.userInterface.*;
import kowale.user.*;
import kowale.event.Event;
import kowale.ticket.Ticket;

import java.lang.Thread;

import javax.print.event.PrintJobAdapter;
import javax.sound.midi.Track;
import javax.swing.JOptionPane;

import java.util.HashMap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.nio.file.WatchService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
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
        Client newClient = new Client(
            "a",
            "a",
            "a",
            hash("a"),
            "email",
            123456789,
            "N",
            date
        );

        // database.register_new_user(newClient);
        database.registerUser(newClient);

        // create example organizers
        EventOrganizer newOrganizer = new EventOrganizer(
            "s",
            "s",
            "s",
            hash("s"),
            "email",
            123456789,
            "company"
        );
        
        // database.register_new_user(newOrganizer);
        database.registerUser(newOrganizer);

        // create example events
        
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        String[][] data = {
            {"A", "100", "999999"},
            {"B", "200", "888888"}
        };
        for (String[] row : data) {
            String sector = row[0];
            int ticketsNumber = Integer.parseInt(row[1]);
            int basePrice = Integer.parseInt(row[2]);
            
            for (int seat = 0; seat < ticketsNumber; seat++) {
                Ticket ticket = new Ticket(
                    sector,
                    seat,
                    basePrice
                );
                tickets.add(ticket);
            }
        }

        Event event = new Event(
            "Ludzie biegający w kółko",
            "Google",
            "Polska",
            "Warszafka",
            "Ulica",
            dateTime
        );
        event.setTickets(tickets);
        EasyVENT.database.createEvent(event);

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

    private void waiting() throws Exception {
        try {
            Thread.sleep(100);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean isNumeric(String string) {
        // System.out.println(string);
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

    public static String hash(String string) throws Exception {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] hash = sha256.digest(string.getBytes(StandardCharsets.UTF_8));
        // string = new String(digest, StandardCharsets.UTF_8);
        // string = digest.toString(); // nondeterministic behaviour when using .toString()

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

    private String[][] eventsToData(ArrayList<Event> events) {
        String[][] data = new String[events.size()][4];

        if (events.size() > 0) {
            // data = new String[events.size()][4];
            for (int i = 0; i < events.size(); i++) {
                Event event = events.get(i);
                data[i] = event.getInfo();
            }
        }

        // System.out.println("events:");
        // System.out.println(events);
        // System.out.println("data:");
        // System.out.println(data);

        return data;
    }

    private String[][] ticketsMapToData(HashMap<String, HashMap<String, Integer>> ticketsMap) {
        String[][]data = new String[ticketsMap.size()][3];

        if (ticketsMap.size() > 0) {
            // data = new String[events.size()][4];
            int i = 0;
            for (String sector : ticketsMap.keySet()) {
                HashMap<String, Integer> numberPrice = ticketsMap.get(sector);
                data[i][0] = sector;
                data[i][1] = numberPrice.get("number").toString();
                data[i][2] = numberPrice.get("price").toString();
                i++;
            }
        }

        // System.out.println("events:");
        // System.out.println(events);
        // System.out.println("data:");
        // System.out.println(data);

        return data;
    }

    private void welcome() throws Exception {
        welcomeFrame = new WelcomeFrame();
        
        while (welcomeFrame.getOption() == "") {
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

    private void register() throws Exception {
        registerFrame = new RegisterFrame();

        while (true) {
            while (registerFrame.getIsReady() == false) {
                waiting();
            }
    
            // check if user input is correct
            if (
                registerFrame.getUserName().trim().length() > 0 &&
                registerFrame.getUserSurname().trim().length() > 0 &&
                registerFrame.getUserLogin().trim().length() > 0 &&
                registerFrame.getUserPassword().trim().length() > 0
            ) {
                int accountType = registerFrame.getAccountType();
                registerFrame.dispose();

                if (accountType == 0){
                    HashMap<String, String> additionalInfo = registerClient();

                    // System.out.println(additionalInfo.get("date"));
                    LocalDate date = LocalDate.parse(
                        additionalInfo.get("date")
                    );

                    Client new_user = new Client(
                        registerFrame.getUserName(),
                        registerFrame.getUserSurname(),
                        registerFrame.getUserLogin(),
                        hash(registerFrame.getUserPassword()),
                        additionalInfo.get("email"),
                        Integer.parseInt(additionalInfo.get("telephone")),
                        additionalInfo.get("gender"),
                        date
                    );

                    EasyVENT.database.registerUser(new_user);
                } else {
                    HashMap<String, String> additionalInfo = registerOrganizer();

                    EventOrganizer new_user = new EventOrganizer(
                        registerFrame.getUserName(),
                        registerFrame.getUserSurname(),
                        registerFrame.getUserLogin(),
                        hash(registerFrame.getUserPassword()),
                        additionalInfo.get("email"),
                        Integer.parseInt(additionalInfo.get("telephone")),
                        additionalInfo.get("company")
                    );
                    EasyVENT.database.registerUser(new_user);
                }

                registerFrame = null;
                nextFrame = "welcome";
                break;
            } else {
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

    private HashMap<String, String> registerClient() throws Exception {
        // TODO: input checking
        registerClientFrame = new RegisterClientFrame();
    
        while (true) {
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
                HashMap<String, String> map = new HashMap<String, String>();
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
        
                return map;
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
    }

    private HashMap<String, String> registerOrganizer() throws Exception {
        // TODO: input checking
        registerOrganizerFrame = new RegisterOrganizerFrame();
    
        while(true) {
            while(registerOrganizerFrame.getIsReady() == false) {
                waiting();
            }

            String telephone = registerOrganizerFrame.getTelephone();
            if (
                registerOrganizerFrame.getEmail().trim().length() > 0 &&
                registerOrganizerFrame.getCompany().trim().length() > 0 &&
                telephone.length() == 9 &&
                isNumeric(telephone)
            ) {
                HashMap<String, String> map = new HashMap<String, String>();
        
                map.put("email", registerOrganizerFrame.getEmail());
                map.put("telephone", registerOrganizerFrame.getTelephone());
                map.put("company", registerOrganizerFrame.getCompany());
        
                registerOrganizerFrame.dispose();
                registerOrganizerFrame = null;
        
                return map;
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
    }


    private void login() throws Exception {
        loginFrame = new LoginFrame();

        while (true) {
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
                nextFrame = "mainMenu";
                loginFrame.dispose();
                loginFrame = null;
                break;
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

    private void mainMenu() throws Exception{
        mainMenuFrame = new MainMenuFrame(GlobalVariables.USER_TYPE);

        while (mainMenuFrame.getOption() == "") {
            waiting();
        }

        switch (mainMenuFrame.getOption()) {
            case "logout":
                GlobalVariables.USER_NAME = null;
                GlobalVariables.USER_ID = -1;
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
                // nextFrame = "manageTickets";
                JOptionPane.showMessageDialog(
                    null,
                    "TODO",
                    "TODO",
                    JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                );
                nextFrame = "mainMenu";
                break;
        }

        mainMenuFrame.dispose();
        mainMenuFrame = null;
    }

    private void viewEvents() throws Exception{
        String[][] data = eventsToData(database.getEvents());
        viewEventsFrame = new ViewEventsFrame(data);
        // activeFrameType = GlobalVariables.FRAME_TYPE;

        while (viewEventsFrame.getOption() == "") {
            waiting();
        }

        switch (viewEventsFrame.getOption()) {
            case "cancel":
                nextFrame = "mainMenu";
                break;
            case "details":
                nextFrame = "eventDetails";
                GlobalVariables.SELECTED_INDEX = viewEventsFrame.getSelectedIndex();
                break;
        }

        viewEventsFrame.dispose();
        viewEventsFrame = null;
    }

    private void manageEvents() throws Exception{
        // TODO:
        // show only events connected to logged organizer
        // remove events
        // modify events
        String[][] data = eventsToData(database.getEvents());
        manageEventsFrame = new ManageEventsFrame(data);

        while (manageEventsFrame.getOption() == "") {
            waiting();
        }

        switch (manageEventsFrame.getOption()) {
            case "cancel":
                nextFrame = "mainMenu";
                break;
            case "remove":
                // TODO
                GlobalVariables.SELECTED_INDEX = manageEventsFrame.getSelectedIndex();
                //Database.removeEvent();
                JOptionPane.showMessageDialog(
                    null,
                    "TODO",
                    "TODO",
                    JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                );
                nextFrame = "manageEvents";
                break;
            case "modify":
                GlobalVariables.SELECTED_INDEX = manageEventsFrame.getSelectedIndex();
                //Database.modifyEvent();
                nextFrame = "modifyEvent";
                break;
        }

        manageEventsFrame.dispose();
        manageEventsFrame = null;
    }

    private void createEvent() throws Exception{
        // TODO: input data check
        createEventFrame = new CreateEventFrame();

        while (createEventFrame.getOption() == "") {
            waiting();
        }

        switch (createEventFrame.getOption()) {
            case "cancel":
                createEventFrame.dispose();
                createEventFrame = null;

                nextFrame = "mainMenu";
                break;
            case "confirm":
                Event event = new Event(
                    createEventFrame.getName(),
                    GlobalVariables.USER_NAME,
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
                    EasyVENT.database.createEvent(event);
                    // GlobalVariables.SECTORS_NUMBER = createEventFrame.getNumOfSectors();
                    // nextFrame = "InputSectorDataFrame";
                    
                }

                nextFrame = "mainMenu";
                break;
        }

    }

    private ArrayList<Ticket> inputSectorData(int sectorsNumber) throws Exception{
        // TODO: input data check
        String[][] sectors = new String[sectorsNumber][3];
        for (int i=0; i<sectorsNumber; ++i) {
            String iStr = String.valueOf(i+1);
            String[] sector = {iStr, "1", "1", "1"};
            sectors[i] = sector;
        }
        inputSectorDataFrame = new InputSectorDataFrame(sectors);

        ArrayList<Ticket> tickets = new ArrayList<Ticket>();   

        while (inputSectorDataFrame.getOption() == "") {
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
                    int basePrice = Integer.parseInt(row[2]);
                    
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

    private void modifyEvent() throws Exception{
        // TODO:
        // ISSUE: event time and date is not displayed correctly
        Event event = database.getEvents().get(GlobalVariables.SELECTED_INDEX);
        HashMap<String, String> extendedDetails = event.getExtendedDetails();
        modifyEventFrame = new ModifyEventFrame(extendedDetails);

        while (modifyEventFrame.getOption() == "") {
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
        // TODO:
        // actual ticket buying
        Event event = database.getEvents().get(GlobalVariables.SELECTED_INDEX);
        HashMap<String, String> eventDetails = event.getDetails();
        HashMap<String, HashMap<String, Integer>> ticketsMap = event.getTicketsMap();
        String[][] ticketsData = ticketsMapToData(ticketsMap);
        eventDetailsFrame = new EventDetailsFrame(eventDetails, ticketsData);

        while(eventDetailsFrame.getOption() == "") {
            waiting();
        }

        switch (eventDetailsFrame.getOption()) {
            case "cancel":
                nextFrame = "mainMenu";
                break;
            case "confirm":
                // Database.buyTickets();
                nextFrame = "mainMenu";
                break;
        }

        eventDetailsFrame.dispose();
        eventDetailsFrame = null;
    }

    public void mainLoop() throws Exception {
        boolean runLoop = true;
        // nextFrame = "Welcome";
        GlobalVariables.USER_NAME = null;
        GlobalVariables.USER_ID = -1;
        GlobalVariables.USER_TYPE = null;

        do {
            waiting();

            switch (nextFrame) {
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
            }
        } while(runLoop);
    }
}



