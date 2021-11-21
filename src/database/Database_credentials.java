package database;
import java.util.HashMap;

public class Database_credentials {
    /*
    Representation of database that holds user login data
    and the rest of information regarding their account.
     */

    public HashMap<String, NewUserData> usersData = new HashMap<String, NewUserData>();
    Integer current_index;
    public Database_credentials(){ // Constructor
        current_index = 0; // set id of a next user to 0
    }

    public NewUserData logIntoDatabase(String _login, String _password){
        if(usersData.containsKey(_login)){  // if database contain user of login
            NewUserData dataOfUser = usersData.get(_login);
            if(dataOfUser.password.equals(_password)){  // credentials verified
                return dataOfUser;  // return data of user
            }
        }
        return null;  // return nothing
    }

    public boolean register_new_user(NewUserData new_user_data){
        /*
        Adds new user to the database. Returns boolean true
        if user has been added successfully.
         */
        usersData.put(new_user_data.login, new_user_data);
        return true;
    }
}
