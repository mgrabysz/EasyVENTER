package kowale.database;

import kowale.userInterface.*;

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
                            viewEventsFrame = new ViewEventsFrame();
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
