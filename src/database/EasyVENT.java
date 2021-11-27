package database;

import userInterface.*;

public class EasyVENT {
    Database_credentials database;
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

        while(run_loop){
            System.out.println("GLOBAL " + StringConstant.FRAME_TYPE);
            System.out.println(last_frame);

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
        }
    }

    public boolean log_in(NewUserData data){
        NewUserData response = database.logIntoDatabase(data.login, data.password);
        if(response != null){
            System.out.println("User was logged in successfully");
            return true;
        }
        else{
            System.out.println("User was logged in unsuccessfully");
            return false;
        }
    }

    public boolean create_new_user(NewUserData data){
        boolean response = database.register_new_user(data);

        if(response){
            System.out.println("User was registered successfully");
            return true;
        }
        else{
            System.out.println("User was not registered!");
            return false;
        }
    }
}
