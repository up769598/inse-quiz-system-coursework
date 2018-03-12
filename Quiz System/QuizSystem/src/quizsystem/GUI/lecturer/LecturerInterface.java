/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizsystem.GUI.lecturer;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import quizsystem.GUI.LoginRegister;
import quizsystem.db.Quiz;

public class LecturerInterface extends javax.swing.JFrame {

    private final DefaultTableModel modelDraftQuiz;
    private final DefaultTableModel modelQuiz;
    private  ArrayList<quizsystem.db.Quiz> draftQuiz;
    private  ArrayList<quizsystem.db.Quiz> quizzes;
    private  ArrayList<quizsystem.db.Quiz> searchQuiz;
    private boolean searched;
    private final String username;

    /**
     * Create a new Lecturer Interface GUI Window
     *
     * @param inUsername username of the logged in lecturer
     */
    public LecturerInterface(String inUsername) {
        initComponents();
        quizzes = new ArrayList<>();
        draftQuiz = new ArrayList<>();
        searchQuiz = new ArrayList<>();
        searched = false;
        username = inUsername;

        Object[] colQuiz = {"Lecturer", "Name", "Time", "Avg Mark"};
        modelQuiz = new DefaultTableModel(colQuiz, 0);
        tblQuiz.setModel(modelQuiz);

        Object[] colDraftQuiz = {"Name", "Number of Questions"};
        modelDraftQuiz = new DefaultTableModel(colDraftQuiz, 0);
        tblDraftQuiz.setModel(modelDraftQuiz);
    }
    
    public ArrayList<quizsystem.db.Quiz> loadDraftQuizzes(){
        //Get all quizzes made by the lecturer that are still in their draft phase
        //add results to the draft quiz arraylist
        ArrayList<Quiz> quiz = new ArrayList<>();
        return quiz;
    }
    
    public ArrayList<quizsystem.db.Quiz> loadQuizzes(){
        //Get all quizzes made by the lecturer that are NOT in their draft phase and potentially have results attached to them
        //Add results to the quizzes arraylist
        ArrayList<Quiz> quiz = new ArrayList<>();
        return quiz;
    }
    
    public void logout() {
        LoginRegister login = new LoginRegister();
        login.setVisible(true);
        this.dispose();
    }

    /**
     * Clears the draft quiz table of all data.
     */
    private void clearDraftQuizTable() {
        modelDraftQuiz.setRowCount(0);
    }

    /**
     * Clears the quiz table of all data.
     */
    private void clearQuizTable() {
        modelQuiz.setRowCount(0);
    }

    /**
     * Appends data from a quiz onto a new row on the draft quiz table
     *
     * @param inQuiz The Quiz to be appended onto the draft quiz table
     */
    private void addDraftQuiz(quizsystem.db.Quiz inQuiz) {
        Object[] data = {inQuiz.getName(), inQuiz.getQuestions().size()};
        modelDraftQuiz.addRow(data);
    }

    /**
     * Appends data from a quiz onto a new row on the quiz table
     *
     * @param inQuiz The Quiz to be appended onto the quiz table
     */
    private void addQuiz(quizsystem.db.Quiz inQuiz) {
        Object[] data = {inQuiz.getLecturerName(), inQuiz.getName(), inQuiz.getTimeLimit(), 0}; //Add average mark
        modelQuiz.addRow(data);
    }

    /**
     * Adds data from all quizzes within a passed list of quizzes to the draft
     * quiz table
     *
     * @param inDraftQuiz Arraylist of all quizzes to be added to the table
     */
    private void displayDraftQuizzes(ArrayList<quizsystem.db.Quiz> inDraftQuiz) {
        clearDraftQuizTable();
        inDraftQuiz.forEach((quiz) -> {
            addDraftQuiz(quiz);
        });
    }

    /**
     * Adds data from all quizzes within a passed list of quizzes to the quiz
     * table
     *
     * @param inQuiz ArrayList of all quizzes to be added to the table
     */
    private void displayQuizzes(ArrayList<quizsystem.db.Quiz> inQuiz) {
        clearQuizTable();
        inQuiz.forEach((quiz) -> {
            addQuiz(quiz);
        });
    }

    /**
     * Searches the list of completed quizzes for quizzes that match search
     * terms then display results on GUI
     *
     * @param name Name of the quiz to search by
     * @param lectName Name of the quiz creating lecturer to search by
     * @param topic The topic of the quiz
     */
    public void searchQuiz(String name, String lectName, String topic) {
        searchQuiz.clear();
        ArrayList<quizsystem.db.Quiz> searchList1 = searchByName(name, quizzes);
        ArrayList<quizsystem.db.Quiz> searchList2 = searchByLectName(lectName, quizzes);
        ArrayList<quizsystem.db.Quiz> searchList3 = searchByTopic(topic, quizzes);
        searchList1.stream().filter((quiz) -> (searchList2.contains(quiz))).forEachOrdered((quiz) -> {
            searchQuiz.add(quiz);
        });
        if (!searchQuiz.isEmpty()) {
            //display the new quizzes
            searched = true;
            //displayCompQuizzes(searchQuiz);
        } else {
            //create a message box saying that no results were found
            Object[] options = {"Ok"};
            JOptionPane.showOptionDialog(this, "No results found", "", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        }
    }

    /**
     * Search through the list of quizzes and find matching quizzes.
     */
    public void advSearch() {
        AdvSearch advSearch = new AdvSearch(this, true);
        advSearch.setVisible(true);
        searchQuiz(advSearch.getName(), advSearch.getLecturer(), advSearch.getTopic());
        advSearch.dispose();
    }

    /**
     * Search through each quiz in a list and filter any quizzes with names that
     * contain a search keyword
     *
     * @param name The name of the quiz to search by
     * @param quizList The full list of quizzes to search through
     * @return The list of quizzes with names that contain that keyword
     */
    public ArrayList<quizsystem.db.Quiz> searchByName(String name, ArrayList<quizsystem.db.Quiz> quizList) {
        ArrayList<quizsystem.db.Quiz> tempList = new ArrayList<>();
        if (!name.equals("Default")) {
            quizList.stream().filter((quiz) -> (quiz.getName().contains(name))).forEachOrdered((quiz) -> {
                tempList.add(quiz);
            });
        }
        return tempList;
    }

    /**
     * Search through each quiz in a list and filter any quizzes with lecturer
     * names that contain a search keyword
     *
     * @param lectName The name of the lecturer to search by
     * @param quizList The full list of quizzes to search through
     * @return The list of quizzes with lecturer names that contain that keyword
     */
    public ArrayList<quizsystem.db.Quiz> searchByLectName(String lectName, ArrayList<quizsystem.db.Quiz> quizList) {
        ArrayList<quizsystem.db.Quiz> tempList = new ArrayList<>();
        if (!lectName.equals("Default")) {
            quizList.stream().filter((quiz) -> (quiz.getLecturerName().contains(lectName))).forEachOrdered((quiz) -> {
                tempList.add(quiz);
            });
        }
        return tempList;
    }

    /**
     * Search through each quiz in a list and filter any quizzes with topics
     * that contain a search keyword
     *
     * @param topic The topic of the quiz to search by
     * @param quizList The full list of quizzes to search through
     * @return The list of quizzes with topics that contain that keyword
     */
    public ArrayList<quizsystem.db.Quiz> searchByTopic(String topic, ArrayList<quizsystem.db.Quiz> quizList) {
        ArrayList<quizsystem.db.Quiz> tempList = new ArrayList<>();
        if (!topic.equals("Default")) {
            quizList.stream().filter((quiz) -> (quiz.getTopic().contains(topic))).forEachOrdered((quiz) -> {
                tempList.add(quiz);
            });
        }
        return tempList;
    }

    /**
     * Withdraw a quiz, revert it back to its draft version and delete all
     * attempt data appended to it.
     */
    public void withdrawQuiz() {
        //revert quiz to draft
        //write to db
        draftQuiz = loadDraftQuizzes();
        quizzes = loadQuizzes();
        displayDraftQuizzes(draftQuiz);
        displayQuizzes(quizzes);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pnlSelectQuiz = new javax.swing.JPanel();
        btnWithdrawQuiz = new javax.swing.JButton();
        lblSelQuizTitle = new javax.swing.JLabel();
        btnReviewAnswers = new javax.swing.JButton();
        btnAdvSearch = new javax.swing.JButton();
        scrpnlQuiz = new javax.swing.JScrollPane();
        tblQuiz = new javax.swing.JTable();
        pnlMiscOperations = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        pnlDraftQuiz = new javax.swing.JPanel();
        scrpnlDraftQuiz = new javax.swing.JScrollPane();
        tblDraftQuiz = new javax.swing.JTable();
        btnCreateQuiz = new javax.swing.JButton();
        lblDraftQuizTitle = new javax.swing.JLabel();
        btnEditQuiz = new javax.swing.JButton();

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

        pnlSelectQuiz.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnWithdrawQuiz.setText("Revert to Draft");
        btnWithdrawQuiz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWithdrawQuizActionPerformed(evt);
            }
        });

        lblSelQuizTitle.setText("Review Created Quizzes");

        btnReviewAnswers.setText("Review Submission");

        btnAdvSearch.setText("Advanced Search");
        btnAdvSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdvSearchActionPerformed(evt);
            }
        });

        tblQuiz.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrpnlQuiz.setViewportView(tblQuiz);

        javax.swing.GroupLayout pnlSelectQuizLayout = new javax.swing.GroupLayout(pnlSelectQuiz);
        pnlSelectQuiz.setLayout(pnlSelectQuizLayout);
        pnlSelectQuizLayout.setHorizontalGroup(
            pnlSelectQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSelectQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSelectQuizLayout.createSequentialGroup()
                        .addComponent(lblSelQuizTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlSelectQuizLayout.createSequentialGroup()
                        .addComponent(btnWithdrawQuiz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReviewAnswers)
                        .addGap(37, 37, 37)
                        .addComponent(btnAdvSearch))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSelectQuizLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(scrpnlQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlSelectQuizLayout.setVerticalGroup(
            pnlSelectQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSelQuizTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrpnlQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(pnlSelectQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnWithdrawQuiz)
                    .addComponent(btnReviewAnswers)
                    .addComponent(btnAdvSearch))
                .addContainerGap())
        );

        pnlMiscOperations.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnLogout.setText("Log Out");
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

        javax.swing.GroupLayout pnlMiscOperationsLayout = new javax.swing.GroupLayout(pnlMiscOperations);
        pnlMiscOperations.setLayout(pnlMiscOperationsLayout);
        pnlMiscOperationsLayout.setHorizontalGroup(
            pnlMiscOperationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMiscOperationsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLogout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMiscOperationsLayout.setVerticalGroup(
            pnlMiscOperationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMiscOperationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMiscOperationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout)
                    .addComponent(btnExit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDraftQuiz.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblDraftQuiz.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrpnlDraftQuiz.setViewportView(tblDraftQuiz);

        btnCreateQuiz.setText("Create Quiz");

        lblDraftQuizTitle.setText("Draft Quizzes");

        btnEditQuiz.setText("Edit Quiz");

        javax.swing.GroupLayout pnlDraftQuizLayout = new javax.swing.GroupLayout(pnlDraftQuiz);
        pnlDraftQuiz.setLayout(pnlDraftQuizLayout);
        pnlDraftQuizLayout.setHorizontalGroup(
            pnlDraftQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDraftQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDraftQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrpnlDraftQuiz)
                    .addGroup(pnlDraftQuizLayout.createSequentialGroup()
                        .addGroup(pnlDraftQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDraftQuizTitle)
                            .addGroup(pnlDraftQuizLayout.createSequentialGroup()
                                .addComponent(btnCreateQuiz)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditQuiz)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDraftQuizLayout.setVerticalGroup(
            pnlDraftQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDraftQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDraftQuizTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(scrpnlDraftQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(pnlDraftQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateQuiz)
                    .addComponent(btnEditQuiz))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMiscOperations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlSelectQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDraftQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlSelectQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDraftQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMiscOperations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdvSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdvSearchActionPerformed
        advSearch();
    }//GEN-LAST:event_btnAdvSearchActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        logout();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnWithdrawQuizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWithdrawQuizActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to withdraw this quiz? All data surrounding quiz attempts will be lost", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            //Withdraw the quiz
        }
    }//GEN-LAST:event_btnWithdrawQuizActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdvSearch;
    private javax.swing.JButton btnCreateQuiz;
    private javax.swing.JButton btnEditQuiz;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnReviewAnswers;
    private javax.swing.JButton btnWithdrawQuiz;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblDraftQuizTitle;
    private javax.swing.JLabel lblSelQuizTitle;
    private javax.swing.JPanel pnlDraftQuiz;
    private javax.swing.JPanel pnlMiscOperations;
    private javax.swing.JPanel pnlSelectQuiz;
    private javax.swing.JScrollPane scrpnlDraftQuiz;
    private javax.swing.JScrollPane scrpnlQuiz;
    private javax.swing.JTable tblDraftQuiz;
    private javax.swing.JTable tblQuiz;
    // End of variables declaration//GEN-END:variables
}
