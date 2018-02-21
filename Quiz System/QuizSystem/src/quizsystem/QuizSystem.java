package quizsystem;

import java.sql.SQLException;
import quizsystem.db.DatabaseHandler;
import quizsystem.GUI.LoginRegister;
import quizsystem.GUI.StudentInterface;

public class QuizSystem {

    public static void main(String[] args) {
       //LoginRegister qr = new LoginRegister();
       //qr.setVisible(true);
             
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
