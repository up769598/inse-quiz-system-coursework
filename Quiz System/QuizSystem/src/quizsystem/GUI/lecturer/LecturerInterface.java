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

    private DefaultTableModel modelSetQuiz;
    private DefaultTableModel modelCompQuiz;
    private ArrayList<quizsystem.db.Quiz> setQuiz;
    private ArrayList<quizsystem.db.Quiz> compQuiz;
    private ArrayList<quizsystem.db.Quiz> searchQuiz;
    private boolean searched;
    private final String username;

    /**
     * Create a new Lecturer Interface GUI Window
     * @param inUsername username of the logged in lecturer
     */
    public LecturerInterface(String inUsername) {
        initComponents();
        compQuiz = new ArrayList<>();
        setQuiz = new ArrayList<>();
        searchQuiz = new ArrayList<>();
        searched = false;
        username = inUsername;
    }

    public void Logout() {
        LoginRegister login = new LoginRegister();
        login.setVisible(true);
        this.dispose();
    }

    public void searchCompQuiz(String name, String lectName, String topic) {
        searchQuiz.clear();
        ArrayList<quizsystem.db.Quiz> searchList1 = searchByName(name, compQuiz);
        // changed from searchbyname to seachByLectName 
        ArrayList<quizsystem.db.Quiz> searchList2 = searchByLectName(lectName, setQuiz);
        ArrayList<quizsystem.db.Quiz> searchList3 = searchByTopic(topic, compQuiz);
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

    public void advSearch() {
        AdvSearch advSearch = new AdvSearch(this, true);
        advSearch.setVisible(true);
        //searchCompQuiz(advSearch.getName(),advSearch.getLecturer(),advSearch.getTopic());
        advSearch.dispose();
    }

    public ArrayList<quizsystem.db.Quiz> searchByName(String name, ArrayList<quizsystem.db.Quiz> quizList) {
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

    public ArrayList<quizsystem.db.Quiz> searchByLectName(String lectName, ArrayList<quizsystem.db.Quiz> quizList) {
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

    public ArrayList<quizsystem.db.Quiz> searchByTopic(String topic, ArrayList<quizsystem.db.Quiz> quizList) {
        ArrayList<quizsystem.db.Quiz> tempList = new ArrayList<>();
        if (!topic.equals("Default")) {
            for (Quiz quiz : quizList) {
                if (quiz.getTopic().contains(topic)) {
                    tempList.add(quiz);
                }
            }
        }
        return tempList;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btCreateQuiz = new javax.swing.JButton();
        btEditQuiz1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btReviewSubmisson = new javax.swing.JButton();
        btAdvanceSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btLogout = new javax.swing.JButton();
        btEditQuiz = new javax.swing.JButton();

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

        btCreateQuiz.setText("Create Quiz");

        btEditQuiz1.setText("Withdraw Quiz");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        btReviewSubmisson.setText("Review Submission");

        btAdvanceSearch.setText("Advance Search");
        btAdvanceSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdvanceSearchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Review Quiz Submissions / Edit");

        btLogout.setText("Log Out");
        btLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(btAdvanceSearch)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btReviewSubmisson)
                            .addGap(23, 23, 23)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(btLogout)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btReviewSubmisson)
                    .addComponent(btAdvanceSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btLogout)
                .addContainerGap())
        );

        btEditQuiz.setText("Edit Quiz");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btEditQuiz)
                        .addGap(18, 18, 18)
                        .addComponent(btEditQuiz1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(btCreateQuiz)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btCreateQuiz)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btEditQuiz1)
                            .addComponent(btEditQuiz))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAdvanceSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdvanceSearchActionPerformed
        advSearch();
    }//GEN-LAST:event_btAdvanceSearchActionPerformed

    private void btLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogoutActionPerformed
        Logout();
    }//GEN-LAST:event_btLogoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdvanceSearch;
    private javax.swing.JButton btCreateQuiz;
    private javax.swing.JButton btEditQuiz;
    private javax.swing.JButton btEditQuiz1;
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btReviewSubmisson;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
