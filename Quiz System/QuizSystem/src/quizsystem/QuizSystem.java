package quizsystem;

import quizsystem.GUI.LoginRegister;
import quizsystem.GUI.StudentInterface;

public class QuizSystem {

    public static void main(String[] args) {
       LoginRegister qr = new LoginRegister();
       qr.setVisible(true);
       
       StudentInterface qr2 = new StudentInterface();
       qr2.setVisible(true);
       
       String pa = "password" ;  
       char[] pass = pa.toCharArray();
       System.out.println(Login.hash(pass, Login.getNextSalt()));
       System.out.println(Login.hash(pass, Login.getNextSalt()));
    }
    
}
