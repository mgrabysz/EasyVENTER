package database;

import userInterface.*;

public class EasyVENT {
    public static Database_credentials database;
    public WelcomeFrame menuframe;
    public RegisterFrame registerframe;
    public LoginFrame loginframe;

    public String user_type;

    public EasyVENT(){ // Constructor
        user_type = "organizer";
        database = new Database_credentials(); // create database

        main_loop();
    }

    public void main_loop(){
        boolean run_loop = true;
        StringConstant.FRAME_TYPE = "welcome";
        String last_frame = "";
        String he = "he";

        do{
            //System.out.println("GLOBAL " + StringConstant.FRAME_TYPE);
            if(last_frame != StringConstant.FRAME_TYPE){

                switch (StringConstant.FRAME_TYPE) {
                    case "welcome":
                        menuframe = new WelcomeFrame();
                        last_frame = "welcome";  // prevents from creating another window
                        break;
                    case "register":
                        menuframe.dispose();  // manually destroy menuframe window
                        registerframe = new RegisterFrame();
                        last_frame = "register";
                        break;
                    case "login":
                        menuframe.dispose();  // manually destroy menuframe window
                        loginframe = new LoginFrame();
                        last_frame = "login";
                        break;
                }
            }
        }while(run_loop);
    }
}
