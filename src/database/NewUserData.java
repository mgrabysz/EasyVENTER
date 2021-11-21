package database;

public class NewUserData {
    /*
    Class used to aggregate user's data like:
    login, password, account type.
     */
    public Character account_type;
    public String login;
    public String password;

    public NewUserData(String _login, String _password, Character _account_type) {
        this.account_type = _account_type;
        this.login = _login;
        this.password = _password;
    }

    @Override
    public String toString() {
        String user_data = "Login: " + login + "\nAccount type: " + account_type;
        return user_data;
    }
}
