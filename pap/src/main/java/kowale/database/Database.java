package kowale.database;
import kowale.user.Client;
import kowale.user.EventOrganizer;
import kowale.user.User;
import kowale.event.Event;
import kowale.ticket.Ticket;

import java.sql.Timestamp;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

// import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import kowale.database.password;

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
        // System.out.println("LOGINNG IN:");
        // iterate through userlist
        for(int i = 0, size = users.size(); i < size; i ++)
        {
            if(users.get(i).getLogin().equals(_login)){
                if(users.get(i).getPassword().equals(_password)){
                    GlobalVariables.USER_NAME = users.get(i).getName();
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
        events.add(event);

        return true;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void connectDatabase() throws SQLException {
        password password = new password();
        String pass = password.password;
        try {
            connection = DriverManager.getConnection("ora4.ii.pw.edu.pl", "z01", pass);
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
        CallableStatement pstmt = null;

        try {
            if (user.getClass().getSimpleName() == "Client"){
                pstmt = connection.prepareCall("{call register_client(?, ?, ?, ?)}");
                pstmt.setString(0, user.getLogin());
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getSurname());
//todo
            } else if (user.getClass().getSimpleName() == "Organizer") {
                pstmt = connection.prepareCall("{call register_organizer(?, ?, ?, ?)}");
                pstmt.setString(0, user.getLogin());
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getSurname());
 //todo
            }
            pstmt.execute();
            success = true;
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
        User user = null;
        if (connection != null) {
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM USERS_CREDENTIALS");
                while (rs.next()) {
                    String type = rs.getString("account_type");
                    String login = rs.getString("login");
                    String pass = rs.getString("password");
                    if (type == "C") {
                        user = new Client(login, pass);
                    } else {
                        user = new EventOrganizer(login, pass);
                    }
                    users.add(user);
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
//todo
    public boolean insertEvent(Event event) {
        return false;
    }

    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<Event>();
        Statement stmt = null;
        Event event = null;
        if (connection != null) {
            try {
                stmt = connection.createStatement();
                String query = "SELECT * FROM EVENTS "+
                "JOIN EVENT_DETAILS USING(event_detail_id)"+
                "JOIN TICKET_QUANTITY USING(ticket_quantity_id)";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    // String organizer_id = rs.getInt("organizer_id");
                    // String name = rs.getString("event_name");
                    // String location_id = rs.getInt("country_id");
                    Date start_date = rs.getDate("start_time");
                    LocalDateTime start_time = new Timestamp(start_date.getTime()).toLocalDateTime();
                    // event = new Event(name, organizer, location, start_date);
                    // events.add(event);
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
        return events;
    }
//todo
    public LinkedList<Ticket> getTicketsOfUser(User user){
        LinkedList<Ticket> tickets = new LinkedList<Ticket>();
        return tickets;
    }

    public boolean editEvent(Event event){
        return false;
    }

    public boolean dropEvent(Event event){
        return false;
    }

    public boolean buyTicket(User user, Event event){
        return false;
    }

    public boolean cancelTicket(User user, Ticket ticket){
        return false;
    }
}
