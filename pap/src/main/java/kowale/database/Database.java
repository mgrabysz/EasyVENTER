package kowale.database;
import kowale.user.User;
import kowale.event.Event;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    /*
    Representation of database.
    */

    public HashMap<String, NewUserData> usersData = new HashMap<String, NewUserData>();
    public ArrayList<User> users = new ArrayList<>();

    public ArrayList<Event> events = new ArrayList<>();

    public Database(){ // Constructor
    }

    public boolean logIntoDatabase(String _login, String _password){
        System.out.println("LOGINNG IN:");
        // iterate through userlist
        for(int i = 0, size = users.size(); i < size; i ++)
        {
            if(users.get(i).getLogin().equals(_login)){
                if(users.get(i).getPassword().equals(_password)){
                    StringConstant.USER_TYPE = users.get(i).getType();
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

    public boolean createEvent(
        String name,
        String location,
        LocalDateTime dateTime,
        int numOfSectors
    ) {
        /*
        Adds new event to the database. Returns boolean true
        if user has been added successfully.
        */
        int id = 0;
        String organizer = "PZPN";

        Event event = new Event(id, name, organizer, location, dateTime, numOfSectors);
        events.add(event);
        return true;
    }
}
