/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizsystem;

import java.sql.*;

/**
 *
 * @author James
 */
public class DatabaseHandler {

    public DatabaseHandler() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://185.27.134.10:3306/INSEQuizSystem", "epiz_21645344", "0f03H2VepYh7");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from s");
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
//            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String[] getPasswordAndSalt(String userID) {
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

    public static boolean checkRegestered() {
        /*
        run database querie to see if the email adress is registered and retruns boolean
         */

        return true;
    }

}
