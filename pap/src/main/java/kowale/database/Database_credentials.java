package kowale.database;
import kowale.user.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Database_credentials {
    /*
    Representation of database that holds user login data
    and the rest of information regarding their account.
     */

    public HashMap<String, NewUserData> usersData = new HashMap<String, NewUserData>();
    public ArrayList<User> users = new ArrayList<>();

    public Database_credentials(){ // Constructor
    }

    public boolean logIntoDatabase(String _login, String _password){
        System.out.println("LOGINNG IN:");
        // iterate through userlist
        for(int i = 0, size = users.size(); i < size; i ++)
        {
            if(users.get(i).getLogin().equals(_login)){
                if(users.get(i).getPassword().equals(_password)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean register_new_user(User new_user){
        /*
        Adds new user to the database. Returns boolean true
        if user has been added successfully.
         */
        users.add(new_user);
        return true;
    }
}
