package quizsystem;

import java.sql.SQLException;
import java.util.List;
import quizsystem.db.DatabaseHandler;
import quizsystem.db.Quiz;
import quizsystem.db.QuizState;

public class QuizSystem {

    public static void main(String[] args) throws SQLException {
       quizsystem.GUI.LoginRegister qr = new quizsystem.GUI.LoginRegister();
       qr.setVisible(true);
                   
        try {
            DatabaseHandler db = new DatabaseHandler();
            List<Quiz> qs = db.getQuizzesForStudent("5", QuizState.INCOMPLETE);
            System.out.println("");
        }
        catch (SQLException ex) {
            System.out.println("[WARN] QuizSystem.main encountered SQLException:");
            System.out.println(ex);
            throw ex;
        }
    }
}
