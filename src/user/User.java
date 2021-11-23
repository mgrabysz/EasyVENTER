package user;



public class User {
    public int user_id;
    public String name;
    public String surname;
    private String login;
    private String password;
    public User(
        int id, String name, String surname,
        String login, String pass
    )
    {
        this.user_id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = pass;
    }
    public String getLogin()
    {
        return this.login;
    }
    public String getPassword()
    {
        return this.password;
    }
}
