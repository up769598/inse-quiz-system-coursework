package quizsystem.GUI.lecturer;

import quizsystem.db.Quiz;

public class CreateQuiz extends javax.swing.JFrame {
    
    private Quiz quiz;

    public CreateQuiz() {
        initComponents();
    }
    
    public CreateQuiz(Quiz inQuiz){
        initComponents();
        //Load whats already complete into GUI
        quiz = inQuiz;
        sldTime.setValue((int) quiz.getTimeLimit());
        tfName.setText(quiz.getName());
        tfNumQuestions.setText(Integer.toString(quiz.getQuestions().size()));
        
    }

    public String getQuizName() {
        String name = "Default";
        try {
            name = tfName.getText();
        } catch (NullPointerException ex) {
            //Text field is empty
        }
        return name;
    }
    
    public void createQuiz(){
        boolean valid = true;
        String name = getQuizName();
        if(name.length() <= 0 && name.length() > 30 && !"Default".equals(name)){
            valid = false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDetails = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        chbTimer = new javax.swing.JCheckBox();
        sldTime = new javax.swing.JSlider();
        lblNumQuestions = new javax.swing.JLabel();
        lblSelectTimeInMins = new javax.swing.JLabel();
        tfNumQuestions = new javax.swing.JTextField();
        scrpnlQuestion = new javax.swing.JScrollPane();
        taQuestion = new javax.swing.JTextArea();
        btnNextQuestion = new javax.swing.JButton();
        btnPreviousQuestion = new javax.swing.JButton();
        btnAddAnswer = new javax.swing.JButton();
        btnDeleteLastAnswer = new javax.swing.JButton();
        pnlAnswers = new javax.swing.JPanel();
        tfAnswer1 = new javax.swing.JTextField();
        tfAnswer2 = new javax.swing.JTextField();
        tfAnswer3 = new javax.swing.JTextField();
        tfAnswer4 = new javax.swing.JTextField();
        tfAnswer5 = new javax.swing.JTextField();
        tfAnswer6 = new javax.swing.JTextField();
        tfAnswer7 = new javax.swing.JTextField();
        tfAnswer8 = new javax.swing.JTextField();
        lblCorrectAnswer = new javax.swing.JLabel();
        btnSaveSubmit = new javax.swing.JButton();
        btnSaveDraft = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblQuestionNumInfo = new javax.swing.JLabel();
        lblQuestionNum = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlDetails.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblName.setText("Name: ");

        chbTimer.setText("Timer?");
        chbTimer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbTimerItemStateChanged(evt);
            }
        });

        sldTime.setMajorTickSpacing(2);
        sldTime.setMaximum(30);
        sldTime.setMinimum(10);
        sldTime.setPaintLabels(true);
        sldTime.setPaintTicks(true);
        sldTime.setSnapToTicks(true);

        lblNumQuestions.setText("Number of Questions");

        lblSelectTimeInMins.setText("Select Time (in minutes)");

        javax.swing.GroupLayout pnlDetailsLayout = new javax.swing.GroupLayout(pnlDetails);
        pnlDetails.setLayout(pnlDetailsLayout);
        pnlDetailsLayout.setHorizontalGroup(
            pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addComponent(lblName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addComponent(chbTimer)
                        .addGap(18, 18, 18)
                        .addComponent(lblSelectTimeInMins)
                        .addGap(18, 18, 18)
                        .addComponent(sldTime, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addComponent(lblNumQuestions)
                        .addGap(18, 18, 18)
                        .addComponent(tfNumQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(249, Short.MAX_VALUE))
        );
        pnlDetailsLayout.setVerticalGroup(
            pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetailsLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chbTimer)
                            .addComponent(lblSelectTimeInMins))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumQuestions)
                            .addComponent(tfNumQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sldTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        taQuestion.setColumns(20);
        taQuestion.setRows(5);
        scrpnlQuestion.setViewportView(taQuestion);

        btnNextQuestion.setText("Next Question");
        btnNextQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextQuestionActionPerformed(evt);
            }
        });

        btnPreviousQuestion.setText("Previous Question");

        btnAddAnswer.setText("Add Answer");

        btnDeleteLastAnswer.setText("Delete Last Answer");

        pnlAnswers.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tfAnswer1.setText("<Insert Answer Here>");

        tfAnswer2.setText("<Insert Answer Here>");

        tfAnswer3.setText("<Insert Answer Here>");

        tfAnswer4.setText("<Insert Answer Here>");

        tfAnswer5.setText("<Insert Answer Here>");

        tfAnswer6.setText("<Insert Answer Here>");

        tfAnswer7.setText("<Insert Answer Here>");

        tfAnswer8.setText("<Insert Answer Here>");

        javax.swing.GroupLayout pnlAnswersLayout = new javax.swing.GroupLayout(pnlAnswers);
        pnlAnswers.setLayout(pnlAnswersLayout);
        pnlAnswersLayout.setHorizontalGroup(
            pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnswersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfAnswer1)
                    .addComponent(tfAnswer2)
                    .addComponent(tfAnswer3)
                    .addComponent(tfAnswer4)
                    .addComponent(tfAnswer5)
                    .addComponent(tfAnswer6)
                    .addComponent(tfAnswer7)
                    .addComponent(tfAnswer8))
                .addContainerGap())
        );
        pnlAnswersLayout.setVerticalGroup(
            pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnswersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfAnswer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfAnswer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfAnswer3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfAnswer4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfAnswer5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfAnswer6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfAnswer7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfAnswer8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        lblCorrectAnswer.setText("Correct Answer: ");

        btnSaveSubmit.setText("Save and Submit");

        btnSaveDraft.setText("Save to Draft");

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblQuestionNumInfo.setText("Question:");

        lblQuestionNum.setText("<Insert Question Number Here>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                        .addComponent(btnNextQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPreviousQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveDraft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveSubmit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(pnlAnswers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnAddAnswer)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnDeleteLastAnswer)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblCorrectAnswer))
                                .addComponent(pnlDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scrpnlQuestion))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblQuestionNumInfo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblQuestionNum)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuestionNumInfo)
                    .addComponent(lblQuestionNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrpnlQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddAnswer)
                    .addComponent(btnDeleteLastAnswer)
                    .addComponent(lblCorrectAnswer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAnswers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNextQuestion)
                    .addComponent(btnPreviousQuestion)
                    .addComponent(btnSaveSubmit)
                    .addComponent(btnSaveDraft)
                    .addComponent(btnExit))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void chbTimerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbTimerItemStateChanged
        if(chbTimer.isSelected() == true){
            sldTime.setEnabled(true);
            
        } else {
            sldTime.setEnabled(false);
        }
    }//GEN-LAST:event_chbTimerItemStateChanged

    private void btnNextQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextQuestionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextQuestionActionPerformed

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
            java.util.logging.Logger.getLogger(CreateQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateQuiz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAnswer;
    private javax.swing.JButton btnDeleteLastAnswer;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNextQuestion;
    private javax.swing.JButton btnPreviousQuestion;
    private javax.swing.JButton btnSaveDraft;
    private javax.swing.JButton btnSaveSubmit;
    private javax.swing.JCheckBox chbTimer;
    private javax.swing.JLabel lblCorrectAnswer;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumQuestions;
    private javax.swing.JLabel lblQuestionNum;
    private javax.swing.JLabel lblQuestionNumInfo;
    private javax.swing.JLabel lblSelectTimeInMins;
    private javax.swing.JPanel pnlAnswers;
    private javax.swing.JPanel pnlDetails;
    private javax.swing.JScrollPane scrpnlQuestion;
    private javax.swing.JSlider sldTime;
    private javax.swing.JTextArea taQuestion;
    private javax.swing.JTextField tfAnswer1;
    private javax.swing.JTextField tfAnswer2;
    private javax.swing.JTextField tfAnswer3;
    private javax.swing.JTextField tfAnswer4;
    private javax.swing.JTextField tfAnswer5;
    private javax.swing.JTextField tfAnswer6;
    private javax.swing.JTextField tfAnswer7;
    private javax.swing.JTextField tfAnswer8;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfNumQuestions;
    // End of variables declaration//GEN-END:variables
}
