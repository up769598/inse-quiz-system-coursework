package quizsystem.GUI.student;

public class TakeQuiz extends javax.swing.JDialog {

    quizsystem.db.Quiz quiz;
    Integer[] selAnswers = new Integer[quiz.getQuestions().size()];
    int selectedAnswer = 0;

    /**
     *
     * @param parent The parent JFrame that this JDialog is created from. 
     * @param modal 
     * @param inQuiz The Quiz that the user is taking 
     */
    public TakeQuiz(java.awt.Frame parent, boolean modal, quizsystem.db.Quiz inQuiz) {
        initComponents();
        quiz = inQuiz;
    }

    /**
     * Resets the GUI, refreshing the list of answers, the question and the question number.
     */
    public void refresh() {
        setQuestion();
        setNumQuestion();
        setAnswers();
        hideCheckBoxes();
    }

    /**
     * Sets the question number to appear on the GUI.
     */
    public void setNumQuestion() {
        lblQuestionNumber.setText(Integer.toString(quiz.getCurrentQuestion()) + " / " + Integer.toString(quiz.getQuestions().size()));
    }

    /**
     * Saves the chosen answer, proceeds the quiz onto the next question and refreshes the GUI.
     */
    public void nextQuestion() {
        selectAnswer();
        int questionNum = quiz.getCurrentQuestion();
        quiz.setCurrentQuestion(questionNum++);
        refresh();
    }

    /**
     * Saves the chosen answer, proceeds the quiz onto the previous question and refreshes the GUI.
     */
    public void prevQuestion() {
        selectAnswer();
        int questionNum = quiz.getCurrentQuestion();
        quiz.setCurrentQuestion(questionNum--);
        refresh();
    }

    /**
     * Sets the question to appear on the GUI.
     */
    public void setQuestion() {
        taQuestion.setText(quiz.getQuestion(quiz.getCurrentQuestion()));
    }

    /**
     * Sets the labels attached to each checkbox to show each possible answer for the question.
     */
    public void setAnswers() {
        String[] answers = quiz.getAnswers(quiz.getCurrentQuestion());
        switch (answers.length) {
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

    /**
     * Hides the checkboxes for questions without the full 8 answers.
     */
    public void hideCheckBoxes() {
        switch (quiz.getAnswers(quiz.getCurrentQuestion()).length) {
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

    /**
     * Saves the selected answer into an array.
     */
    public void selectAnswer() {
        selAnswers[quiz.getCurrentQuestion()] = selectedAnswer;
    }
    
    /**
     * Record the complete collection of given answers ready to be written to the database.
     */
    public void saveAnswers(){
        //Save answers and write them to the database
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgpChosenAnswer = new javax.swing.ButtonGroup();
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

        bgpChosenAnswer.add(chbAnswer1);
        chbAnswer1.setText("(Insert Answer Here)");
        chbAnswer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbAnswer1ActionPerformed(evt);
            }
        });

        bgpChosenAnswer.add(chbAnswer2);
        chbAnswer2.setText("(Insert Answer Here)");
        chbAnswer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbAnswer2ActionPerformed(evt);
            }
        });

        bgpChosenAnswer.add(chbAnswer3);
        chbAnswer3.setText("(Insert Answer Here)");
        chbAnswer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbAnswer3ActionPerformed(evt);
            }
        });

        bgpChosenAnswer.add(chbAnswer4);
        chbAnswer4.setText("(Insert Answer Here)");
        chbAnswer4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbAnswer4ActionPerformed(evt);
            }
        });

        bgpChosenAnswer.add(chbAnswer5);
        chbAnswer5.setText("(Insert Answer Here)");
        chbAnswer5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbAnswer5ActionPerformed(evt);
            }
        });

        bgpChosenAnswer.add(chbAnswer6);
        chbAnswer6.setText("(Insert Answer Here)");
        chbAnswer6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbAnswer6ActionPerformed(evt);
            }
        });

        bgpChosenAnswer.add(chbAnswer7);
        chbAnswer7.setText("(Insert Answer Here)");
        chbAnswer7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbAnswer7ActionPerformed(evt);
            }
        });

        bgpChosenAnswer.add(chbAnswer8);
        chbAnswer8.setText("(Insert Answer Here)");
        chbAnswer8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbAnswer8ActionPerformed(evt);
            }
        });

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
        selectAnswer();
    }//GEN-LAST:event_btnFinishActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        //Quit and go back to quiz preview
    }//GEN-LAST:event_btnQuitActionPerformed

    private void chbAnswer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbAnswer1ActionPerformed
        selectedAnswer = 1;
    }//GEN-LAST:event_chbAnswer1ActionPerformed

    private void chbAnswer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbAnswer2ActionPerformed
        selectedAnswer = 2;
    }//GEN-LAST:event_chbAnswer2ActionPerformed

    private void chbAnswer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbAnswer3ActionPerformed
        selectedAnswer = 3;
    }//GEN-LAST:event_chbAnswer3ActionPerformed

    private void chbAnswer4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbAnswer4ActionPerformed
        selectedAnswer = 4;
    }//GEN-LAST:event_chbAnswer4ActionPerformed

    private void chbAnswer5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbAnswer5ActionPerformed
        selectedAnswer = 5;
    }//GEN-LAST:event_chbAnswer5ActionPerformed

    private void chbAnswer6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbAnswer6ActionPerformed
        selectedAnswer = 6;
    }//GEN-LAST:event_chbAnswer6ActionPerformed

    private void chbAnswer7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbAnswer7ActionPerformed
        selectedAnswer = 7;
    }//GEN-LAST:event_chbAnswer7ActionPerformed

    private void chbAnswer8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbAnswer8ActionPerformed
        selectedAnswer = 8;
    }//GEN-LAST:event_chbAnswer8ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpChosenAnswer;
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
