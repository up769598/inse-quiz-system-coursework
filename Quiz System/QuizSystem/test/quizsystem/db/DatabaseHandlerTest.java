/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizsystem.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class DatabaseHandlerTest {
    
    public DatabaseHandlerTest() {
    }

   
    @Test
    public void testExecute_String() throws Exception {
        System.out.println("execute");
        String query = "select * from Users";
        DatabaseHandler instance = new DatabaseHandler();
        ArrayList<ResultRow> result = instance.execute(query);
        assertNotNull(result);

    }
    /**
     * Test of isUserRegistered method, of class DatabaseHandler.
     */
    @Test
    public void testIsUserRegistered() throws Exception {
        System.out.println("isUserRegistered");
        String email = "up769598@myport.ac.uk";
        DatabaseHandler instance = new DatabaseHandler();
        boolean expResult = true;
        boolean result = instance.isUserRegistered(email);
        assertEquals(expResult, result);
    }
    
}
