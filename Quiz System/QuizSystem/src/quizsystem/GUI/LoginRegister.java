package quizsystem.GUI;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import quizsystem.Login;
import quizsystem.db.DatabaseHandler;

public class LoginRegister extends javax.swing.JFrame {

    /**
     * Construct the GUI
     */
    public LoginRegister() {
        initComponents();
    }

    /**
     * Retrieve a text value as a String from a text field found on the GUI
     *
     * @param inputBox The input box that the text will be retrieved from
     * @return The string entered into the text field. If the text field is
     * empty, a default string is returned.
     */
    public String getTextInput(JTextField inputBox) {
        String textIn = "Default";
        try {
            textIn = inputBox.getText();
        } catch (NullPointerException ex) {
            //Text field is empty
        }
        return textIn;
    }

    /**
     * Retrieve a text value as an array of characters from a password field
     * found on the GUI
     *
     * @param inputBox The input box found on the GUI to return
     * @return The password entered into the passed inputBox returned as an
     * array of characters. If the password field is empty, a null variable is
     * returned.
     */
    public char[] getPasswordInput(JPasswordField inputBox) {
        char[] passwordIn = null;
        try {
            passwordIn = inputBox.getPassword();
        } catch (NullPointerException ex) {
            //Password fields are empty
        }
        return passwordIn;
    }

    /**
     * Compare two passwords to confirm they match and that the passwords
     * conform to having at least 1 number
     *
     * @param password1 The first password to be validated
     * @param password2 The second password to be validated
     * @return A boolean to indicate whether the two passwords are valid.
     */
    public boolean validatePasswords() {
        char[] password1 = getPasswordInput(tfPasswordRegister);
        char[] password2 = getPasswordInput(tfCPasswordRegister);
        boolean valid = true; //Presume valid until proven invalid
        if (Arrays.equals(password1, password2)) {
            //Passwords Match
            if (password1.length >= 8) {
                //Password must have at least 8 characters
                boolean checkNum = false;
                for (char i : password1) {
                    if (Character.isDigit(i)) {
                        checkNum = true;
                    }
                }
                if (!checkNum) {
                    //At least 1 number is not present in the password
                    valid = false;
                }
            } else {
                valid = false;
            }
        } else {
            //Passwords do not match
            valid = false;
        }
        return valid;
    }

    public boolean validateUsername(String username) {
        return username.endsWith("@myport.ac.uk") || username.endsWith("@port.ac.uk");
    }

    public String getRegEmail() {
        String email = getTextInput(tfEmailRegister);
        if (validateUsername(email)) {
            return email;
        }
        return "Default"; //Return the email if correct
    }

    public char[] getRegPassword() {
        char[] password = getPasswordInput(tfPasswordRegister);
        char[] cpassword = getPasswordInput(tfCPasswordRegister);
        if (validatePasswords()) {
            return password;
        }
        char [] def = null;
        def[1] = 'd';
        return def;
        
    }

    public String getRegCourse() {
        return (String) cboCourseRegister.getSelectedItem();
    }

    public void getRegDetails() {
        boolean valid = true;
        String email = getRegEmail();
        char[] password = getRegPassword();
        String course = getRegCourse();
        String[] userIDtemp = email.split("@");
        userIDtemp = userIDtemp[0].split("up");
        String userID = userIDtemp[1];
        if ("Default".equals(email) || "Default".equals(password) || course.equals("Choose Course Here")) {
            valid = false;
        }
        boolean lecturer = false;
        if (email.endsWith("@port.ac.uk")) {
            lecturer = true;
        }
        if (valid && lecturer) {
            //Create a new lecturer user
        } else if(valid) {
                    try {
           DatabaseHandler db = new DatabaseHandler();
           if(db.isUserRegistered(userID)){
               byte[] salt = Login.getNextSalt();
               Login.hash(password,salt);
               db.addUser(userID, password, salt, course);
           }
       }
       catch (SQLException ex) {
           System.out.println("[WARN] LoginRegister.getRegDetails encountered SQLException:");
           System.out.println(ex);
       }
        } else {
            //Return an error
            createMessagePane("Error: Please ensure that all entered data is valid and try again","Error");
        }

    }

    public String getUserName() {
       return getTextInput(tfEmailLogin);
    }
    
    public char[] getPassword(){
        return getPasswordInput(tfPasswordLogin);
    }
    
    public void createMessagePane(String message, String title) {
        Object[] options = {"Ok"};
        JOptionPane.showOptionDialog(this, message, title, JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblErrorOutput1 = new javax.swing.JLabel();
        pnlLogin = new javax.swing.JPanel();
        lblLoginTitle = new javax.swing.JLabel();
        tfEmailLogin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfPasswordLogin = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        pnlRegister = new javax.swing.JPanel();
        lblNoAccountTitle = new javax.swing.JLabel();
        tfEmailRegister = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboCourseRegister = new javax.swing.JComboBox<>();
        btnRegister = new javax.swing.JButton();
        tfPasswordRegister = new javax.swing.JPasswordField();
        tfCPasswordRegister = new javax.swing.JPasswordField();
        lblErrorOutputEmailR = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblErrorOutputPasswordR = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblLoginTitle.setText("Login");

        jLabel3.setText("Email:");

        jLabel4.setText("Password:");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addComponent(lblLoginTitle)
                        .addGap(0, 374, Short.MAX_VALUE))
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfEmailLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(tfPasswordLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(btnLogin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLoginTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEmailLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(20, 20, 20)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPasswordLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(89, 89, 89)
                .addComponent(btnLogin)
                .addContainerGap())
        );

        pnlRegister.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNoAccountTitle.setText("Don't have an account? Register Here");

        tfEmailRegister.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEmailRegisterFocusLost(evt);
            }
        });

        jLabel5.setText("Email:");

        jLabel6.setText("Password:");

        jLabel7.setText("Confirm Password:");

        jLabel8.setText("Please enter a password with at least 8 characters, and a number");

        jLabel10.setText("Course:");

        cboCourseRegister.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Course Here", "Computer Science", "Computing", "Software Engineering" }));

        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        tfPasswordRegister.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfPasswordRegisterFocusLost(evt);
            }
        });

        tfCPasswordRegister.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCPasswordRegisterFocusLost(evt);
            }
        });

        lblErrorOutputEmailR.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Please enter your university email, ending in either @port.ac.uk or @myport.ac.uk");

        lblErrorOutputPasswordR.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlRegisterLayout = new javax.swing.GroupLayout(pnlRegister);
        pnlRegister.setLayout(pnlRegisterLayout);
        pnlRegisterLayout.setHorizontalGroup(
            pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterLayout.createSequentialGroup()
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRegisterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlRegisterLayout.createSequentialGroup()
                                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNoAccountTitle)
                                    .addGroup(pnlRegisterLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(66, 66, 66)
                                        .addComponent(tfEmailRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegisterLayout.createSequentialGroup()
                                        .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRegisterLayout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cboCourseRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRegisterLayout.createSequentialGroup()
                                                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(pnlRegisterLayout.createSequentialGroup()
                                                        .addComponent(tfPasswordRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addComponent(tfCPasswordRegister)))
                                            .addComponent(lblErrorOutputPasswordR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(1, 1, 1)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlRegisterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblErrorOutputEmailR, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegisterLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRegister)
                .addGap(170, 170, 170))
        );
        pnlRegisterLayout.setVerticalGroup(
            pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNoAccountTitle)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfEmailRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblErrorOutputEmailR, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfPasswordRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfCPasswordRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblErrorOutputPasswordR, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboCourseRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(btnRegister)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(btnExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(btnExit)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            
            if (Login.login(this.getUserName(),this.getPassword()) =="Successful login"){
                quizsystem.GUI.student.StudentInterface stu = new quizsystem.GUI.student.StudentInterface();
                this.setVisible(false);
                stu.setVisible(true);
            }else{
              String error = Login.login(this.getUserName(),this.getPassword());
              // use error to create an error mesage popup
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }//GEN-LAST:event_btnLoginActionPerformed

    private void tfPasswordRegisterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPasswordRegisterFocusLost
        //Check passwords for validity and whether they match
        if (!validatePasswords()) {
            lblErrorOutputPasswordR.setText("Passwords are not valid.");
        } else {
            lblErrorOutputPasswordR.setText("");
        }
    }//GEN-LAST:event_tfPasswordRegisterFocusLost

    private void tfCPasswordRegisterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCPasswordRegisterFocusLost
        //Check passwords for validity and whether they match
        if (!validatePasswords()) {
            lblErrorOutputPasswordR.setText("Passwords are not valid.");
        } else {
            lblErrorOutputPasswordR.setText("");
        }
    }//GEN-LAST:event_tfCPasswordRegisterFocusLost

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        //Register a new user
        getRegDetails();
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void tfEmailRegisterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEmailRegisterFocusLost
        //Check validity of the email
        if (!validateUsername(getTextInput(tfEmailRegister))) {
            lblErrorOutputEmailR.setText("Entered email is not valid, please enter your university email");
        } else {
            lblErrorOutputEmailR.setText("");
        }
    }//GEN-LAST:event_tfEmailRegisterFocusLost

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        //Exit the system
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginRegister.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginRegister.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginRegister.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginRegister.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JComboBox<String> cboCourseRegister;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblErrorOutput1;
    private javax.swing.JLabel lblErrorOutputEmailR;
    private javax.swing.JLabel lblErrorOutputPasswordR;
    private javax.swing.JLabel lblLoginTitle;
    private javax.swing.JLabel lblNoAccountTitle;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlRegister;
    private javax.swing.JPasswordField tfCPasswordRegister;
    private javax.swing.JTextField tfEmailLogin;
    private javax.swing.JTextField tfEmailRegister;
    private javax.swing.JPasswordField tfPasswordLogin;
    private javax.swing.JPasswordField tfPasswordRegister;
    // End of variables declaration//GEN-END:variables
}
