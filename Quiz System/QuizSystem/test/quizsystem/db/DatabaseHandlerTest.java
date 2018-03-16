package quizsystem.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

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
    
     /**
     * Test of isUserRegistered method, of class DatabaseHandler.
     * with a user that dose not exist
     */
    @Test
    public void testIsNotUserRegistered() throws Exception {
        System.out.println("isNotUserRegistered");
        String email = "up999999@myport.ac.uk";
        DatabaseHandler instance = new DatabaseHandler();
        boolean expResult = false;
        boolean result = instance.isUserRegistered(email);
        assertEquals(expResult, result);
    }
    
}
