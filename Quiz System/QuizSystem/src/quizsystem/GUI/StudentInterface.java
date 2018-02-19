package quizsystem.GUI;

public class StudentInterface extends javax.swing.JFrame {

    /**
     * Creates new form StudentInterface
     */
    public StudentInterface() {
        initComponents();
    }

        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pnlSetQuiz = new javax.swing.JPanel();
        btnSelectQuiz = new javax.swing.JButton();
        lblSetQuizTitle = new javax.swing.JLabel();
        srpnlSetQuiz = new javax.swing.JScrollPane();
        tblSetQuiz = new javax.swing.JTable();
        pnlRandom = new javax.swing.JPanel();
        btnRandomQuiz = new javax.swing.JButton();
        pnlCompQuiz = new javax.swing.JPanel();
        lblCompQuizTitle = new javax.swing.JLabel();
        srpnlCompQuiz = new javax.swing.JScrollPane();
        tblCompQuiz = new javax.swing.JTable();
        btnAdvSearch = new javax.swing.JButton();
        btnReviewAnswers = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlSetQuiz.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSelectQuiz.setText("Take Quiz");
        btnSelectQuiz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectQuizActionPerformed(evt);
            }
        });

        lblSetQuizTitle.setText("Complete a Set Quiz");

        tblSetQuiz.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Topic", "Lecturer", "Name", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        srpnlSetQuiz.setViewportView(tblSetQuiz);
        if (tblSetQuiz.getColumnModel().getColumnCount() > 0) {
            tblSetQuiz.getColumnModel().getColumn(0).setResizable(false);
            tblSetQuiz.getColumnModel().getColumn(1).setResizable(false);
            tblSetQuiz.getColumnModel().getColumn(2).setResizable(false);
            tblSetQuiz.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout pnlSetQuizLayout = new javax.swing.GroupLayout(pnlSetQuiz);
        pnlSetQuiz.setLayout(pnlSetQuizLayout);
        pnlSetQuizLayout.setHorizontalGroup(
            pnlSetQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSetQuizLayout.createSequentialGroup()
                .addGroup(pnlSetQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSetQuizLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSelectQuiz))
                    .addGroup(pnlSetQuizLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlSetQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(srpnlSetQuiz, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                            .addGroup(pnlSetQuizLayout.createSequentialGroup()
                                .addComponent(lblSetQuizTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pnlSetQuizLayout.setVerticalGroup(
            pnlSetQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSetQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSetQuizTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(srpnlSetQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSelectQuiz)
                .addContainerGap())
        );

        pnlRandom.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnRandomQuiz.setText("Take a Random Quiz");
        btnRandomQuiz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRandomQuizActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRandomLayout = new javax.swing.GroupLayout(pnlRandom);
        pnlRandom.setLayout(pnlRandomLayout);
        pnlRandomLayout.setHorizontalGroup(
            pnlRandomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRandomLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRandomQuiz)
                .addGap(180, 180, 180))
        );
        pnlRandomLayout.setVerticalGroup(
            pnlRandomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRandomLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRandomQuiz)
                .addGap(37, 37, 37))
        );

        pnlCompQuiz.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCompQuizTitle.setText("Review Completed quizzes");

        tblCompQuiz.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Topic", "Lecturer", "Name", "Time", "Mark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        srpnlCompQuiz.setViewportView(tblCompQuiz);
        if (tblCompQuiz.getColumnModel().getColumnCount() > 0) {
            tblCompQuiz.getColumnModel().getColumn(0).setResizable(false);
            tblCompQuiz.getColumnModel().getColumn(1).setResizable(false);
            tblCompQuiz.getColumnModel().getColumn(2).setResizable(false);
            tblCompQuiz.getColumnModel().getColumn(3).setResizable(false);
            tblCompQuiz.getColumnModel().getColumn(4).setResizable(false);
        }

        btnAdvSearch.setText("Advanced Search");
        btnAdvSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdvSearchActionPerformed(evt);
            }
        });

        btnReviewAnswers.setText("Review Answers");
        btnReviewAnswers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReviewAnswersActionPerformed(evt);
            }
        });

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCompQuizLayout = new javax.swing.GroupLayout(pnlCompQuiz);
        pnlCompQuiz.setLayout(pnlCompQuizLayout);
        pnlCompQuizLayout.setHorizontalGroup(
            pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCompQuizLayout.createSequentialGroup()
                        .addComponent(btnLogout)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExit))
                    .addGroup(pnlCompQuizLayout.createSequentialGroup()
                        .addComponent(btnAdvSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReviewAnswers))
                    .addComponent(srpnlCompQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCompQuizTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCompQuizLayout.setVerticalGroup(
            pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCompQuizTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(srpnlCompQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdvSearch)
                    .addComponent(btnReviewAnswers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout)
                    .addComponent(btnExit))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSetQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlRandom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCompQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlCompQuiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlSetQuiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlRandom, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectQuizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectQuizActionPerformed
        //Selects the quiz pressed on the table and continues onto taking that quiz
    }//GEN-LAST:event_btnSelectQuizActionPerformed

    private void btnRandomQuizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRandomQuizActionPerformed
        //Create a random quiz
//        Question randomQuiz = new Question(quizsystem.Quiz inQuiz)
//        randomQuiz.setVisible(true);
        
    }//GEN-LAST:event_btnRandomQuizActionPerformed

    private void btnAdvSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdvSearchActionPerformed
        //Advanced Search through completed quizzes
    }//GEN-LAST:event_btnAdvSearchActionPerformed

    private void btnReviewAnswersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReviewAnswersActionPerformed
        //Review answers given for that completed quiz
    }//GEN-LAST:event_btnReviewAnswersActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        //Logout and return to the lgoin screen
        LoginRegister login = new LoginRegister();
        this.dispose();
        
    }//GEN-LAST:event_btnLogoutActionPerformed

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
            java.util.logging.Logger.getLogger(StudentInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdvSearch;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRandomQuiz;
    private javax.swing.JButton btnReviewAnswers;
    private javax.swing.JButton btnSelectQuiz;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCompQuizTitle;
    private javax.swing.JLabel lblSetQuizTitle;
    private javax.swing.JPanel pnlCompQuiz;
    private javax.swing.JPanel pnlRandom;
    private javax.swing.JPanel pnlSetQuiz;
    private javax.swing.JScrollPane srpnlCompQuiz;
    private javax.swing.JScrollPane srpnlSetQuiz;
    private javax.swing.JTable tblCompQuiz;
    private javax.swing.JTable tblSetQuiz;
    // End of variables declaration//GEN-END:variables
}
