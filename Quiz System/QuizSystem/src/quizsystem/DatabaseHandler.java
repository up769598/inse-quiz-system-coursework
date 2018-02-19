/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizsystem;

/**
 *
 * @author James
 */
public class DatabaseHandler {

    public static String[] getPasswordAndSalt(String userID){
        String db_Password = null;
        String db_Salt = null;
        /*
        * retreve the hashed password and salt from the database
        */
        
        
        
        
        String[] passAndSalt = null;
        passAndSalt[0] = db_Password;
        passAndSalt[1] = db_Salt;
        return passAndSalt;
        
    }
    public static boolean checkRegestered(){
        /*
        run database querie to see if the email adress is registered and retruns boolean
        */
        
        return true;
    }
    
    
}
