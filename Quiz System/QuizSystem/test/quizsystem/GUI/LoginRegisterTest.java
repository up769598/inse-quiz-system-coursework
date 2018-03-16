/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizsystem.GUI;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class LoginRegisterTest {
    
    public LoginRegisterTest() {
    }
    /**
     * Test of validateUsername method, of class LoginRegister.
     */
    @Test
    public void testValidateUsername() {
        System.out.println("validateUsername");
        String username = "up769598@myport.ac.uk";
        LoginRegister instance = new LoginRegister();
        boolean expResult = true;
        boolean result = instance.validateUsername(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserName method, of class LoginRegister.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        LoginRegister instance = new LoginRegister();
        String expResult = "aaa.bbb@port.ac.uk";
        String result = instance.getUserName();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPassword method, of class LoginRegister.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        LoginRegister instance = new LoginRegister();
        String expResult = "inse1inse";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of createMessagePane method, of class LoginRegister.
     */
    @Test
    public void testCreateMessagePane() {
        System.out.println("createMessagePane");
        String message = "Test";
        String title = "TADA";
        LoginRegister instance = new LoginRegister();
        instance.createMessagePane(message, title);
    }

    /**
     * Test of main method, of class LoginRegister.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        LoginRegister.main(args);
    }
    
}
