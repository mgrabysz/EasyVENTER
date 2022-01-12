package kowale;

import kowale.user.User;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest{
    User user = new User("John", "Rambo", "johnny", "1234", "email@gmail.com", 666666666);

    @Test
    public void getLoginTest(){
        assertEquals("johnny", user.getLogin());
    }

    @Test
    public void getPasswordTest(){
        assertEquals("1234", user.getPassword());
    }

    @Test
    public void getNameTest(){
        assertEquals("John", user.getName());
    }

    @Test
    public void getSurnameTest(){
        assertEquals("Rambo", user.getSurname());
    }

    @Test
    public void getEmailTest(){
        assertEquals("email@gmail.com", user.getEmail());
    }

    @Test
    public void getPhoneTest(){
        assertEquals(666666666, user.getPhoneNumber());
    }

    @Test
    public void constructorOnlyWithLoginTest(){
        User user2 = new User("login", "haslo");
        assertEquals("login", user2.getLogin());
        assertEquals("haslo", user2.getPassword());
    }

}
