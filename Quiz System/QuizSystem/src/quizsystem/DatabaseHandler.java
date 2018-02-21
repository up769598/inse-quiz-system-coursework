package quizsystem;

import java.sql.*;

public class DatabaseHandler {

    public DatabaseHandler() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://52.91.67.192:3306/inse", "inse", "Wv7q7hG9");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT 1 AS one;");
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
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
