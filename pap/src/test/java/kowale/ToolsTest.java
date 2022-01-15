package kowale;
import kowale.tools.Tools;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ToolsTest {

    Tools tools = new Tools();

    @Test
    public void validateEmailTestDot(){
        String invalidEmail = "user.name@domain.com";
        assertTrue(tools.validateEmail(invalidEmail));
    }

    @Test
    public void validateEmailTestHypen(){
        String invalidEmail = "user-name@domain.com";
        assertTrue(tools.validateEmail(invalidEmail));
    }

    @Test
    public void validateEmailTestDotUnderscore(){
        String invalidEmail = "user_name@domain.com";
        assertTrue(tools.validateEmail(invalidEmail));
    }

    @Test
    public void validateEmailTestInvalid(){
        String invalidEmail = "username.@domain.com";
        assertFalse(tools.validateEmail(invalidEmail));
    }

    @Test
    public void validateEmailTestDotPrefix(){
        String invalidEmail = ".user.name@domain.com";
        assertFalse(tools.validateEmail(invalidEmail));
    }

    @Test
    public void validateEmailTestDotSuffix(){
        String invalidEmail = "user-name@domain.com.";
        assertFalse(tools.validateEmail(invalidEmail));
    }

    @Test
    public void validateEmailTestEmptyDomain(){
        String invalidEmail = "username@.com";
        assertFalse(tools.validateEmail(invalidEmail));
    }
}
