package quizsystem;

import java.sql.SQLException;
import java.util.HashMap;
import quizsystem.db.DatabaseHandler;
import quizsystem.db.Quiz;

public class QuizSystem {

    public static void main(String[] args) throws SQLException {
        
        Login login = new Login();
        System.out.println(login.hash("password","qwerty"));
        
       quizsystem.GUI.LoginRegister qr = new quizsystem.GUI.LoginRegister();
       qr.setVisible(true);
                   
        try {
            DatabaseHandler db = new DatabaseHandler();
            
            HashMap<String, String> qm = new HashMap<>();
            qm.put("usrID", "4");
            qm.put("draft", "false");
            qm.put("timeLimit", "30.00");
            qm.put("name", "something");
            Quiz q = Quiz.create(qm);
            
            System.out.println("");
        }
        catch (SQLException ex) {
            System.out.println("[WARN] QuizSystem.main encountered SQLException:");
            System.out.println(ex);
            throw ex;
        }
    }
}
