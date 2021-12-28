package kowale;

import kowale.user.User;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest
{
    @Test
    public void getIDTest()
    {
        User user = new User(1, "John", "Rambo", "johnny", "1234");
        assertEquals(1, user.getID());
    }
}
