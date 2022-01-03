package kowale.database;

import kowale.userInterface.*;
import kowale.user.*;
import kowale.event.Event;

import java.lang.Thread;

import javax.swing.JOptionPane;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private String activeFrameType = "";
    // private String user_type;
    // private JFrame activeFrame;

    public EasyVENT() throws NoSuchAlgorithmException { // Constructor
        database = new Database(); // create database

        // create example clients
        Client newClient = new Client("Stachu", "Jones", "sjones", hash("sjones"));
        EasyVENT.database.register_new_user(newClient);
        newClient = new Client("a", "a", "a", hash("a"));

        EasyVENT.database.register_new_user(newClient);

        // create example organizers
        EventOrganizer newOrganizer = new EventOrganizer("Zbigniew", "Boniek", "pzpn", hash("pzpn"));
        EasyVENT.database.register_new_user(newOrganizer);
        newOrganizer = new EventOrganizer("s", "s", "s", hash("s"));
        EasyVENT.database.register_new_user(newOrganizer);

        // create exampl events
        LocalDateTime dateTime = LocalDateTime.now();
        Event event = new Event("Mecz", null, "PGE Narodowy", dateTime);
        EasyVENT.database.createEvent(event);
        event = new Event("Meczyk jakiś", null, "Bydgoszcz", dateTime);
        EasyVENT.database.createEvent(event);

        mainLoop();
    }

    private String hash(String string) throws NoSuchAlgorithmException {
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
                data[i] = event.getEventInfo();
            }
    
        }

        System.out.println("events:");
        System.out.println(events);
        System.out.println("data:");
        System.out.println(data);

        return data;
    }

    private void welcome() {
        if(activeFrameType != GlobalVariables.FRAME_TYPE){
            welcomeFrame = new WelcomeFrame();
            activeFrameType = GlobalVariables.FRAME_TYPE;  // prevents from creating another window
        } else if (welcomeFrame.getOption() != "") {
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
    }

    private void register() throws NoSuchAlgorithmException {
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
                        hash(registerFrame.getUserPassword())
                    );
                    EasyVENT.database.register_new_user(new_user);
                } else {
                    EventOrganizer new_user = new EventOrganizer(
                        registerFrame.getUserName(),
                        registerFrame.getUserSurname(),
                        registerFrame.getUserLogin(),
                        hash(registerFrame.getUserPassword())
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

    private void login() throws NoSuchAlgorithmException {
        if(activeFrameType != GlobalVariables.FRAME_TYPE){
            loginFrame = new LoginFrame();
            activeFrameType = GlobalVariables.FRAME_TYPE;
        } else if (loginFrame.getIsReady()) {
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

    private void mainMenu() {
        if(activeFrameType != GlobalVariables.FRAME_TYPE){
            mainMenuFrame = new MainMenuFrame(GlobalVariables.USER_TYPE);
            activeFrameType = GlobalVariables.FRAME_TYPE;  // prevents from creating another window
        } else if (mainMenuFrame.getOption() != "") {
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
                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    activeFrameType = null;
                    break;
            }
            mainMenuFrame.dispose();
            mainMenuFrame = null;
        }
    }

    private void viewEvents() {
        if(activeFrameType != GlobalVariables.FRAME_TYPE){
            String[][] data = eventsToData(database.getEvents()); 
            viewEventsFrame = new ViewEventsFrame(data);
            activeFrameType = GlobalVariables.FRAME_TYPE;
        } else if (viewEventsFrame.getOption() != "") {
            switch (viewEventsFrame.getOption()) {
                case "cancel":
                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    break;
                case "details":
                    GlobalVariables.FRAME_TYPE = "EventDetails";
                    break;
            }
            viewEventsFrame.dispose();
            viewEventsFrame = null;
        }
    }

    private void viewEventsOrganizer() {
        if(activeFrameType != GlobalVariables.FRAME_TYPE){
            // TODO: show only events connected to logged organizer
            String[][] data = eventsToData(database.getEvents());
            manageEventsFrame = new ManageEventsFrame(data);
            activeFrameType = GlobalVariables.FRAME_TYPE;
        } else if (manageEventsFrame.getOption() != "") {
            switch (manageEventsFrame.getOption()) {
                case "cancel":
                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    break;
                case "remove":
                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    break;
                case "modify":
                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    break;
            }
            manageEventsFrame.dispose();
            manageEventsFrame = null;
        }
    }

    private void createEvent() {
        // TODO: assign proper organizer to event
        if(activeFrameType != GlobalVariables.FRAME_TYPE){
            createEventFrame = new CreateEventFrame();
            activeFrameType = GlobalVariables.FRAME_TYPE;
        } else if (createEventFrame.getOption() != "") {
            switch (createEventFrame.getOption()) {
                case "cancel":
                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    break;
                case "create":
                    GlobalVariables.EVENT = new Event(
                        createEventFrame.getName(),
                        GlobalVariables.USER_NAME,
                        createEventFrame.getLocatione(),
                        createEventFrame.getDateTime()
                    );
                    GlobalVariables.SECTORS_NUMBER = createEventFrame.getNumOfSectors();
                    GlobalVariables.FRAME_TYPE = "InputSectorDataFrame";
                    break;
            }
            createEventFrame.dispose();
            createEventFrame = null;
        }
    }

    private void inputSectorData() {
        if(activeFrameType != GlobalVariables.FRAME_TYPE){
            int number = GlobalVariables.SECTORS_NUMBER;
            String[][] sectors = new String[number][3];
            for (int i=0; i<number; ++i) {
                String iStr = String.valueOf(i+1);
                String[] sector = {iStr, "0", "0", "0"};
                sectors[i] = sector;
            }
            inputSectorDataFrame = new InputSectorDataFrame(sectors);
            activeFrameType = GlobalVariables.FRAME_TYPE;
        } else if (inputSectorDataFrame.getOption() != "") {
            switch (inputSectorDataFrame.getOption()) {
                case "cancel":
                    GlobalVariables.EVENT = null;
                    GlobalVariables.SECTORS_NUMBER = -1;

                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    break;
                case "confirm":
                    GlobalVariables.EVENT.setTickets(
                        inputSectorDataFrame.getTickets()
                    );
                    EasyVENT.database.createEvent(GlobalVariables.EVENT);
                    GlobalVariables.EVENT = null;

                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    break;
            }
            inputSectorDataFrame.dispose();
            inputSectorDataFrame = null;
        }
    }

    private void eventDetails() {
        if(activeFrameType != GlobalVariables.FRAME_TYPE){
            String[][] data = {
                { "Meczyk jakiś", "Firma Krzak", "Bydgoszcz", "35.19.2022 25:72" },
                { "Ludzie biegający w kółko", "Google", "Warszafka", "48.17.2023 29:81" }
            };
            eventDetailsFrame = new EventDetailsFrame(data);
            activeFrameType = GlobalVariables.FRAME_TYPE;
        } else if (eventDetailsFrame.getOption() != "") {
            switch (eventDetailsFrame.getOption()) {
                case "cancel":
                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    break;
                case "confirm":
                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    break;
                case "calculate":
                    GlobalVariables.FRAME_TYPE = "MainMenu";
                    break;
            }
            eventDetailsFrame.dispose();
            eventDetailsFrame = null;
        }
    }

    public void mainLoop() throws NoSuchAlgorithmException {
        boolean runLoop = true;
        GlobalVariables.FRAME_TYPE = "Welcome";
        GlobalVariables.USER_NAME = null;
        GlobalVariables.USER_ID = -1;
        GlobalVariables.USER_TYPE = null;

        do {
            try {
                Thread.sleep(100);
            }
            catch (Exception e) {
                System.out.println(e);
            }

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
                    viewEventsOrganizer();
                    break;
                case "CreateEvent":
                    createEvent();
                    break;
                case "InputSectorDataFrame":
                    inputSectorData();
                    break;
                case "EventDetails":
                    eventDetails();
                    break;
            }
        } while(runLoop);
    }
}



