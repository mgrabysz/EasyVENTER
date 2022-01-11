package kowale.database;
import kowale.user.Client;
import kowale.user.EventOrganizer;
import kowale.user.User;
import kowale.event.Event;
import kowale.ticket.Ticket;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
// import java.util.Objects;
import java.util.Map;

import javax.sound.midi.SysexMessage;
import javax.swing.plaf.synth.SynthSliderUI;
import javax.swing.text.DefaultEditorKit.CopyAction;

// import kowale.database.password;

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
            // System.out.println(users.get(i).getType());
            if(users.get(i).getLogin().equals(_login)) {
                // System.out.println(_password);
                // System.out.println(users.get(i).getPassword());

                if(users.get(i).getPassword().equals(_password)) {
                    GlobalVariables.USER_LOGIN = users.get(i).getLogin();
                    GlobalVariables.USER_TYPE = users.get(i).getType();
                    // System.out.println(users.get(i).getName());
                    // System.out.println(users.get(i).getType());
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
                    // System.out.println(type);
                    String login = rs.getString("login");
                    String pass = rs.getString("password");
                    if (type.equals("C")) {
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
        // returns events with tickets available for purchase
        ArrayList<Event> events = new ArrayList<Event>();
        Statement stmt = null;
        Event event = null;
        if (connection != null) {
            try {
                stmt = connection.createStatement();
                String query = "SELECT companies.name as company_name, event_name, country_name, cities.name as city_name, street, start_time FROM events JOIN event_details USING (event_id) "+
                    "JOIN addresses USING(address_id) JOIN cities USING(city_id) "+
                    "JOIN countries USING(country_id) JOIN organizer_data USING(organizer_id) "+
                    "JOIN companies USING(company_id)";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String organizer = rs.getString("company_name");
                    String name = rs.getString("event_name");
                    String country = rs.getString("country_name");
                    String city = rs.getString("city_name");
                    String address = rs.getString("street");
                    Date start_date = rs.getDate("start_time");
                    LocalDateTime start_time = new Timestamp(start_date.getTime()).toLocalDateTime();
                    event = new Event(name, organizer, country, city, address, start_time);
                    events.add(event);
                }
                for (int i=0; i< events.size(); ++i){
                    ArrayList<Ticket> tickets = new ArrayList<Ticket>();
                    String ev_name = events.get(i).getName();
                    String qu = "SELECT sector, seat, ticket_price FROM tickets "+
                        "JOIN events USING(event_id) "+
                        "LEFT JOIN ticket_orders ON(tickets.ticket_id=ticket_orders.ticket_id) "+
                        "WHERE event_name = ? "+
                        "AND tickets.ticket_id NOT IN(SELECT ticket_id FROM ticket_orders)";
                    PreparedStatement pstmt = connection.prepareStatement(qu);
                    pstmt.setString(1, ev_name);
                    ResultSet rs2 = pstmt.executeQuery();
                    while (rs2.next()) {
                        String sector = rs2.getString("sector");
                        int seat = Integer.parseInt(rs2.getString("seat"));
                        int price = rs2.getInt("ticket_price");
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

    public ArrayList<Ticket> getTicketsOfUser(String login, Event event){
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        Statement stmt = null;
        String ev_name = event.getName();
        if (connection != null) {
            try {
                stmt = connection.createStatement();
                String query = String.format("SELECT * FROM tickets JOIN ticket_orders USING(ticket_id)"+
                    "JOIN ticket_categories USING(category_id)"+
                    "JOIN client_orders USING(order_id) JOIN user_credentials ON (client_id = user_id)"+
                    "JOIN events USING(event_id) WHERE login = '%s' AND event_name = '%s'", login, ev_name);
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String sector = rs.getString("sector");
                    int seat = Integer.parseInt(rs.getString("seat"));
                    int price = rs.getInt("ticket_price");
                    String category = rs.getString("ategory_name");
                    Ticket ticket = new Ticket(sector, seat, price);
                    ticket.setCategory(category);
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

    public ArrayList<Event> getEventsOfUser(String login){
        ArrayList<Event> events = new ArrayList<Event>();
        Statement stmt = null;
        if (connection != null) {
            try {
                stmt = connection.createStatement();
                String query = String.format(
                "SELECT companies.name as comp_name, cities.name as city_name, event_name, country_name, street, start_time "+
                "FROM user_credentials "+
                "JOIN client_data ON(user_id=client_id) "+
                "JOIN client_orders USING(client_id) "+
                "JOIN ticket_orders USING(order_id) "+
                "JOIN tickets USING(ticket_id) "+
                "JOIN events ON(events.event_id=tickets.event_id) "+
                "JOIN event_details ON(events.event_id = event_details.event_id) "+
                "JOIN organizer_data USING(organizer_id) "+
                "JOIN companies USING(company_id) "+
                "JOIN addresses USING(address_id) "+
                "JOIN cities USING(city_id) "+
                "JOIN countries USING(country_id) WHERE login = '%s' ", login);
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {

                    String organizer = rs.getString("comp_name");
                    String name = rs.getString("event_name");
                    String country = rs.getString("country_name");
                    String city = rs.getString("city_name");
                    String address = rs.getString("street");
                    Date start_date = rs.getDate("start_time");

                    LocalDateTime start_time = new Timestamp(start_date.getTime()).toLocalDateTime();
                    Event event = new Event(name, organizer, country, city, address, start_time);
                    events.add(event);
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

    public ArrayList<Event> getEventsOfOrganizer(String login){
        ArrayList<Event> events = new ArrayList<Event>();
        Statement stmt = null;
        if (connection != null) {
            try {
                stmt = connection.createStatement();
                String query = String.format(
                "SELECT companies.name as comp_name, cities.name as city_name, event_name, country_name, street, start_time "+
                "FROM user_credentials "+
                "JOIN organizer_data ON(user_id=organizer_id) "+
                "JOIN events USING(organizer_id) "+
                "JOIN event_details ON(events.event_id = event_details.event_id) "+
                "JOIN companies USING(company_id) "+
                "JOIN addresses USING(address_id) "+
                "JOIN cities USING(city_id) "+
                "JOIN countries USING(country_id) WHERE login = '%s' ", login);
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {

                    String organizer = rs.getString("comp_name");
                    String name = rs.getString("event_name");
                    String country = rs.getString("country_name");
                    String city = rs.getString("city_name");
                    String address = rs.getString("street");
                    Date start_date = rs.getDate("start_time");

                    LocalDateTime start_time = new Timestamp(start_date.getTime()).toLocalDateTime();
                    Event event = new Event(name, organizer, country, city, address, start_time);
                    events.add(event);
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

    public ArrayList<Event> getEventsButNotOfUser(String login) {
        /*
         * Returns list of events available for this user
         * (All events for which user does not have tickets yet)
        */
        ArrayList<Event> events = getAllEvents();
        ArrayList<Event> userEvents = getEventsOfUser(login);

        for (Event event : userEvents) {
            String name = event.getName();

            for (int i=0; i<events.size(); ++i) {

                Event temp = events.get(i);

                if (temp.getName().equals(name)) {
                    events.remove(i);
                    break;
                }
            }
        }

        return events;
    }

    public boolean insertEvent(Event event, String organizerLogin) {
        /*
        Pass Event object and Ticket array
        */

        CallableStatement cs = null;
        if (connection != null) {
            try {
                String eventName = event.getName();
                String eventCountry = event.getCountry();
                String eventCity = event.getCity();
                String eventAddress = event.getAddress();
                LocalDateTime eventDate = event.getDateTime();
                Date eventDateSQL = Date.valueOf(eventDate.toLocalDate());

                cs = connection.prepareCall("{call ADD_EVENT(?,?,?,?,?,?,?,?)}");
                cs.setString(1, eventName);
                cs.setString(2, organizerLogin);
                cs.setString(3, eventCountry);
                cs.setString(4, eventCity);
                cs.setString(5, eventAddress);
                cs.setDate(6, eventDateSQL);
                cs.registerOutParameter(7, Types.NUMERIC);
                cs.registerOutParameter(8, Types.NUMERIC);
                cs.execute();
                int eventID = cs.getInt(7);
                int eventDetailID = cs.getInt(8);

                /* INSERT TICKET QUANTITIES */
                if(this.insertTicketQuantities(eventDetailID, event.getTickets())){
                    /* INSERT TICKETS */
                    this.insertTickets(eventID, event.getTickets());
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                if (cs != null){
                    try {
                        cs.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }

        return true;
    }

    public boolean insertTicketQuantities(int eventDetailID, ArrayList<Ticket> tickets){
        /* Count occurences of tickets in each sector */
        Map<String,Integer> hm = new HashMap();
        for(Ticket ticket: tickets){
            if(!hm.containsKey(ticket.getSector())){
                hm.put(ticket.getSector(),1);
            }else{
                hm.put(ticket.getSector(), hm.get(ticket.getSector())+1);
            }
        }

        int sectorPrice = 0;
        int sectorOccurences;
        // Iterate through keys = Sector values
        for (String uniqueSector : hm.keySet()) {
            sectorOccurences = hm.get(uniqueSector);
            // Get price of ticket in that sector
            // DOESNT WORK ON MY JAVA EDITION // Arrays.stream(tickets).map(r->r.getSector());
            for (Ticket ticket : tickets) {
                if(ticket.getSector() == uniqueSector){
                    sectorPrice = ticket.getPrice();
                    break;
                }
            }
            if(sectorPrice != 0){  // Make sure that sector price has been assigned
                // DO SQL LOGIC
                CallableStatement cs = null;

                if (connection != null) {
                    try {
                        cs = connection.prepareCall("{call ADD_TICKET_QUANTITY(?,?,?,?)}");
                        cs.setInt(1, eventDetailID);
                        cs.setString(2, uniqueSector);
                        cs.setInt(3, sectorOccurences);
                        cs.setInt(4, sectorPrice);
                        cs.execute();

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    } finally {
                        if (cs != null){
                            try {
                                cs.close();
                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean insertTickets(int eventID, ArrayList<Ticket> tickets){
        CallableStatement cs = null;
        if (connection != null) {
            try {
                for (Ticket ticket : tickets){
                    int tSeat = ticket.getSeat();
                    String tSector = ticket.getSector();
                    int tPrice = ticket.getPrice();

                    cs = connection.prepareCall("{call ADD_TICKET(?,?,?,?)}");
                    cs.setInt(1, eventID);
                    cs.setString(2, String.valueOf(tSeat));
                    cs.setString(3, String.valueOf(tSector)); // convert to DB char type
                    cs.setInt(4, tPrice);

                    cs.executeQuery();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                if (cs != null){
                    try {
                        cs.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }

        return true;
    }

    public boolean cancelEvent(Event event){
        /*
        Pass Event object and Ticket array
        To cancel an event, one need its name, since it is an unique field in the db.
        */

        CallableStatement cs = null;
        if (connection != null) {
            try {
                String eventName = event.getName();
                cs = connection.prepareCall("{call CANCEL_EVENT(?)}");
                cs.setString(1, eventName);
                cs.execute();
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                if (cs != null){
                    try {
                        cs.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }
        return true;
    }

    public boolean editEvent(Event event){
        /*
        Pass Event object and Ticket array
        */

        CallableStatement cs = null;
        if (connection != null) {
            try {
                String eventName = event.getName();
                String eventCountry = event.getCountry();
                String eventCity = event.getCity();
                String eventAddress = event.getAddress();
                LocalDateTime eventDate = event.getDateTime();
                Date eventDateSQL = Date.valueOf(eventDate.toLocalDate());

                cs = connection.prepareCall("{call EDIT_EVENT(?,?,?,?,?)}");
                cs.setString(1, eventName);
                cs.setString(2, eventCountry);
                cs.setString(3, eventCity);
                cs.setString(4, eventAddress);
                cs.setDate(5, eventDateSQL);
                cs.execute();
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                if (cs != null){
                    try {
                        cs.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }

        return true;
    }

    public boolean buyTickets(Event event, String userLogin, ArrayList<Ticket> tickets){
        CallableStatement cs = null;
        int orderID = -1; // initialize variable with false value
        for(Ticket ticket: tickets){
            if (connection != null) {
                try {
                    String eventName = event.getName();
                    String ticketSector = ticket.getSector();
                    String ticketCategory = ticket.getCategory();
                    int ticketSeat = ticket.getSeat();
                    cs = connection.prepareCall("{call BUY_TICKET(?,?,?,?,?,?,?)}");
                    cs.setString(1, eventName);
                    cs.setString(2, userLogin);
                    cs.setString(3, ticketCategory);
                    cs.setString(4, ticketSector);
                    cs.setString(5, String.valueOf(ticketSeat));
                    cs.setInt(6, orderID);
                    cs.registerOutParameter(7, Types.NUMERIC);
                    cs.execute();

                    orderID = cs.getInt(7);  // update value of orderID

                } catch (SQLException ex) {
                    System.out.println(ex);
                } finally {
                    if (cs != null){
                        try {
                            cs.close();
                        } catch (SQLException ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean cancelTicket(String event_name, Ticket ticket){
        CallableStatement stmt = null;
        Boolean succes = false;
        if (connection != null) {
            try {
                stmt = connection.prepareCall("{call REMOVE_TICKET(?,?,?)}");
                stmt.setString(1, event_name);
                stmt.setString(2, ticket.getSector());
                stmt.setInt(3, ticket.getSeat());
                stmt.execute();
                succes = true;
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                if (stmt != null){
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        return false;
                    }
                }
            }
        }
        return succes;
    }

    public String getCompanyName(String login){
        CallableStatement stmt = null;
        String company = "";
        if (connection != null) {
            try {
                stmt = connection.prepareCall("{? = call get_company(?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(2, login);
                stmt.execute();
                company = stmt.getString(1);
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
        return company;
    }
}
