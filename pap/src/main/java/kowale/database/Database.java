package kowale.database;
import kowale.user.User;
import kowale.event.Event;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Database {
    /*
    Representation of database.
    */

    public HashMap<String, NewUserData> usersData = new HashMap<String, NewUserData>();
    public ArrayList<User> users = new ArrayList<>();
    public Connection connection = null;
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
                    GlobalVariables.USER_TYPE = users.get(i).getType();
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

    public boolean createEvent(Event event) {
        /*
        Adds new event to the database. Returns boolean true
        if user has been added successfully.
        */
        String organizer = "PZPN";

        event.setOrganizer(organizer);
        events.add(event);

        return true;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void connectDatabase() throws SQLException {
        String password = "";
        // TODO
        try {
            connection = DriverManager.getConnection("ora4.ii.pw.edu.pl", "z01", password);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void closeDatabase() throws SQLException {
        try {
            connection.close();
            connection = null;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public boolean registerUser(User user) throws SQLException{
        if (connection == null){
            return false;
        }
        boolean success;
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?)");
            pstmt.setString(0, user.getName());
// TODO
            success = (pstmt.executeUpdate() == 1);
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
                return false;
            }
        }
        return success;
    }

    public LinkedList<User> getAllUsersCredentials(){
        LinkedList<User> users = new LinkedList<User>();
        Statement stmt = null;
        if (connection != null) {
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM users_credentials");
                while (rs.next()) {
                    // TODO
                    User user;
                    // TODO
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                if (stmt != null){
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }
        return users;
    }

}
