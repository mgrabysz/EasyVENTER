package kowale.database;

import kowale.userInterface.*;
import kowale.user.*;
import javax.swing.JFrame;

public class EasyVENT {
    public static Database_credentials database;
    public WelcomeFrame menuFrame;
    public RegisterFrame registerFrame;
    public LoginFrame loginFrame;
    public CreateEventFrame createEventFrame;
    public ViewEventsFrame viewEventsFrame;

    public JFrame activeFrame;


    public String user_type;

    public EasyVENT(){ // Constructor
        database = new Database_credentials(); // create database

        // create an example client
        Client newClient = new Client("Stachu", "Jones", "sjones", "sjones");
        EasyVENT.database.register_new_user(newClient);

        // create an example organizer
        EventOrganizer newOrganizer = new EventOrganizer("Zbigniew", "Boniek", "pzpn", "pzpn");
        EasyVENT.database.register_new_user(newOrganizer);

        main_loop();
    }

    public void main_loop() {
        boolean run_loop = true;
        StringConstant.FRAME_TYPE = "welcome";
        StringConstant.USER_TYPE = null;
        String lastFrameType = "";

        do {
            //System.out.println("GLOBAL " + StringConstant.FRAME_TYPE);
            if(lastFrameType != StringConstant.FRAME_TYPE){
                switch (StringConstant.FRAME_TYPE) {
                    case "WelcomeFrame":
                        activeFrame = new WelcomeFrame();
                        lastFrameType = StringConstant.FRAME_TYPE;  // prevents from creating another window
                        break;
                    case "RegisterFrame":
                        activeFrame = new RegisterFrame();
                        lastFrameType = StringConstant.FRAME_TYPE;
                        break;
                    case "LoginFrame":
                        activeFrame = new LoginFrame();
                        lastFrameType = StringConstant.FRAME_TYPE;
                        break;
                    case "after login":
                        if (StringConstant.USER_TYPE == "client") {
                            // example data to display in table
                            String[][] data = {
                                { "Meczyk jakiś", "Firma Krzak", "Bydgoszcz", "35.19.2022 25:72" },
                                { "Ludzie biegający w kółko", "Google", "Warszafka", "48.17.2023 29:81" }
                            };
                            String[] columnNames = {"Name", "Organizer", "Location", "Date and time"};

                            viewEventsFrame = new ViewEventsFrame(data);
                            StringConstant.FRAME_TYPE = "ViewEventsFrame";
                            lastFrameType = StringConstant.FRAME_TYPE;
                        } else if (StringConstant.USER_TYPE == "organizer") {
                            createEventFrame = new CreateEventFrame();
                            StringConstant.FRAME_TYPE = "CreateEventFrame";
                            lastFrameType = StringConstant.FRAME_TYPE;
                        }
                        break;
                }
            }
        } while(run_loop);
    }
}
