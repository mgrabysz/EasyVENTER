package kowale.user;



public class User {
    private String name;
    private String surname;
    private String login;
    private String password;
    protected String type;

    public User(
        String name, String surname,
        String login, String pass
    ){
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = pass;
    }

    public String getLogin(){
        return this.login;
    }
    public String getPassword(){
        return this.password;
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getType(){
        return type;
    }
}
