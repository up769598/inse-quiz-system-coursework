package quizsystem.GUI;

public class Question extends javax.swing.JFrame {
    quizsystem.Quiz quiz;

    /**
     * Creates new form Question
     * @param inQuiz The Quiz being taken
     */
    public Question(quizsystem.Quiz inQuiz) {
        initComponents();
        quiz = inQuiz;
        
    }

    /**TO ADD:
     * Begin Timer
     * Check Timer
     * Force quit upon finish timer
     * move between questions
     * Calculate num of questions
     * Set textfields (question, answers, timer, question number)
     * Auto set checkboxes to invisible/visible
     * 
    */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrpAnswers = new javax.swing.ButtonGroup();
        scrpnlQuestion = new javax.swing.JScrollPane();
        taQuestion = new javax.swing.JTextArea();
        lblQuestionNumber = new javax.swing.JLabel();
        pnlQuestions = new javax.swing.JPanel();
        chbAnswer1 = new javax.swing.JCheckBox();
        chbAnswer2 = new javax.swing.JCheckBox();
        chbAnswer3 = new javax.swing.JCheckBox();
        chbAnswer4 = new javax.swing.JCheckBox();
        chbAnswer5 = new javax.swing.JCheckBox();
        chbAnswer6 = new javax.swing.JCheckBox();
        chbAnswer7 = new javax.swing.JCheckBox();
        chbAnswer8 = new javax.swing.JCheckBox();
        lblTimer = new javax.swing.JLabel();
        btnNextQuestion = new javax.swing.JButton();
        btnPrevQuestion = new javax.swing.JButton();
        btnFinish = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        taQuestion.setColumns(20);
        taQuestion.setRows(5);
        taQuestion.setText("(Insert Question Here)");
        scrpnlQuestion.setViewportView(taQuestion);

        lblQuestionNumber.setText("(Question Number) / (Total Number of Questions):");

        pnlQuestions.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btngrpAnswers.add(chbAnswer1);
        chbAnswer1.setText("(Insert Answer Here)");

        btngrpAnswers.add(chbAnswer2);
        chbAnswer2.setText("(Insert Answer Here)");

        btngrpAnswers.add(chbAnswer3);
        chbAnswer3.setText("(Insert Answer Here)");

        btngrpAnswers.add(chbAnswer4);
        chbAnswer4.setText("(Insert Answer Here)");

        btngrpAnswers.add(chbAnswer5);
        chbAnswer5.setText("(Insert Answer Here)");

        btngrpAnswers.add(chbAnswer6);
        chbAnswer6.setText("(Insert Answer Here)");

        btngrpAnswers.add(chbAnswer7);
        chbAnswer7.setText("(Insert Answer Here)");

        btngrpAnswers.add(chbAnswer8);
        chbAnswer8.setText("(Insert Answer Here)");

        javax.swing.GroupLayout pnlQuestionsLayout = new javax.swing.GroupLayout(pnlQuestions);
        pnlQuestions.setLayout(pnlQuestionsLayout);
        pnlQuestionsLayout.setHorizontalGroup(
            pnlQuestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbAnswer1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                    .addComponent(chbAnswer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbAnswer3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbAnswer4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbAnswer5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbAnswer6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbAnswer7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbAnswer8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlQuestionsLayout.setVerticalGroup(
            pnlQuestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chbAnswer1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chbAnswer2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chbAnswer3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chbAnswer4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chbAnswer5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chbAnswer6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chbAnswer7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chbAnswer8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTimer.setText("Finish Time: (Insert Finish Time)");

        btnNextQuestion.setText("Next Question");
        btnNextQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextQuestionActionPerformed(evt);
            }
        });

        btnPrevQuestion.setText("Previous Question");
        btnPrevQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevQuestionActionPerformed(evt);
            }
        });

        btnFinish.setText("Finish");
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });

        btnQuit.setText("Quit");
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrpnlQuestion)
                    .addComponent(lblQuestionNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNextQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPrevQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFinish, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQuit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuestionNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrpnlQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTimer)
                        .addGap(18, 18, 18)
                        .addComponent(btnNextQuestion)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrevQuestion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFinish)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuit)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextQuestionActionPerformed
        //Move onto the next question
    }//GEN-LAST:event_btnNextQuestionActionPerformed

    private void btnPrevQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevQuestionActionPerformed
        //Move back to the previous question
    }//GEN-LAST:event_btnPrevQuestionActionPerformed

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
        //Finish and submit answers
    }//GEN-LAST:event_btnFinishActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        //Quit and go back to quiz preview
    }//GEN-LAST:event_btnQuitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnNextQuestion;
    private javax.swing.JButton btnPrevQuestion;
    private javax.swing.JButton btnQuit;
    private javax.swing.ButtonGroup btngrpAnswers;
    private javax.swing.JCheckBox chbAnswer1;
    private javax.swing.JCheckBox chbAnswer2;
    private javax.swing.JCheckBox chbAnswer3;
    private javax.swing.JCheckBox chbAnswer4;
    private javax.swing.JCheckBox chbAnswer5;
    private javax.swing.JCheckBox chbAnswer6;
    private javax.swing.JCheckBox chbAnswer7;
    private javax.swing.JCheckBox chbAnswer8;
    private javax.swing.JLabel lblQuestionNumber;
    private javax.swing.JLabel lblTimer;
    private javax.swing.JPanel pnlQuestions;
    private javax.swing.JScrollPane scrpnlQuestion;
    private javax.swing.JTextArea taQuestion;
    // End of variables declaration//GEN-END:variables
}