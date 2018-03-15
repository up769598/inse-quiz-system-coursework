/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizsystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class LoginTest {
    
    public LoginTest() {
    }
    
    /**
     * Test of login method, of class Login.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        String email = "up123456@myport.ac.uk";
        String password = "inse1inse";
        String expResult = "Successful login";
        String result = Login.login(email, password);
        assertEquals(expResult, result);
    }
    /**
     * Test of hash method, of class Login.
     */
    @Test
    public void testHash() {
        System.out.println("hash");
        String password = "password";
        String salt = "qwertyu";
        String result = Login.hash(password, salt);
        assertEquals(expResult, result);
    }

    /**
     * Test of isPasswordCorrect method, of class Login.
     */
    @Test
    public void testIsPasswordCorrect() {
        System.out.println("isPasswordCorrect");
        String password = "password";
        String salt = "qwerty";
        String expectedHash = "";
        boolean expResult = false;
        boolean result = Login.isPasswordCorrect(password, salt, expectedHash);
        assertEquals(expResult, result);
    }

    /**
     * Test of unregistered user trying to login 
     */
    @Test
    public void testUnregistered() throws Exception {
        System.out.println("testUnregistered");
        String email = "up823840@myport.ac.uk";
        String password = "TestCode1";
        String expResult = "User not registered";
        String result = Login.login(email, password);
        assertEquals(expResult, result);
    }
    
    /**
     * Tests the result if a wrong password is entered for a registered user
     */
    @Test
    public void testWrongPassword() throws Exception {
        System.out.println("testWrongPassword");
        String email = "up823830@myport.ac.uk";
        String password = "TestCode";
        String expResult = "Incorrect Password";
        String result = Login.login(email, password);
        assertEquals(expResult, result);
    }

     /**
     * Tests the result if no password is entered for a registered user
     */
    @Test
    public void testNoPassword() throws Exception {
        System.out.println("testNoPassword");
        String email = "up823837@myport.ac.uk";
        String password = "";
        String expResult = "Incorrect Password";
        String result = Login.login(email, password);
        assertEquals(expResult, result);
    }

     /**
     * Tests the result if a wrong email (Doesn't end in uni email) is entered 
     */
    @Test
    public void testWrongEmail() throws Exception {
        System.out.println("testWrongEmail");
        String email = "up823830@hotmail.co.uk";
        String password = "TestCode1";
        String expResult = "User not registered";
        String result = Login.login(email, password);
        assertEquals(expResult, result);
    }
    
    /**
     * Tests the result if a no email is entered
     */
    @Test
    public void testNothingEnteredEmail() throws Exception {
        System.out.println("testNothingEnteredEmail");
        String email = "";
        String password = "TestCode1";
        String expResult = "User not registered";
        String result = Login.login(email, password);
        assertEquals(expResult, result);
    }
        
    /**
     * Tests the result if a nothing is entered 
     */
    
    @Test
    public void testNothingEntered() throws Exception {
        System.out.println("testNothingEntered ");
        String email = "";
        String password = "";
        String expResult = "User not registered";
        String result = Login.login(email, password);
        assertEquals(expResult, result);
        
    }
}
