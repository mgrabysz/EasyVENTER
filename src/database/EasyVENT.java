package database;

import userInterface.*;

public class EasyVENT {
    Database_credentials database;
    public MainMenuFrame menuframe;
    public String user_type;

    public EasyVENT(){ // Constructor
        user_type = "client";
        menuframe = new MainMenuFrame(user_type);
        database = new Database_credentials(); // create database
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
