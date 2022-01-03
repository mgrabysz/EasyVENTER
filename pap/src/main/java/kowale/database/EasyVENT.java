package kowale.database;

import kowale.userInterface.*;
import kowale.user.*;
import kowale.event.Event;

import java.lang.Thread;

import javax.swing.JOptionPane;

// import java.awt.Frame;
// import javax.swing.JFrame;

public class EasyVENT {
    public static Database database;

    public WelcomeFrame welcomeFrame;
    public RegisterFrame registerFrame;
    public LoginFrame loginFrame;
    public MainMenuFrame mainMenuFrame;
    public CreateEventFrame createEventFrame;
    public ViewEventsOrganizerFrame viewEventsOrganizerFrame;
    public ViewEventsFrame viewEventsFrame;
    public InputSectorDataFrame inputSectorDataFrame;
    public EventDetailsFrame eventDetailsFrame;

    public String user_type;
    // public JFrame activeFrame;

    public EasyVENT(){ // Constructor
        database = new Database(); // create database

        // create an example client
        Client newClient = new Client("Stachu", "Jones", "sjones", "sjones");
        EasyVENT.database.register_new_user(newClient);
        newClient = new Client("a", "a", "a", "a");
        EasyVENT.database.register_new_user(newClient);

        // create an example organizer
        EventOrganizer newOrganizer = new EventOrganizer("Zbigniew", "Boniek", "pzpn", "pzpn");
        EasyVENT.database.register_new_user(newOrganizer);
        newOrganizer = new EventOrganizer("s", "s", "s", "s");
        EasyVENT.database.register_new_user(newOrganizer);

        mainLoop();
    }

    public void mainLoop() {
        boolean runLoop = true;
        GlobalVariables.FRAME_TYPE = "Welcome";
        String activeFrameType = "";
        GlobalVariables.USER_TYPE = null;

        do {
            try {
                Thread.sleep(50);
            }
            catch (Exception e) {
                System.out.println(e);
            }

            switch (GlobalVariables.FRAME_TYPE) {
                case "Welcome":
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
                    break;
                case "Register":
                    if(activeFrameType != GlobalVariables.FRAME_TYPE){
                        registerFrame = new RegisterFrame();
                        activeFrameType = GlobalVariables.FRAME_TYPE;
                    } else if (registerFrame.getIsReady()) {
                        // check if user input is correct
                        System.out.println("Ready");
                        
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
                                    registerFrame.getUserPassword()
                                );
                                EasyVENT.database.register_new_user(new_user);
                            } else {
                                EventOrganizer new_user = new EventOrganizer(
                                    registerFrame.getUserName(),
                                    registerFrame.getUserSurname(),
                                    registerFrame.getUserLogin(),
                                    registerFrame.getUserPassword()
                                );
                                EasyVENT.database.register_new_user(new_user);
                            }
    
                            GlobalVariables.FRAME_TYPE = "Welcome";
                            registerFrame.dispose();
                            registerFrame = null;
                        } else {
                            registerFrame.setIsReady(false);

                            System.out.println("Not correct");
                            JOptionPane.showMessageDialog(
                                null,
                                "Invalid value in one or more fields.",
                                "Invalid user input",
                                JOptionPane.ERROR_MESSAGE    // ads red "x" picture
                            );
                        }
                        
                    }
                    break;
                case "Login":
                    if(activeFrameType != GlobalVariables.FRAME_TYPE){
                        loginFrame = new LoginFrame();
                        activeFrameType = GlobalVariables.FRAME_TYPE;
                    } else if (loginFrame.getIsReady()) {
                        // check if user input is correct
                        if (
                            EasyVENT.database.logIntoDatabase(
                                loginFrame.getUserLogin(),
                                loginFrame.getUserPassword()
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
                    break;
                case "MainMenu":
                    if(activeFrameType != GlobalVariables.FRAME_TYPE){
                        mainMenuFrame = new MainMenuFrame(GlobalVariables.USER_TYPE);
                        activeFrameType = GlobalVariables.FRAME_TYPE;  // prevents from creating another window
                    } else if (mainMenuFrame.getOption() != "") {
                        switch (mainMenuFrame.getOption()) {
                            case "logout":
                                GlobalVariables.USER_TYPE = null;
                                GlobalVariables.FRAME_TYPE = "Welcome";
                                break;
                            case "viewEvents":
                                GlobalVariables.FRAME_TYPE = "ViewEvents";
                                break;
                            case "manageEvents":
                                GlobalVariables.FRAME_TYPE = "ViewEventsOrganizer";
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
                    break;
                case "ViewEventsOrganizer":
                    if(activeFrameType != GlobalVariables.FRAME_TYPE){
                        String[][] data = {
                            { "Meczyk jakiś", "Firma Krzak", "Bydgoszcz", "35.19.2022 25:72" },
                            { "Ludzie biegający w kółko", "Google", "Warszafka", "48.17.2023 29:81" }
                        };
                        viewEventsOrganizerFrame = new ViewEventsOrganizerFrame(data);
                        activeFrameType = GlobalVariables.FRAME_TYPE;
                    } else if (viewEventsOrganizerFrame.getOption() != "") {
                        switch (viewEventsOrganizerFrame.getOption()) {
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
                        viewEventsOrganizerFrame.dispose();
                        viewEventsOrganizerFrame = null;
                    }
                    break;
                case "CreateEvent":
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
                                    null,
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
                    break;
                case "InputSectorDataFrame":
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
                    break;
                case "ViewEvents":
                    if(activeFrameType != GlobalVariables.FRAME_TYPE){
                        String[][] data = {
                            { "Meczyk jakiś", "Firma Krzak", "Bydgoszcz", "35.19.2022 25:72" },
                            { "Ludzie biegający w kółko", "Google", "Warszafka", "48.17.2023 29:81" }
                        };
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
                    break;
                case "EventDetails":
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
                    break;
            }
        } while(runLoop);
    }
}



