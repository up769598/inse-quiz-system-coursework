package quizsystem;

import java.sql.SQLException;
import java.util.List;
import quizsystem.db.AttemptAnswer;
import quizsystem.db.DatabaseHandler;

public class QuizSystem {

    public static void main(String[] args) {
       quizsystem.GUI.LoginRegister qr = new quizsystem.GUI.LoginRegister();
       qr.setVisible(true);
                   
       try {
           DatabaseHandler db = new DatabaseHandler();
           String[] pas = db.getPasswordAndSalt("up769598@myport.ac.uk");
           List<AttemptAnswer> aa = db.getQuizAttempt("1", "5");
           System.out.println("");
       }
       catch (SQLException ex) {
           System.out.println("[WARN] QuizSystem.main encountered SQLException:");
           System.out.println(ex);
       }
    }
}
