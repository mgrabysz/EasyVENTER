package kowale.user;



public class User {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private int phoneNumber;
    protected String type;

    public User(
        String name, String surname,
        String login, String password,
        String email, int phone
    ){
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phone;
    }

    public User(
        String login, String password
    ){
        this.login = login;
        this.password = password;
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
    public String getEmail(){
        return this.email;
    }
    public int getPhoneNumber(){
        return this.phoneNumber;
    }
}
