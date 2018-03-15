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
        assertEquals(result, result);
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

    
}
