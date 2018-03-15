package quizsystem;

import java.sql.SQLException;
import java.util.List;
import quizsystem.db.DatabaseHandler;
import quizsystem.db.DraftState;
import quizsystem.db.Quiz;

public class QuizSystem {

    public static void main(String[] args) throws SQLException {
        
        Login login = new Login();
        System.out.println(login.hash("password","qwerty"));
        
       quizsystem.GUI.LoginRegister qr = new quizsystem.GUI.LoginRegister();
       qr.setVisible(true);
       
        try {
            DatabaseHandler db = new DatabaseHandler();
            
            List<Quiz> qs = Quiz.getQuizzesForLecturer("4", DraftState.LIVE);
            
            System.out.println("");
        }
        catch (SQLException ex) {
            System.out.println("[WARN] QuizSystem.main encountered SQLException:");
            System.out.println(ex);
            throw ex;
        }
    }
}
