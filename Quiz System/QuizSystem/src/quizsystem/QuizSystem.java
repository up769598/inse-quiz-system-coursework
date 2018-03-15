package quizsystem;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import quizsystem.db.DatabaseHandler;
import quizsystem.db.Quiz;

public class QuizSystem {

    public static void main(String[] args) throws SQLException, InvalidObjectException {
        quizsystem.GUI.LoginRegister qr = new quizsystem.GUI.LoginRegister();
        qr.setVisible(true);
       
        try {
            DatabaseHandler db = new DatabaseHandler();
            
            Quiz q = Quiz.getById("5");
            q.revertToDraft();
            q.deleteAssociated();
            
            System.out.println("");
        }
        catch (SQLException | InvalidObjectException ex) {
            System.out.println("[WARN] QuizSystem.main encountered SQLException:");
            System.out.println(ex);
            throw ex;
        }
    }
}
