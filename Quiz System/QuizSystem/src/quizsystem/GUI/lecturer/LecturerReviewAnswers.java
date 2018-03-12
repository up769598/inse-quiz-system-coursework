package quizsystem.GUI.lecturer;

public class LecturerReviewAnswers extends javax.swing.JDialog {
    private final quizsystem.db.Quiz quiz;
    
    public LecturerReviewAnswers(java.awt.Frame parent, boolean modal, quizsystem.db.Quiz inQuiz) {
        super(parent, modal);
        initComponents();
        quiz = inQuiz;
        //Set the marks attained here
    }
    
    public void refresh(){
        setNumQuestion();
        setAnswers();
        hideLabels();
    }
    
    public void setNumQuestion(){
        lblQuestionNumber.setText(Integer.toString(quiz.getCurrentQuestion())  + " / " + Integer.toString(quiz.getQuestions().size()));
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
                lblAnswer8.setVisible(true);
                lblAnswer8.setText(answers[7]);
            case 7:
                lblAnswer7.setVisible(true);
                lblAnswer7.setText(answers[6]);
            case 6:
                lblAnswer6.setVisible(true);
                lblAnswer6.setText(answers[5]);
            case 5:
                lblAnswer5.setVisible(true);
                lblAnswer5.setText(answers[4]);
            case 4:
                lblAnswer4.setVisible(true);
                lblAnswer4.setText(answers[3]);
            case 3:
                lblAnswer3.setVisible(true);
                lblAnswer3.setText(answers[2]);
            case 2:
                lblAnswer2.setVisible(true);
                lblAnswer1.setVisible(true);
                lblAnswer2.setText(answers[1]);
                lblAnswer1.setText(answers[0]);
                break;
            default:
                break;
        }
    }
    
    public void hideLabels(){
        switch(quiz.getAnswers(quiz.getCurrentQuestion()).length){
            case 2:
                lblAnswer3.setVisible(false);
            case 3:
                lblAnswer4.setVisible(false);
            case 4:
                lblAnswer5.setVisible(false);
            case 5:
                lblAnswer6.setVisible(false);
            case 6:
                lblAnswer7.setVisible(false);
            case 7:
                lblAnswer8.setVisible(false);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblQuestionNumber = new javax.swing.JLabel();
        btnPrevQuestion = new javax.swing.JButton();
        btnNextQuestion = new javax.swing.JButton();
        pnlQuestions = new javax.swing.JPanel();
        lblAnswer1 = new javax.swing.JLabel();
        lblAnswer2 = new javax.swing.JLabel();
        lblAnswer3 = new javax.swing.JLabel();
        lblAnswer4 = new javax.swing.JLabel();
        lblAnswer5 = new javax.swing.JLabel();
        lblAnswer6 = new javax.swing.JLabel();
        lblAnswer7 = new javax.swing.JLabel();
        lblAnswer8 = new javax.swing.JLabel();
        btnQuit = new javax.swing.JButton();
        scrpnlQuestion = new javax.swing.JScrollPane();
        taQuestion = new javax.swing.JTextArea();
        lblCorrectAnswerTitle = new javax.swing.JLabel();
        lblCorrectAnswerNum = new javax.swing.JLabel();
        lblGivenAnswerTitle = new javax.swing.JLabel();
        lblGivenAnswerNum = new javax.swing.JLabel();
        pnlMarks = new javax.swing.JPanel();
        lblPercentage = new javax.swing.JLabel();
        lblGrade = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblQuestionNumber.setText("(Question Number) / (Total Number of Questions):");

        btnPrevQuestion.setText("Previous Question");
        btnPrevQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevQuestionActionPerformed(evt);
            }
        });

        btnNextQuestion.setText("Next Question");
        btnNextQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextQuestionActionPerformed(evt);
            }
        });

        pnlQuestions.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAnswer1.setText("(Insert Answer Here)");

        lblAnswer2.setText("(Insert Answer Here)");

        lblAnswer3.setText("(Insert Answer Here)");

        lblAnswer4.setText("(Insert Answer Here)");

        lblAnswer5.setText("(Insert Answer Here)");

        lblAnswer6.setText("(Insert Answer Here)");

        lblAnswer7.setText("(Insert Answer Here)");

        lblAnswer8.setText("(Insert Answer Here)");

        javax.swing.GroupLayout pnlQuestionsLayout = new javax.swing.GroupLayout(pnlQuestions);
        pnlQuestions.setLayout(pnlQuestionsLayout);
        pnlQuestionsLayout.setHorizontalGroup(
            pnlQuestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnswer1)
                    .addComponent(lblAnswer2)
                    .addComponent(lblAnswer3)
                    .addComponent(lblAnswer4)
                    .addComponent(lblAnswer5)
                    .addComponent(lblAnswer6)
                    .addComponent(lblAnswer7)
                    .addComponent(lblAnswer8))
                .addContainerGap(804, Short.MAX_VALUE))
        );
        pnlQuestionsLayout.setVerticalGroup(
            pnlQuestionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnswer1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAnswer2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAnswer3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAnswer4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAnswer5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAnswer6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAnswer7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAnswer8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnQuit.setText("Quit");
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });

        taQuestion.setColumns(20);
        taQuestion.setRows(5);
        taQuestion.setText("(Insert Question Here)");
        scrpnlQuestion.setViewportView(taQuestion);

        lblCorrectAnswerTitle.setText("Correct Answer");

        lblCorrectAnswerNum.setText("(Insert Correct Answer Num Here)");

        lblGivenAnswerTitle.setText("Given Answer");

        lblGivenAnswerNum.setText("(Insert Given Answer Num Here)");

        pnlMarks.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPercentage.setText("(Insert Overall Mark Here)");

        lblGrade.setText("(Insert Predicted Grade Here)");

        javax.swing.GroupLayout pnlMarksLayout = new javax.swing.GroupLayout(pnlMarks);
        pnlMarks.setLayout(pnlMarksLayout);
        pnlMarksLayout.setHorizontalGroup(
            pnlMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPercentage)
                    .addComponent(lblGrade))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMarksLayout.setVerticalGroup(
            pnlMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPercentage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGrade)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMarks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrpnlQuestion)
                    .addComponent(lblQuestionNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCorrectAnswerTitle)
                                    .addComponent(lblCorrectAnswerNum)
                                    .addComponent(lblGivenAnswerTitle)
                                    .addComponent(lblGivenAnswerNum))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnNextQuestion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPrevQuestion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQuit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuestionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrpnlQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCorrectAnswerTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCorrectAnswerNum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGivenAnswerTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGivenAnswerNum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNextQuestion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrevQuestion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrevQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevQuestionActionPerformed
        //Move back to the previous question
        prevQuestion();
    }//GEN-LAST:event_btnPrevQuestionActionPerformed

    private void btnNextQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextQuestionActionPerformed
        //Move onto the next question
        nextQuestion();
    }//GEN-LAST:event_btnNextQuestionActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        //Quit and go back to student interface
        dispose();
    }//GEN-LAST:event_btnQuitActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNextQuestion;
    private javax.swing.JButton btnPrevQuestion;
    private javax.swing.JButton btnQuit;
    private javax.swing.JLabel lblAnswer1;
    private javax.swing.JLabel lblAnswer2;
    private javax.swing.JLabel lblAnswer3;
    private javax.swing.JLabel lblAnswer4;
    private javax.swing.JLabel lblAnswer5;
    private javax.swing.JLabel lblAnswer6;
    private javax.swing.JLabel lblAnswer7;
    private javax.swing.JLabel lblAnswer8;
    private javax.swing.JLabel lblCorrectAnswerNum;
    private javax.swing.JLabel lblCorrectAnswerTitle;
    private javax.swing.JLabel lblGivenAnswerNum;
    private javax.swing.JLabel lblGivenAnswerTitle;
    private javax.swing.JLabel lblGrade;
    private javax.swing.JLabel lblPercentage;
    private javax.swing.JLabel lblQuestionNumber;
    private javax.swing.JPanel pnlMarks;
    private javax.swing.JPanel pnlQuestions;
    private javax.swing.JScrollPane scrpnlQuestion;
    private javax.swing.JTextArea taQuestion;
    // End of variables declaration//GEN-END:variables
}
