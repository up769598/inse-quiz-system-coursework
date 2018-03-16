package quizsystem.GUI.student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import quizsystem.GUI.LoginRegister;
import quizsystem.GUI.ReviewAnswers;
import quizsystem.db.*;

public class StudentInterface extends javax.swing.JFrame {

    private final DefaultTableModel modelSetQuiz;
    private final DefaultTableModel modelCompQuiz;
    private ArrayList<Quiz> setQuiz;
    private ArrayList<Quiz> compQuiz;
    private final ArrayList<Quiz> searchQuiz;
    private boolean searched;
    private final String username;
    private User user = null;

    /**
     * Creates new form StudentInterface, initialises the ArrayLists and sets up
     * the model for the two gui tables.
     *
     * @param inUsername The username of the student who is logged in
     */
    public StudentInterface(String inUsername) {
        initComponents();
        searchQuiz = new ArrayList<>();
        searched = false;
        username = inUsername;
        try {
            user = User.getByEmail(username);
        } catch (SQLException ex) {
            System.out.println("[WARN] QuizSystem.GUI.lecturer.LecturerInterface encountered SQLException:");
            System.out.println(ex);
        }
        loadSetQuizzes();
        loadCompQuizzes();
        Object[] colSetQuiz = {"Lecturer", "Name"};
        modelSetQuiz = new DefaultTableModel(colSetQuiz, 0);
        tblSetQuiz.setModel(modelSetQuiz);

        Object[] colCompQuiz = {"Lecturer", "Name", "Mark"};
        modelCompQuiz = new DefaultTableModel(colCompQuiz, 0);
        tblCompQuiz.setModel(modelCompQuiz);

        displaySetQuizzes(setQuiz);
        displayCompQuizzes(compQuiz);
    }

    public void loadSetQuizzes() {
        //Get all quizzes from the database that are able to be taken by the student but have not yet completed
        //Load the results in the setQuiz arraylist
        try {
            DatabaseHandler db = new DatabaseHandler();
            setQuiz = db.getQuizzesForStudent(user.getUserId(), QuizState.INCOMPLETE);
        } catch (SQLException ex) {
            System.out.println("[WARN] QuizSystem.GUI.student.StudentInterface encountered SQLException:");
            System.out.println(ex);
        }
    }

    public void loadCompQuizzes() {
        //Get all quizzes from the database that have results attached to them
        //Load the results into the compQuiz arraylist
        try {
            User user = User.getByEmail(username);
            DatabaseHandler db = new DatabaseHandler();
            compQuiz = db.getQuizzesForStudent(user.getUserId(), QuizState.COMPLETED);
        } catch (SQLException ex) {
            System.out.println("[WARN] QuizSystem.GUI.student.StudentInterface encountered SQLException:");
            System.out.println(ex);
        }
    }

    /**
     * Appends data from a quiz onto a new row on the set quiz table
     *
     * @param inQuiz The Quiz to be appended onto the set quiz table
     */
    private void addSetQuiz(quizsystem.db.Quiz inQuiz) {
        Object[] data = {inQuiz.getLecturerName(), inQuiz.getName()};
        modelSetQuiz.addRow(data);
    }

    /**
     * Appends data from a quiz onto a new row on the complete quiz table
     *
     * @param inQuiz The Quiz to be appended onto the complete quiz table
     */
    private void addCompQuiz(quizsystem.db.Quiz inQuiz) {
        int totalMark = 0;
        try {
            DatabaseHandler db = new DatabaseHandler();
            List<AttemptAnswer> tempResults = db.getQuizAttempt(inQuiz.getQuizID(), User.getByEmail(username).getUserId());
            for (AttemptAnswer tempResult : tempResults) {
                totalMark += tempResult.getMarks();
            }
        } catch (SQLException ex) {
            System.out.println("[WARN] QuizSystem.GUI.lecturer.SelectResult encountered SQLException:");
            System.out.println(ex);
        }

        Object[] data = {inQuiz.getLecturerName(), inQuiz.getName(), totalMark};
        modelCompQuiz.addRow(data);
    }

    /**
     * Clears the set quiz table of all data.
     */
    private void clearSetQuizTable() {
        modelSetQuiz.setRowCount(0);
    }

    /**
     * Clears the the completed quiz table of all data.
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
        if (!inSetQuiz.isEmpty()) {
            inSetQuiz.forEach((quiz) -> {
                addSetQuiz(quiz);
            });
        }
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
     * Searches the list of completed quizzes for quizzes that match search
     * terms then display results on gui
     *
     * @param name Name of the quiz to search by
     * @param lectName Name of the quiz creating lecturer to search by
     * @param topic The topic of the quiz
     */
    public void searchCompQuiz(String name, String lectName, String topic) {
        searchQuiz.clear();
        searchByName(name);
        searchByLectName(lectName);
        searchByTopic(topic);
        if (!searchQuiz.isEmpty()) {
            //display the new quizzes
            searched = true;
            displayCompQuizzes(searchQuiz);
        } else {
            //create a message box saying that no results were found
            Object[] options = {"Ok"};
            JOptionPane.showOptionDialog(this, "No results found", "", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        }
    }

    /**
     * Searches through the list of completed quizzes to find quizzes that have names that
     * contain the search term
     *
     * @param name Name of the quiz used as the search term
     */
    public void searchByName(String name) {
        if (!"".equals(name)) {
            if (!name.equals("Default")) {
                for (Quiz quiz : compQuiz) {
                    if (quiz.getName().contains(name)) {
                        searchQuiz.add(quiz);
                    }
                }
            }
        }
    }

    /**
     * Searches through the list of completed quizzes to find quizzes that have lecturer
     * names that contain the search term
     *
     * @param lectName Name of the quiz creator lecturer used as a search term
     */
    public void searchByLectName(String lectName) {
        if (!"".equals(lectName)) {
            if (!lectName.equals("Default")) {
                for (Quiz quiz : compQuiz) {
                    if (quiz.getLecturerName().contains(lectName)) {
                        searchQuiz.add(quiz);
                    }
                }
            }
        }
    }

    public void searchByTopic(String topic) {
        if (!"".equals(topic)) {
            if (!topic.equals("Default")) {
                for (Quiz quiz : compQuiz) {
                    if (quiz.getTopic().contains(topic)) {
                        searchQuiz.add(quiz);
                    }
                }
            }
        }
    }

    /**
     * Clear the table of all quizzes found in the advanced search and reset the
     * table to display all completed quizzes.
     */
    public void clearSearch() {
        searched = false;
        displayCompQuizzes(compQuiz);
    }

    /**
     * Create a new advanced search window and upon window close, display
     * results of search to the completed quiz table.
     */
    public void advSearch() {
        AdvSearch advSearch = new AdvSearch(this, true);
        advSearch.setVisible(true);
        searchCompQuiz(advSearch.getName(), advSearch.getLecturer(), advSearch.getTopic());
        advSearch.dispose();
    }

    /**
     * Log out of the system and return to the Login/Register GUI
     */
    public void Logout() {
        LoginRegister login = new LoginRegister();
        login.setVisible(true);
        this.dispose();
    }

    /**
     * Get the quiz selected by the user and load the quiz preview window to
     * allow the user to take the quiz.
     */
    public void takeQuiz() {
        quizsystem.db.Quiz quiz = setQuiz.get(tblSetQuiz.getSelectedRow());
        quizsystem.GUI.student.QuizPreview qp = new quizsystem.GUI.student.QuizPreview(quiz, username);
    }

    public quizsystem.db.Quiz getSetQuiz() {
        return setQuiz.get(tblSetQuiz.getSelectedRow());
    }

    public quizsystem.db.Quiz getCompQuiz() {
        Quiz quiz;
        if (searched) {
            quiz = searchQuiz.get(tblCompQuiz.getSelectedRow());
        } else {
            quiz = compQuiz.get(tblCompQuiz.getSelectedRow());
        }
        return quiz;
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
        pnlCompQuiz = new javax.swing.JPanel();
        lblCompQuizTitle = new javax.swing.JLabel();
        srpnlCompQuiz = new javax.swing.JScrollPane();
        tblCompQuiz = new javax.swing.JTable();
        btnAdvSearch = new javax.swing.JButton();
        btnReviewAnswers = new javax.swing.JButton();
        btnClearSearch = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnRefreshTables = new javax.swing.JButton();

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
        setResizable(false);

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
                .addComponent(srpnlSetQuiz)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSelectQuiz)
                .addContainerGap())
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

        btnClearSearch.setText("Clear Search");
        btnClearSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCompQuizLayout = new javax.swing.GroupLayout(pnlCompQuiz);
        pnlCompQuiz.setLayout(pnlCompQuizLayout);
        pnlCompQuizLayout.setHorizontalGroup(
            pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(srpnlCompQuiz)
                    .addGroup(pnlCompQuizLayout.createSequentialGroup()
                        .addComponent(lblCompQuizTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCompQuizLayout.createSequentialGroup()
                        .addGroup(pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClearSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdvSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReviewAnswers)))
                .addContainerGap())
        );
        pnlCompQuizLayout.setVerticalGroup(
            pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompQuizLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCompQuizTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(srpnlCompQuiz, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlCompQuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdvSearch)
                    .addComponent(btnReviewAnswers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClearSearch)
                .addContainerGap())
        );

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

        btnRefreshTables.setText("Refresh Tables");
        btnRefreshTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshTablesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSetQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLogout)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefreshTables, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLogout)
                            .addComponent(btnExit)
                            .addComponent(btnRefreshTables))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectQuizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectQuizActionPerformed
        //Selects the quiz pressed on the table and continues onto taking that quiz
        Quiz quiz = getSetQuiz();
        QuizPreview qp = new QuizPreview(quiz, username);
        qp.setVisible(true);
    }//GEN-LAST:event_btnSelectQuizActionPerformed

    private void btnAdvSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdvSearchActionPerformed
        advSearch();
    }//GEN-LAST:event_btnAdvSearchActionPerformed

    private void btnReviewAnswersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReviewAnswersActionPerformed
        //Review answers given for that completed quiz
        Quiz quiz = getCompQuiz();
        try {
            DatabaseHandler db = new DatabaseHandler();
            List<AttemptAnswer> results = db.getQuizAttempt(quiz.getQuizID(), User.getByEmail(username).getUserId());
            ReviewAnswers ra = new ReviewAnswers(this, true, quiz, results);
            ra.setVisible(true);

        } catch (SQLException ex) {
            System.out.println("[WARN] QuizSystem.GUI.lecturer.SelectResult encountered SQLException:");
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnReviewAnswersActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Logout();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        //Exit the system
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnClearSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSearchActionPerformed
        clearSearch();
    }//GEN-LAST:event_btnClearSearchActionPerformed

    private void btnRefreshTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshTablesActionPerformed
        loadSetQuizzes();
        loadCompQuizzes();
        displaySetQuizzes(setQuiz);
        displayCompQuizzes(compQuiz);
    }//GEN-LAST:event_btnRefreshTablesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdvSearch;
    private javax.swing.JButton btnClearSearch;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRefreshTables;
    private javax.swing.JButton btnReviewAnswers;
    private javax.swing.JButton btnSelectQuiz;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCompQuizTitle;
    private javax.swing.JLabel lblSetQuizTitle;
    private javax.swing.JPanel pnlCompQuiz;
    private javax.swing.JPanel pnlSetQuiz;
    private javax.swing.JScrollPane srpnlCompQuiz;
    private javax.swing.JScrollPane srpnlSetQuiz;
    private javax.swing.JTable tblCompQuiz;
    private javax.swing.JTable tblSetQuiz;
    // End of variables declaration//GEN-END:variables
}
