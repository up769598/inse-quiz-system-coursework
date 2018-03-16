package quizsystem.GUI.lecturer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import quizsystem.db.AttemptAnswer;
import quizsystem.db.DatabaseHandler;
import quizsystem.db.Quiz;
import quizsystem.db.User;

public class SelectResult extends javax.swing.JFrame {

    private Quiz quiz;
    private DefaultTableModel resultsModel;
    private ArrayList<String> attemptingStudents;

    public SelectResult(Quiz inQuiz) {
        initComponents();
        quiz = inQuiz;
        lblQuizName.setText(quiz.getName());
        attemptingStudents = new ArrayList();

        Object[] resCol = {"Username", "Total Mark"};
        resultsModel = new DefaultTableModel(resCol, 0);
        tblResults.setModel(resultsModel);

        loadResults();
        displayResults();
    }

    public void displayResults() {
        for (String username : attemptingStudents) {
            Object[] data = {username, totalMarks(username)};
            resultsModel.addRow(data);
        }
    }

    public void loadResults() {
        try {
            DatabaseHandler db = new DatabaseHandler();
            List<AttemptAnswer> tempResults = db.getQuizAttempt("1", "*");
            for (AttemptAnswer tempResult : tempResults) {
                if (attemptingStudents.contains(tempResult.getUser().getEmail())) {
                    attemptingStudents.add(tempResult.getUser().getEmail());
                }
            }
        } catch (SQLException ex) {
            System.out.println("[WARN] QuizSystem.GUI.lecturer.SelectResult encountered SQLException:");
            System.out.println(ex);
        }
        if(attemptingStudents.isEmpty()){
            JOptionPane.showConfirmDialog(this, "No results found for this quiz", "Warning", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
        }
    }

    public int totalMarks(String username) {
        int totalMark = 0;
        try {
            DatabaseHandler db = new DatabaseHandler();
            List<AttemptAnswer> tempResults = db.getQuizAttempt(quiz.getQuizID(), User.getByEmail(username).getUserId());
            for (AttemptAnswer tempResult : tempResults) {
                totalMark += tempResult.getMarks();
            }
        } catch (SQLException ex) {
            System.out.println("[WARN] QuizSystem.GUI.lecturer.SelectResult encountered SQLException:");
            System.out.println(ex);
        }
        return totalMark;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblQuizNameTitle = new javax.swing.JLabel();
        lblQuizName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResults = new javax.swing.JTable();
        btnGoBack = new javax.swing.JButton();
        btnReviewResults = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblQuizNameTitle.setText("Quiz Name: ");

        lblQuizName.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblResults.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblResults);

        btnGoBack.setText("Go Back");
        btnGoBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBackActionPerformed(evt);
            }
        });

        btnReviewResults.setText("Review Results");
        btnReviewResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReviewResultsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGoBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReviewResults))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblQuizNameTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblQuizName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblQuizNameTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblQuizName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGoBack)
                    .addComponent(btnReviewResults))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGoBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBackActionPerformed
        dispose();
    }//GEN-LAST:event_btnGoBackActionPerformed

    private void btnReviewResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReviewResultsActionPerformed

        if (tblResults.getSelectedRow() == -1) {
            JOptionPane.showConfirmDialog(this, "Please select a student and try again", "Warning", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
        } else {
            String username = attemptingStudents.get(tblResults.getSelectedRow());
            try {
                DatabaseHandler db = new DatabaseHandler();
                List<AttemptAnswer> results = db.getQuizAttempt(quiz.getQuizID(), User.getByEmail(username).getUserId());
                LecturerReviewAnswers lra = new LecturerReviewAnswers(this, true, quiz, results);
                lra.setVisible(true);

            } catch (SQLException ex) {
                System.out.println("[WARN] QuizSystem.GUI.lecturer.SelectResult encountered SQLException:");
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_btnReviewResultsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGoBack;
    private javax.swing.JButton btnReviewResults;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblQuizName;
    private javax.swing.JLabel lblQuizNameTitle;
    private javax.swing.JTable tblResults;
    // End of variables declaration//GEN-END:variables
}
