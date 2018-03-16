package quizsystem;

import java.io.InvalidObjectException;
import java.sql.SQLException;

public class QuizSystem {

    public static void main(String[] args) throws SQLException, InvalidObjectException {
        quizsystem.GUI.LoginRegister qr = new quizsystem.GUI.LoginRegister();
        qr.setVisible(true);
    }
}
