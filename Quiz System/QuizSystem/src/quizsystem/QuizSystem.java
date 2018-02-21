package quizsystem;

import java.sql.SQLException;
import quizsystem.db.DatabaseHandler;

public class QuizSystem {

    public static void main(String[] args) {
       quizsystem.GUI.LoginRegister qr = new quizsystem.GUI.LoginRegister();
       qr.setVisible(true);
             
       String pa = "password" ;  
       char[] pass = pa.toCharArray();
       System.out.println(Login.hash(pass, Login.getNextSalt()));
       System.out.println(Login.hash(pass, Login.getNextSalt()));
       
       try {
           DatabaseHandler db = new DatabaseHandler();
       }
       catch (SQLException ex) {
           System.out.println(ex);
       }
    }
}
