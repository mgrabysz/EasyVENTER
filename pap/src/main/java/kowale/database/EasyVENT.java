package kowale.database;

import kowale.userInterface.*;
import kowale.user.*;

public class EasyVENT {
    public static Database_credentials database;
    public WelcomeFrame menuFrame;
    public RegisterFrame registerFrame;
    public LoginFrame loginFrame;
    public CreateEventFrame createEventFrame;
    public ViewEventsFrame viewEventsFrame;


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

    public void main_loop(){
        boolean run_loop = true;
        StringConstant.FRAME_TYPE = "welcome";
        StringConstant.USER_TYPE = "a";
        String lastFrame = "";

        do{
            //System.out.println("GLOBAL " + StringConstant.FRAME_TYPE);
            if(lastFrame != StringConstant.FRAME_TYPE){

                switch (StringConstant.FRAME_TYPE) {
                    case "welcome":
                        menuFrame = new WelcomeFrame();
                        lastFrame = "welcome";  // prevents from creating another window
                        break;
                    case "register":
                        menuFrame.dispose();  // manually destroy menuFrame window
                        registerFrame = new RegisterFrame();
                        lastFrame = "register";
                        break;
                    case "login":
                        menuFrame.dispose();  // manually destroy menuFrame window
                        loginFrame = new LoginFrame();
                        lastFrame = "login";
                        break;
                    case "after login":
                        menuFrame.dispose();  // manually destroy loginFrame window
                        if (StringConstant.USER_TYPE == "client") {

                            // example data to display in table
                            String[][] data = {
                                { "Meczyk jakiś", "Firma Krzak", "Bydgoszcz", "35.19.2022 25:72" },
                                { "Ludzie biegający w kółko", "Google", "Warszafka", "48.17.2023 29:81" }
                            };
                            String[] columnNames = {"Name", "Organizer", "Location", "Date and time"};

                            viewEventsFrame = new ViewEventsFrame(data, columnNames);

                            StringConstant.FRAME_TYPE = "view events";
                            lastFrame = StringConstant.FRAME_TYPE;
                        } else if (StringConstant.USER_TYPE == "organizer") {
                            createEventFrame = new CreateEventFrame();
                            StringConstant.FRAME_TYPE = "create event";
                            lastFrame = StringConstant.FRAME_TYPE;
                        }
                        break;
                }
            }
        }while(run_loop);
    }
}
