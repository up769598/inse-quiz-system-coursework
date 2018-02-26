package quizsystem.GUI.student;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import quizsystem.GUI.LoginRegister;
import quizsystem.db.Quiz;

public class StudentInterface extends javax.swing.JFrame {

    private DefaultTableModel modelSetQuiz;
    private DefaultTableModel modelCompQuiz;
    private ArrayList<quizsystem.db.Quiz> setQuiz;
    private ArrayList<quizsystem.db.Quiz> compQuiz;

    /**
     * Creates new form StudentInterface
     */
    public StudentInterface() {
        initComponents();
        compQuiz = new ArrayList<>();
        setQuiz = new ArrayList<>();

        Object[] colSetQuiz = {"Lecturer", "Name", "Time"};
        modelSetQuiz = new DefaultTableModel(colSetQuiz, 0);
        tblSetQuiz.setModel(modelSetQuiz);

        Object[] colCompQuiz = {"Lecturer", "Name", "Time", "Mark"};
        modelCompQuiz = new DefaultTableModel(colCompQuiz, 0);
        tblCompQuiz.setModel(modelCompQuiz);
    }

    /**
     * Appends data from a quiz onto a new row on the set quiz table
     *
     * @param inQuiz The Quiz to be appended onto the set quiz table
     */
    private void addSetQuiz(quizsystem.db.Quiz inQuiz) {
        Object[] data = {inQuiz.getLecturerName(), inQuiz.getName(), inQuiz.getTimeLimit()};
        modelSetQuiz.addRow(data);
    }

    /**
     * Appends data from a quiz onto a new row on the complete quiz table
     *
     * @param inQuiz The Quiz to be appended onto the complete quiz table
     */
    private void addCompQuiz(quizsystem.db.Quiz inQuiz) {
        Object[] data = {inQuiz.getLecturerName(), inQuiz.getName(), inQuiz.getTimeLimit(), 0}; //Need to add answer
        modelCompQuiz.addRow(data);
    }

    /**
     * Clears the set quiz table of all data
     */
    private void clearSetQuizTable() {
        modelSetQuiz.setRowCount(0);
    }

    /**
     * Clears the the completed quiz table of all data
     */
    private void clearCompQuizTable() {
        modelCompQuiz.setRowCount(0);
    }

    /**
     * Adds data from all quizzes within a passed list of quizzes to the set
     * quiz table
     *
     * @param inSetQuiz Arraylist of all quizzes to be added to the table
     */
    private void displaySetQuizzes(ArrayList<quizsystem.db.Quiz> inSetQuiz) {
        clearSetQuizTable();
        inSetQuiz.forEach((quiz) -> {
            addSetQuiz(quiz);
        });
    }

    /**
     * Adds data from all quizzes within a passed list of quizzes to the
     * completed quiz table
     *
     * @param inCompQuiz ArrayList of all quizzes to be added to the table
     */
    private void displayCompQuizzes(ArrayList<quizsystem.db.Quiz> inCompQuiz) {
        clearCompQuizTable();
        inCompQuiz.forEach((quiz) -> {
            addCompQuiz(quiz);
        });
    }

    /**
     * Searches the list of completed quizzes for quizzes that match search terms then display results on gui
     * @param name Name of the quiz to search by
     * @param lectName Name of the quiz creating lecturer to search by
     */
    public void searchCompQuiz(String name, String lectName) {
        ArrayList<quizsystem.db.Quiz> searchList1 = searchByName(name,compQuiz);
        ArrayList<quizsystem.db.Quiz> searchList2 = searchByName(lectName,setQuiz);
        ArrayList<quizsystem.db.Quiz> tempList = new ArrayList<>();
        searchList1.stream().filter((quiz) -> (searchList2.contains(quiz))).forEachOrdered((quiz) -> {
            tempList.add(quiz);
        });
        if(!tempList.isEmpty()){
            displayCompQuizzes(tempList);
        } else {
            //create a message box saying that no results were found
        }
    }
    
    /**
     * Searches through a list of quizzes to find quizzes that have names that contain the search term
     * @param name Name of the quiz used as the search term
     * @param quizList List of quizzes to search through
     * @return List of quizzes with names containing the search term
     */
    public ArrayList<quizsystem.db.Quiz> searchByName(String name, ArrayList<quizsystem.db.Quiz> quizList){
        ArrayList<quizsystem.db.Quiz> tempList = new ArrayList<>();
        if (!name.equals("Default")) {
            for (Quiz quiz : quizList) {
                if (quiz.getName().contains(name)) {
                    tempList.add(quiz);
                }
            }
        }
        return tempList;
    }
    
    /**
     * Searches through a list of quizzes to find quizzes that have lecturer names that contain the search term
     * @param lectName Name of the quiz creator lecturer used as a search term
     * @param quizList List of quizzes to search through
     * @return List of quizzes with lecturer names containing the search term
     */
    public ArrayList<quizsystem.db.Quiz> searchByLectName(String lectName, ArrayList<quizsystem.db.Quiz> quizList){
        ArrayList<quizsystem.db.Quiz> tempList = new ArrayList<>();
        if (!lectName.equals("Default")) {
            for (Quiz quiz : quizList) {
                if (quiz.getLecturerName().contains(lectName)) {
                    tempList.add(quiz);
                }
            }
        }
        return tempList;
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
        btnAttemptAgain = new javax.swing.JButton();

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
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        srpnlSetQuiz.setViewportView(tblSetQuiz);

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
                                .addComponent(lblSetQuizTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pnlSetQuizLayout.setVerticalGroup(
            pnlSetQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSetQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSetQuizTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(srpnlSetQuiz, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            .addGroup(pnlRandomLayout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(btnRandomQuiz)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlRandomLayout.setVerticalGroup(
            pnlRandomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRandomLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btnRandomQuiz)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCompQuiz.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCompQuizTitle.setText("Review Completed Quizzes");

        tblCompQuiz.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        srpnlCompQuiz.setViewportView(tblCompQuiz);

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

        btnAttemptAgain.setText("Attempt Again");

        javax.swing.GroupLayout pnlCompQuizLayout = new javax.swing.GroupLayout(pnlCompQuiz);
        pnlCompQuiz.setLayout(pnlCompQuizLayout);
        pnlCompQuizLayout.setHorizontalGroup(
            pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(srpnlCompQuiz)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCompQuizLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCompQuizLayout.createSequentialGroup()
                                .addComponent(btnLogout)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExit))
                            .addGroup(pnlCompQuizLayout.createSequentialGroup()
                                .addComponent(btnAdvSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReviewAnswers)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAttemptAgain))))
                    .addGroup(pnlCompQuizLayout.createSequentialGroup()
                        .addComponent(lblCompQuizTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlCompQuizLayout.setVerticalGroup(
            pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCompQuizTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(srpnlCompQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdvSearch)
                    .addComponent(btnReviewAnswers)
                    .addComponent(btnAttemptAgain))
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
                        .addComponent(pnlSetQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlRandom, javax.swing.GroupLayout.PREFERRED_SIZE, 58, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectQuizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectQuizActionPerformed
        //Selects the quiz pressed on the table and continues onto taking that quiz
        // quizsystem.Quiz selectedQuiz = getSelectedQuiz(selectedQuiz);
        //QuizPreview quiz = new QuizPreview(selectedQuiz,student)
        //quiz.setVisible(true);
    }//GEN-LAST:event_btnSelectQuizActionPerformed

    private void btnRandomQuizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRandomQuizActionPerformed
        //Create a random quiz
        // quizsystem.Quiz random = getRandomQuiz();
        //QuizPreview randomQuiz = new QuizPreview(random,student)
        //randomQuiz.setVisible(true);

    }//GEN-LAST:event_btnRandomQuizActionPerformed

    private void btnAdvSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdvSearchActionPerformed
        //Advanced Search through completed quizzes
        AdvSearch advSearch = new AdvSearch(this, true);
        advSearch.setVisible(true);
        String name = advSearch.getName();
        String topic = advSearch.getTopic();
        String lecturer = advSearch.getLecturer();
        advSearch.dispose();
    }//GEN-LAST:event_btnAdvSearchActionPerformed

    private void btnReviewAnswersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReviewAnswersActionPerformed
        //Review answers given for that completed quiz
    }//GEN-LAST:event_btnReviewAnswersActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        //Logout and return to the lgoin screen
        LoginRegister login = new LoginRegister();
        login.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        //Exit the system
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdvSearch;
    private javax.swing.JButton btnAttemptAgain;
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
