package quizsystem;

import java.io.InvalidObjectException;
import java.sql.SQLException;

/**
 * Create a new instance of the Quiz System
 */
public class QuizSystem {

    /**
     * Run the Quiz System program
     * @param args Any external arguments (none are needed)
     * @throws SQLException
     * @throws InvalidObjectException
     */
    public static void main(String[] args) throws SQLException, InvalidObjectException {
        quizsystem.GUI.LoginRegister qr = new quizsystem.GUI.LoginRegister();
        qr.setVisible(true);
    }
}
