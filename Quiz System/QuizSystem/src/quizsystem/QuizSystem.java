package quizsystem;

import java.sql.SQLException;
import quizsystem.db.DatabaseHandler;

public class QuizSystem {

    public static void main(String[] args) {
       quizsystem.GUI.LoginRegister qr = new quizsystem.GUI.LoginRegister();
       qr.setVisible(true);
                   
       try {
           DatabaseHandler db = new DatabaseHandler();
           db.getPasswordAndSalt("up769598@myport.ac.uk");
       }
       catch (SQLException ex) {
           System.out.println("[WARN] QuizSystem.main encountered SQLException:");
           System.out.println(ex);
       }
    }
}
