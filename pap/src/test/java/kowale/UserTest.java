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
}
