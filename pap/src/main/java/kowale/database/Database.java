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
// import java.util.Objects;

import kowale.database.password;

public class Database {
    /*
    Representation of database.
    */

    public HashMap<String, NewUserData> usersData = new HashMap<String, NewUserData>();
    public ArrayList<User> users = new ArrayList<>();
    public Connection connection = null;
    public ArrayList<Event> events = new ArrayList<>();

    public Database() throws SQLException { // Constructor
        connectDatabase();
    }

    public boolean logIntoDatabase(String _login, String _password) {
        // System.out.println("LOGINNG IN:");
        // iterate through userlist
        LinkedList<User> users = getAllUsersCredentials();
        for(int i = 0, size = users.size(); i < size; i ++) {
            if(users.get(i).getLogin().equals(_login)) {
                // System.out.println(_password);
                // System.out.println(users.get(i).getPassword());

                if(users.get(i).getPassword().equals(_password)) {
                    GlobalVariables.USER_NAME = users.get(i).getName();
                    GlobalVariables.USER_TYPE = users.get(i).getType();
                    return true;
                }
            }
        }
        return false;
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
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@//ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
            connection = DriverManager.getConnection(url, "z01", pass);
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException cnf){
            System.out.println(cnf);
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

    public boolean registerUser(User user) {
        /*
        Adds new user to the database. Returns boolean true
        if user has been added successfully.
         */
        String userType = user.getClass().getSimpleName().toString();
        // System.out.println(userType);

        if (connection == null){
            return false;
        }
        boolean success = false;
        CallableStatement pstmt = null;
        try {
            if (userType.equals("Client")){
                // System.out.println("IF Client");
                Client cuser = (Client)user;
                pstmt = connection.prepareCall("{call register_client(?, ?, ?, ?, ?, ?, ?, ?)}");
                pstmt.setString(1, cuser.getLogin());
                pstmt.setString(2, cuser.getPassword());
                pstmt.setString(3, cuser.getName());
                pstmt.setString(4, cuser.getSurname());
                pstmt.setString(5, cuser.getEmail());
                pstmt.setDate(6, Date.valueOf(cuser.getBirth()));
                pstmt.setString(7, cuser.getGender());
                pstmt.setInt(8, cuser.getPhoneNumber());
                // System.out.println("IF Client END");
            } else if (userType.equals("EventOrganizer")) {
                EventOrganizer euser = (EventOrganizer)user;
                pstmt = connection.prepareCall("{call register_organizer(?, ?, ?, ?, ?, ?, ?)}");
                pstmt.setString(1, euser.getLogin());
                pstmt.setString(2, euser.getPassword());
                pstmt.setString(3, euser.getName());
                pstmt.setString(4, euser.getSurname());
                pstmt.setString(5, euser.getEmail());
                pstmt.setInt(6, euser.getPhoneNumber());
                pstmt.setString(7, euser.getCompanyName());
            }
            pstmt.execute();
            success = true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } finally {
            try {
                // System.out.println(success);
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
                ResultSet rs = stmt.executeQuery("SELECT * FROM USER_CREDENTIALS");
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


    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<Event>();
        Statement stmt = null;
        Event event = null;
        if (connection != null) {
            try {
                stmt = connection.createStatement();
                //todo
                String query = "";

                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String organizer = rs.getString("companies.name");
                    String name = rs.getString("event_name");
                    String country = rs.getString("country_name");
                    String city = rs.getString("cities.name");
                    String address = rs.getString("street");
                    Date start_date = rs.getDate("start_time");
                    LocalDateTime start_time = new Timestamp(start_date.getTime()).toLocalDateTime();
                    event = new Event(name, organizer, country, city, address, start_time);
                    events.add(event);
                }
                for (int i=0; i< events.size(); ++i){
                    ArrayList<Ticket> tickets = new ArrayList<Ticket>();
                    String ev_name = events.get(i).getName();
                    String query2 = String.format("SELECT * FROM tickets JOIN events USING(event_id) WHERE event_name = %s", ev_name);
                    ResultSet rs2 = stmt.executeQuery(query2);
                    while (rs2.next()) {
                        String sector = rs.getString("sector");
                        int seat = Integer.parseInt(rs.getString("seat"));
                        int price = rs.getInt("ticket_price");
                        Ticket ticket = new Ticket(sector, seat, price);
                        tickets.add(ticket);
                    }
                    events.get(i).setTickets(tickets);
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

    public LinkedList<Ticket> getTicketsOfUser(User user){
        LinkedList<Ticket> tickets = new LinkedList<Ticket>();
        Statement stmt = null;
        String login = user.getLogin();
        if (connection != null) {
            try {
                stmt = connection.createStatement();
                String query = String.format("SELECT * FROM tickets JOIN ticket_orders USING(ticket_id)"+
                    "JOIN client_orders USING(order_id) JOIN user_credentials ON (client_id = user_id)"+
                    "WHERE login = %s", login);
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String sector = rs.getString("sector");
                    int seat = Integer.parseInt(rs.getString("seat"));
                    int price = rs.getInt("ticket_price");
                    Ticket ticket = new Ticket(sector, seat, price);
                    tickets.add(ticket);
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
        return tickets;
    }

    public boolean insertEvent(Event event) {
        return false;
    }

    public boolean editEvent(Event event){
        return false;
    }

    public boolean buyTicket(User user, Event event){
        return false;
    }

    public boolean cancelTicket(User user, Ticket ticket){
        return false;
    }
}
