package quizsystem.GUI.student;

public class Question extends javax.swing.JDialog{
    quizsystem.Quiz quiz;
   
    public Question(java.awt.Frame parent, boolean modal, quizsystem.Quiz inQuiz) {
        initComponents();
        quiz = inQuiz;
    }

    public void refresh(){
        setNumQuestion();
        setAnswers();
        hideCheckBoxes();
    }
    
    public void setNumQuestion(){
        lblQuestionNumber.setText(Integer.toString(quiz.getCurrentQuestion())  + " / " + Integer.toString(quiz.getQuestions().length));
    }
    
    public void nextQuestion(){
        int questionNum = quiz.getCurrentQuestion();
        quiz.setCurrentQuestion(questionNum++);
        refresh();
    }
    
    public void prevQuestion(){
        int questionNum = quiz.getCurrentQuestion();
        quiz.setCurrentQuestion(questionNum--);
        refresh();
    }
    
    public void setQuestion(){
        taQuestion.setText(quiz.getQuestion(quiz.getCurrentQuestion()));
    }
    
    public void setAnswers(){
        String[] answers = quiz.getAnswers(quiz.getCurrentQuestion());
        switch(answers.length){
            case 8:
                chbAnswer8.setVisible(true);
                chbAnswer8.setText(answers[7]);
            case 7:
                chbAnswer7.setVisible(true);
                chbAnswer7.setText(answers[6]);
            case 6:
                chbAnswer6.setVisible(true);
                chbAnswer6.setText(answers[5]);
            case 5:
                chbAnswer5.setVisible(true);
                chbAnswer5.setText(answers[4]);
            case 4:
                chbAnswer4.setVisible(true);
                chbAnswer4.setText(answers[3]);
            case 3:
                chbAnswer3.setVisible(true);
                chbAnswer3.setText(answers[2]);
            case 2:
                chbAnswer2.setVisible(true);
                chbAnswer1.setVisible(true);
                chbAnswer2.setText(answers[1]);
                chbAnswer1.setText(answers[0]);
                break;
            default:
                break;
        }
    }
    
    public void hideCheckBoxes(){
        switch(quiz.getAnswers(quiz.getCurrentQuestion()).length){
            case 2:
                chbAnswer3.setVisible(false);
            case 3:
                chbAnswer4.setVisible(false);
            case 4:
                chbAnswer5.setVisible(false);
            case 5:
                chbAnswer6.setVisible(false);
            case 6:
                chbAnswer7.setVisible(false);
            case 7:
                chbAnswer8.setVisible(false);
            
        }
    }
    
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrpnlQuestion = new javax.swing.JScrollPane();
        taQuestion = new javax.swing.JTextArea();
        lblTimer = new javax.swing.JLabel();
        lblQuestionNumber = new javax.swing.JLabel();
        btnNextQuestion = new javax.swing.JButton();
        pnlQuestions = new javax.swing.JPanel();
        chbAnswer1 = new javax.swing.JCheckBox();
        chbAnswer2 = new javax.swing.JCheckBox();
        chbAnswer3 = new javax.swing.JCheckBox();
        chbAnswer4 = new javax.swing.JCheckBox();
        chbAnswer5 = new javax.swing.JCheckBox();
        chbAnswer6 = new javax.swing.JCheckBox();
        chbAnswer7 = new javax.swing.JCheckBox();
        chbAnswer8 = new javax.swing.JCheckBox();
        btnPrevQuestion = new javax.swing.JButton();
        btnFinish = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        taQuestion.setColumns(20);
        taQuestion.setRows(5);
        taQuestion.setText("(Insert Question Here)");
        scrpnlQuestion.setViewportView(taQuestion);

        lblTimer.setText("Finish Time: (Insert Finish Time)");

        lblQuestionNumber.setText("(Question Number) / (Total Number of Questions):");

        btnNextQuestion.setText("Next Question");
        btnNextQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextQuestionActionPerformed(evt);
            }
        });

        pnlQuestions.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chbAnswer1.setText("(Insert Answer Here)");

        chbAnswer2.setText("(Insert Answer Here)");

        chbAnswer3.setText("(Insert Answer Here)");

        chbAnswer4.setText("(Insert Answer Here)");

        chbAnswer5.setText("(Insert Answer Here)");

        chbAnswer6.setText("(Insert Answer Here)");

        chbAnswer7.setText("(Insert Answer Here)");

        chbAnswer8.setText("(Insert Answer Here)");

        javax.swing.GroupLayout pnlQuestionsLayout = new javax.swing.GroupLayout(pnlQuestions);
        pnlQuestions.setLayout(pnlQuestionsLayout);
        pnlQuestionsLayout.setHorizontalGroup(
            pnlQuestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbAnswer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbAnswer3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbAnswer4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbAnswer5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbAnswer6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbAnswer7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbAnswer8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbAnswer1, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addComponent(lblQuestionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        nextQuestion();
    }//GEN-LAST:event_btnNextQuestionActionPerformed

    private void btnPrevQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevQuestionActionPerformed
        //Move back to the previous question
        prevQuestion();
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
