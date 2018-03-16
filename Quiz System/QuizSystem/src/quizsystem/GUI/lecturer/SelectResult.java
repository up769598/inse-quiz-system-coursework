package quizsystem.GUI.lecturer;

import quizsystem.GUI.ReviewAnswers;
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
    private List<String> attemptingStudents;

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
            attemptingStudents = db.getStudentsTakenQuiz(quiz.getQuizID());
        } catch (SQLException ex) {
            System.out.println("[WARN] QuizSystem.GUI.lecturer.SelectResult encountered SQLException:");
            System.out.println(ex);
        }
        if(attemptingStudents.isEmpty()){
            JOptionPane.showMessageDialog(this, "No results found for this quiz", "Warning", JOptionPane.WARNING_MESSAGE);
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
        jPanel1 = new javax.swing.JPanel();
        btnGoBack = new javax.swing.JButton();
        btnReviewResults = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGoBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReviewResults)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGoBack)
                    .addComponent(btnReviewResults))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblQuizNameTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblQuizName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGoBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBackActionPerformed
        dispose();
    }//GEN-LAST:event_btnGoBackActionPerformed

    private void btnReviewResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReviewResultsActionPerformed

        if (tblResults.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student and try again", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String username = attemptingStudents.get(tblResults.getSelectedRow());
            try {
                DatabaseHandler db = new DatabaseHandler();
                List<AttemptAnswer> results = db.getQuizAttempt(quiz.getQuizID(), User.getByEmail(username).getUserId());
                ReviewAnswers ra = new ReviewAnswers(this, true, quiz, results);
                ra.setVisible(true);
            } catch (SQLException ex) {
                System.out.println("[WARN] QuizSystem.GUI.lecturer.SelectResult encountered SQLException:");
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_btnReviewResultsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGoBack;
    private javax.swing.JButton btnReviewResults;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblQuizName;
    private javax.swing.JLabel lblQuizNameTitle;
    private javax.swing.JTable tblResults;
    // End of variables declaration//GEN-END:variables
}
