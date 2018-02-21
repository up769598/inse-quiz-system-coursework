package quizsystem.GUI.student;

public class QuizPreview extends javax.swing.JFrame {
        
    private final quizsystem.Quiz quiz;
    private final quizsystem.Student student;

    /**
     * Creates new form QuizPreview
     * @param inQuiz Quiz being taken by the student
     * @param inStudent The student account being accessed
     */
    public QuizPreview(quizsystem.Quiz inQuiz, quizsystem.Student inStudent) {
        initComponents();
        quiz = inQuiz;
        student = inStudent;
        lblName.setText("Name: " + quiz.getName());
        lblLecturerName.setText("Lecturer: " + quiz.getLecturerName());
        lblTime.setText(Float.toString(quiz.getTimelimit()));
        lblNumQuestions.setText("Total Number of Questions: " + Integer.toString(quiz.getQuestions().length));
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        lblLecturerName = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblNumQuestions = new javax.swing.JLabel();
        btnTakeQuiz = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblName.setText("(Insert Quiz Name Here)");
        lblName.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblLecturerName.setText("(Insert Lecturer Name Here)");
        lblLecturerName.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTime.setText("(Insert Time Limit Here)");
        lblTime.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNumQuestions.setText("(Insert Number of Questions Here)");
        lblNumQuestions.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnTakeQuiz.setText("Take Quiz");
        btnTakeQuiz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTakeQuizActionPerformed(evt);
            }
        });

        btnBack.setText("Go Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLecturerName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                    .addComponent(lblTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                    .addComponent(lblNumQuestions, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTakeQuiz)
                .addGap(68, 68, 68)
                .addComponent(btnBack)
                .addGap(113, 113, 113))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblLecturerName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNumQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnTakeQuiz))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTakeQuizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTakeQuizActionPerformed
        //Continue onto taking the quiz
        Question question = new Question(this, true, quiz);
    }//GEN-LAST:event_btnTakeQuizActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        //Return to the student interface
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnTakeQuiz;
    private javax.swing.JLabel lblLecturerName;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumQuestions;
    private javax.swing.JLabel lblTime;
    // End of variables declaration//GEN-END:variables
}
