package kowale.database;

import kowale.userInterface.*;
import kowale.user.*;

import java.lang.Thread;

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
                    } else {
                        if (welcomeFrame.getOption() != "") {
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
                    break;
                case "Register":
                    if(activeFrameType != GlobalVariables.FRAME_TYPE){
                        registerFrame = new RegisterFrame();
                        activeFrameType = GlobalVariables.FRAME_TYPE;
                    } else {
                        if (registerFrame.getIsReady()) {
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
                        }
                    }
                    break;
                case "Login":
                    if(activeFrameType != GlobalVariables.FRAME_TYPE){
                        loginFrame = new LoginFrame();
                        activeFrameType = GlobalVariables.FRAME_TYPE;
                    } else {
                        if (loginFrame.getIsReady()) {
                            GlobalVariables.FRAME_TYPE = "MainMenu";
                            loginFrame.dispose();
                            loginFrame = null;
                        }
                    }
                    break;
                case "MainMenu":
                    if(activeFrameType != GlobalVariables.FRAME_TYPE){
                        mainMenuFrame = new MainMenuFrame(GlobalVariables.USER_TYPE);
                        activeFrameType = GlobalVariables.FRAME_TYPE;  // prevents from creating another window
                    } else {
                        if (mainMenuFrame.getOption() != "") {
                            switch (mainMenuFrame.getOption()) {
                                case "logout":
                                    GlobalVariables.USER_TYPE = null;
                                    GlobalVariables.FRAME_TYPE = "Welcome";
                                    break;
                                case "manageEvents":
                                    GlobalVariables.FRAME_TYPE = "ViewEventsOrganizer";
                                    break;
                                case "createEvent":
                                    GlobalVariables.FRAME_TYPE = "CreateEvent";
                                    break;
                                case "viewEvents":
                                    GlobalVariables.FRAME_TYPE = "viewEvents";
                                    break;
                                case "manageTickets":
                                    // GlobalVariables.FRAME_TYPE = "ManageTickets";
                                    break;
                            }
                            mainMenuFrame.dispose();
                            mainMenuFrame = null;
                        }
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
                    } else {
                        // System.out.println("else");
                        // if (loginFrame.getIsReady()) {
                        //     GlobalVariables.FRAME_TYPE = "MainMenu";
                        //     loginFrame.dispose();
                        //     loginFrame = null;
                        // }
                    }
                    break;
                // case "after login":
                //     if (GlobalVariables.USER_TYPE == "client") {
                //         // example data to display in table
                //         String[][] data = {
                //             { "Meczyk jakiś", "Firma Krzak", "Bydgoszcz", "35.19.2022 25:72" },
                //             { "Ludzie biegający w kółko", "Google", "Warszafka", "48.17.2023 29:81" }
                //         };
                //         String[] columnNames = {"Name", "Organizer", "Location", "Date and time"};

                //         //tutaj powinno byc uzyte database.getEvents()

                //         viewEventsFrame = new ViewEventsFrame(data);
                //         GlobalVariables.FRAME_TYPE = "ViewEventsFrame";
                //         activeFrameType = GlobalVariables.FRAME_TYPE;
                //     } else if (GlobalVariables.USER_TYPE == "organizer") {
                //         createEventFrame = new CreateEventFrame();
                //         GlobalVariables.FRAME_TYPE = "CreateEventFrame";
                //         activeFrameType = GlobalVariables.FRAME_TYPE;
                //     }
                //     break;
                case "InputSectorDataFrame":
                    int number = GlobalVariables.SECTORS_NUMBER;
                    String[][] sectors = new String[number][3];
                    for (int i=0; i<number; ++i) {
                        String iStr = String.valueOf(i+1);
                        String[] sector = {iStr, "0", "0", "0"};
                        sectors[i] = sector;
                    }
                    inputSectorDataFrame = new InputSectorDataFrame(sectors);
                    activeFrameType = GlobalVariables.FRAME_TYPE;

            }
        } while(runLoop);
    }
}



