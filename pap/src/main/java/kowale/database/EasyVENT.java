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

import java.time.LocalDate;
import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
// import java.awt.Frame;
// import javax.swing.JFrame;

public class EasyVENT {
    private static Database database;

    private WelcomeFrame welcomeFrame;
    private RegisterFrame registerFrame;
    private LoginFrame loginFrame;
    private MainMenuFrame mainMenuFrame;
    private CreateEventFrame createEventFrame;
    private ManageEventsFrame manageEventsFrame;
    private ViewEventsFrame viewEventsFrame;
    private InputSectorDataFrame inputSectorDataFrame;
    private EventDetailsFrame eventDetailsFrame;
    private ModifyEventFrame modifyEventFrame;

    private String activeFrameType = "";

    private LocalDate date = LocalDate.now();
    private LocalDateTime dateTime = LocalDateTime.now();
    // private String user_type;
    // private JFrame activeFrame;

    public EasyVENT() throws Exception { // Constructor
        database = new Database(); // create database

        // create example clients
        Client newClient = new Client(
            "Stachu",
            "Jones",
            "sjones",
            hash("sjones"),
            "email",
            -1,
            "gender",
            date
        );
        EasyVENT.database.register_new_user(newClient);
        newClient = new Client(
            "a",
            "a",
            "a",
            hash("a"),
            "email",
            -1,
            "gender",
            date
        );

        EasyVENT.database.register_new_user(newClient);

        // create example organizers
        EventOrganizer newOrganizer = new EventOrganizer(
            "Zbigniew",
            "Boniek",
            "pzpn",
            hash("pzpn"),
            "email",
            -1,
            "company"
        );
        EasyVENT.database.register_new_user(newOrganizer);
        newOrganizer = new EventOrganizer(
            "s",
            "s",
            "s",
            hash("s"),
            "email",
            -1,
            "company"
        );
        EasyVENT.database.register_new_user(newOrganizer);

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

    private String hash(String string) throws Exception {
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

private void welcome() throws Exception{
        welcomeFrame = new WelcomeFrame();
        activeFrameType = GlobalVariables.FRAME_TYPE;  // prevents from creating another window
        
        while (welcomeFrame.getOption() == "") {
            waiting();
        }
        
        switch (welcomeFrame.getOption()) {
            case "register":
                GlobalVariables.FRAME_TYPE= "Register";
                break;
            case "login":
                GlobalVariables.FRAME_TYPE= "Login";
                break;
        }
        welcomeFrame.dispose();
        welcomeFrame = null;
    }

    private void register() throws Exception {
        if(activeFrameType != GlobalVariables.FRAME_TYPE){
            registerFrame = new RegisterFrame();
            activeFrameType = GlobalVariables.FRAME_TYPE;
        } else if (registerFrame.getIsReady()) {
            // check if user input is correct
            if (
                registerFrame.getUserName().trim().length() > 0 &&
                registerFrame.getUserSurname().trim().length() > 0 &&
                registerFrame.getUserLogin().trim().length() > 0 &&
                registerFrame.getUserPassword().trim().length() > 0
            ) {
                if (registerFrame.getAccountType() == 0){
                    Client new_user = new Client(
                        registerFrame.getUserName(),
                        registerFrame.getUserSurname(),
                        registerFrame.getUserLogin(),
                        hash(registerFrame.getUserPassword()),
                        "email",
                        -1,
                        "gender",
                        date
                    );
                    EasyVENT.database.register_new_user(new_user);
                } else {
                    EventOrganizer new_user = new EventOrganizer(
                        registerFrame.getUserName(),
                        registerFrame.getUserSurname(),
                        registerFrame.getUserLogin(),
                        hash(registerFrame.getUserPassword()),
                        "email",
                        -1,
                        "company"
                    );
                    EasyVENT.database.register_new_user(new_user);
                }

                GlobalVariables.FRAME_TYPE = "Welcome";
                registerFrame.dispose();
                registerFrame = null;
            } else {
                registerFrame.setIsReady(false);

                // System.out.println("Not correct");
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
        activeFrameType = GlobalVariables.FRAME_TYPE;

        while (true) {
            while (loginFrame.getIsReady() == false) {
                waiting();
            } 
            
            // check if user input is correct
            if (
                EasyVENT.database.logIntoDatabase(
                    loginFrame.getUserLogin(),
                    hash(loginFrame.getUserPassword())
                )
            ) {
                GlobalVariables.FRAME_TYPE = "MainMenu";
                loginFrame.dispose();
                loginFrame = null;
                return;
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
        activeFrameType = GlobalVariables.FRAME_TYPE;  // prevents from creating another window

        while (mainMenuFrame.getOption() == "") {
            waiting();
        }

        switch (mainMenuFrame.getOption()) {
            case "logout":
                GlobalVariables.USER_NAME = null;
                GlobalVariables.USER_ID = -1;
                GlobalVariables.USER_TYPE = null;
                GlobalVariables.FRAME_TYPE = "Welcome";
                break;
            case "viewEvents":
                GlobalVariables.FRAME_TYPE = "ViewEvents";
                break;
            case "manageEvents":
                GlobalVariables.FRAME_TYPE = "ManageEvents";
                break;
            case "createEvent":
                GlobalVariables.FRAME_TYPE = "CreateEvent";
                break;
            case "manageTickets":
                // GlobalVariables.FRAME_TYPE = "ManageTickets";
                JOptionPane.showMessageDialog(
                    null,
                    "TODO",
                    "TODO",
                    JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                );
                GlobalVariables.FRAME_TYPE = "MainMenu";
                activeFrameType = null;
                break;
        }
        mainMenuFrame.dispose();
        mainMenuFrame = null;
    }

    private void viewEvents() throws Exception{
        String[][] data = eventsToData(database.getEvents());
        viewEventsFrame = new ViewEventsFrame(data);
        activeFrameType = GlobalVariables.FRAME_TYPE;

        while (viewEventsFrame.getOption() == "") {
            waiting();
        }

        switch (viewEventsFrame.getOption()) {
            case "cancel":
                GlobalVariables.FRAME_TYPE = "MainMenu";
                break;
            case "details":
                GlobalVariables.FRAME_TYPE = "EventDetails";
                GlobalVariables.SELECTED_INDEX = viewEventsFrame.getSelectedIndex();
                break;
        }
        viewEventsFrame.dispose();
        viewEventsFrame = null;
    }

    private void manageEvents() {
        if(activeFrameType != GlobalVariables.FRAME_TYPE){
            // TODO:
            // show only events connected to logged organizer
            // remove events
            // modify events
            String[][] data = eventsToData(database.getEvents());
            manageEventsFrame = new ManageEventsFrame(data);
            activeFrameType = GlobalVariables.FRAME_TYPE;
        } else if (manageEventsFrame.getOption() != "") {
            switch (manageEventsFrame.getOption()) {
                case "cancel":
                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    break;
                case "remove":
                    GlobalVariables.SELECTED_INDEX = manageEventsFrame.getSelectedIndex();
                    //Database.removeEvent();
                    JOptionPane.showMessageDialog(
                        null,
                        "TODO",
                        "TODO",
                        JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                    );
                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    break;
                case "modify":
                    GlobalVariables.SELECTED_INDEX = manageEventsFrame.getSelectedIndex();
                    //Database.modifyEvent();
                    GlobalVariables.FRAME_TYPE = "ModifyEvent";
                    break;
            }
            manageEventsFrame.dispose();
            manageEventsFrame = null;
        }
    }

    private void createEvent() throws Exception{
        createEventFrame = new CreateEventFrame();
        activeFrameType = GlobalVariables.FRAME_TYPE;

        while (createEventFrame.getOption() == "") {
            waiting();
        }

        switch (createEventFrame.getOption()) {
            case "cancel":
                GlobalVariables.FRAME_TYPE = "MainMenu";
                createEventFrame.dispose();
                createEventFrame = null;
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
                    // GlobalVariables.FRAME_TYPE = "InputSectorDataFrame";
                    
                }
                GlobalVariables.FRAME_TYPE = "MainMenu";
                break;
        }
    }

    private ArrayList<Ticket> inputSectorData(int sectorsNumber) throws Exception{
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        // int sectorsNumber = GlobalVariables.SECTORS_NUMBER;
        String[][] sectors = new String[sectorsNumber][3];
        for (int i=0; i<sectorsNumber; ++i) {
            String iStr = String.valueOf(i+1);
            String[] sector = {iStr, "1", "1", "1"};
            sectors[i] = sector;
        }
        inputSectorDataFrame = new InputSectorDataFrame(sectors);
        // activeFrameType = GlobalVariables.FRAME_TYPE;

        while (inputSectorDataFrame.getOption() == "") {
            waiting();
        }

        switch (inputSectorDataFrame.getOption()) {
            case "cancel":
                // GlobalVariables.EVENT = null;
                // GlobalVariables.SECTORS_NUMBER = -1;

                // GlobalVariables.FRAME_TYPE = "MainMenu";
                return null;
                // break;
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

                // GlobalVariables.EVENT.setTickets(tickets);
                // EasyVENT.database.createEvent(GlobalVariables.EVENT);
                // GlobalVariables.EVENT = null;

                // GlobalVariables.FRAME_TYPE = "MainMenu";
                break;
        }
        inputSectorDataFrame.dispose();
        inputSectorDataFrame = null;
        return tickets;
    }

    private void modifyEvent() {
        if(activeFrameType != GlobalVariables.FRAME_TYPE){
            Event event = database.getEvents().get(GlobalVariables.SELECTED_INDEX);
            HashMap<String, String> extendedDetails = event.getExtendedDetails();
            modifyEventFrame = new ModifyEventFrame(extendedDetails);
            activeFrameType = GlobalVariables.FRAME_TYPE;
        } else if (modifyEventFrame.getOption() != "") {
            switch (modifyEventFrame.getOption()) {
                case "cancel":
                    GlobalVariables.FRAME_TYPE = "ManageEvents";
                    break;
                case "confirm":
                    JOptionPane.showMessageDialog(
                        null,
                        "TODO",
                        "TODO",
                        JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                    );
                    GlobalVariables.FRAME_TYPE = "ManageEvents";
                    break;
            }
            modifyEventFrame.dispose();
            modifyEventFrame = null;
        }
    }

    private void eventDetails() {
        // TODO: actual ticket buying
        if(activeFrameType != GlobalVariables.FRAME_TYPE){
            Event event = database.getEvents().get(GlobalVariables.SELECTED_INDEX);
            HashMap<String, String> eventDetails = event.getDetails();
            HashMap<String, HashMap<String, Integer>> ticketsMap = event.getTicketsMap();
            String[][] ticketsData = ticketsMapToData(ticketsMap);
            eventDetailsFrame = new EventDetailsFrame(eventDetails, ticketsData);
            activeFrameType = GlobalVariables.FRAME_TYPE;
        } else if (eventDetailsFrame.getOption() != "") {
            switch (eventDetailsFrame.getOption()) {
                case "cancel":
                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    eventDetailsFrame.dispose();
                    eventDetailsFrame = null;
                    break;
                case "confirm":
                    // Database.buyTickets();

                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    eventDetailsFrame.dispose();
                    eventDetailsFrame = null;
                    break;
                case "calculate":
                    // GlobalVariables.FRAME_TYPE = "MainMenu";
                    break;
            }
        }
    }

    public void mainLoop() throws Exception {
        boolean runLoop = true;
        GlobalVariables.FRAME_TYPE = "Welcome";
        GlobalVariables.USER_NAME = null;
        GlobalVariables.USER_ID = -1;
        GlobalVariables.USER_TYPE = null;

        do {
            waiting();

            switch (GlobalVariables.FRAME_TYPE) {
                case "Welcome":
                    welcome();
                    break;
                case "Register":
                    register();
                    break;
                case "Login":
                    login();
                    break;
                case "MainMenu":
                    mainMenu();
                    break;
                case "ViewEvents":
                    viewEvents();
                    break;
                case "ManageEvents":
                    manageEvents();
                    break;
                case "ModifyEvent":
                    modifyEvent();
                    break;
                case "CreateEvent":
                    createEvent();
                    break;
                // case "InputSectorDataFrame":
                //     inputSectorData();
                //     break;
                case "EventDetails":
                    eventDetails();
                    break;
            }
        } while(runLoop);
    }
}



