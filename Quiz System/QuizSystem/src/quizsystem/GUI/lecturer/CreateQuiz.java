package quizsystem.GUI.lecturer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JTextField;
import quizsystem.db.Quiz;

public class CreateQuiz extends javax.swing.JFrame implements ActionListener{

    private Quiz quiz;
    private String[] questions = new String[30];
    private String[][] answers = new String[30][8];
    private Integer[] correctAnswers = new Integer[30];
    private int currentQuestion = 0;
    private int numQuestions = 5;
    private String username;

    public CreateQuiz(String inUsername) {
        initComponents();
        username = inUsername;
    }

    public CreateQuiz(Quiz inQuiz, String inUsername) {
        initComponents();
        //Load whats already complete into GUI
        quiz = inQuiz;
        username = inUsername;
        sldTime.setValue((int) quiz.getTimeLimit());
        tfName.setText(quiz.getName());
        numQuestions = quiz.getQuestions().size();
    }

    /**
     * Attempts to get the Quiz name from the GUI.
     * 
     * @return The Quiz name if recovered successfully from the GUI, otherwise "Default"
     */
    public String getQuizName() {
        String name = "Default";
        try {
            name = tfName.getText();
        } catch (NullPointerException ex) {
            //Text field is empty
        }
        return name;
    }
    
    /**
     * Attempts to get the Quiz topic from the GUI.
     * 
     * @return The Quiz topic if recovered successfully from the GUI, otherwise "Default"
     */
    public String getQuizTopic() {
        String topic = "Default";
        try {
            topic = tfTopic.getText();
        } catch (NullPointerException ex) {
            //Text field is empty
        }
        return topic;
    }

    /**
     * Refreshes all the fields on the GUI to display the latest information about the current question.
     */
    public void refresh() {
        setQuestion();
        setAnswers();
        setQuestionNum();
    }

    /**
     * Sets the question text area to display the saved question.
     */
    public void setQuestion() {
        taQuestion.setText(questions[currentQuestion]);
    }

    /**
     * Set the saved answers into each of the answer text fields. 
     */
    public void setAnswers() {
        for (int i = 0; i < 8; i++) {
            String answer = answers[currentQuestion][i];
            if (answer != null) {
                switch (i) {
                    case 0:
                        tfAnswer1.setText(answer);
                        break;
                    case 1:
                        tfAnswer2.setText(answer);
                        break;
                    case 2:
                        tfAnswer3.setText(answer);
                        break;
                    case 3:
                        tfAnswer4.setText(answer);
                        break;
                    case 4:
                        tfAnswer5.setText(answer);
                        break;
                    case 5:
                        tfAnswer6.setText(answer);
                        break;
                    case 6:
                        tfAnswer7.setText(answer);
                        break;
                    case 7:
                        tfAnswer8.setText(answer);
                        break;
                }
            }
        }
    }

    /**
     * Saves entered question and answer data from the GUI.
     */
    public void save() {
        saveQuestion();
        saveAnswers();
        sortAnswers();
        refresh();
    }

    /**
     * Saves the question entered into the question text area in the questions array or a default message if there is no entered question.
     */
    public void saveQuestion() {
        try {
            questions[currentQuestion] = taQuestion.getText();
        } catch (NullPointerException ex) {
            //Text Area is empty
            questions[currentQuestion] = "Please enter a question here";
        }
    }

    /**
     * Saves the answer from each answer text field into the answers array.
     */
    public void saveAnswers() {
        getAnswer(tfAnswer1, 0);
        getAnswer(tfAnswer2, 1);
        getAnswer(tfAnswer3, 2);
        getAnswer(tfAnswer4, 3);
        getAnswer(tfAnswer5, 4);
        getAnswer(tfAnswer6, 5);
        getAnswer(tfAnswer7, 6);
        getAnswer(tfAnswer8, 7);
    }
    
    /**
     * Adds an action listener to each of the radio buttons and assigns its action command.
     */
    public void initialiseRadioButtons(){
        rbAnswer1.setActionCommand("0");
        rbAnswer1.addActionListener(this);
        rbAnswer2.setActionCommand("1");
        rbAnswer2.addActionListener(this);
        rbAnswer3.setActionCommand("2");
        rbAnswer3.addActionListener(this);
        rbAnswer4.setActionCommand("3");
        rbAnswer4.addActionListener(this);
        rbAnswer5.setActionCommand("4");
        rbAnswer5.addActionListener(this);
        rbAnswer6.setActionCommand("5");
        rbAnswer6.addActionListener(this);
        rbAnswer7.setActionCommand("6");
        rbAnswer7.addActionListener(this);
        rbAnswer8.setActionCommand("7");
        rbAnswer8.addActionListener(this);
    }
    
    /**
     * Saves the selected correct answer into the array of correct answers.
     * @param e The radio button that set off the action event.
     */
    @Override
    public void actionPerformed(ActionEvent e){
        correctAnswers[currentQuestion] = Integer.parseInt(e.getActionCommand());
    }

    /**
     * Gets an answer from a passed text field and enters it into its specified place in the answers array.
     * @param tf A text field that an answer will be extracted from and entered into the answers array
     * @param index The ID of the answer being entered into the answers array
     */
    public void getAnswer(JTextField tf, int index) {
        try {
            answers[currentQuestion][index] = tf.getText();
        } catch (NullPointerException ex) {
            //Text Area is empty
            answers[currentQuestion][index] = null;
        }
    }

    /**
     * Sets the current question number onto the GUI.
     */
    public void setQuestionNum() {
        lblQuestionNum.setText(Integer.toString(currentQuestion) + ":" + Integer.toString(numQuestions));
    }

    /**
     * Saves current question data and moves onto the next question provided that the current question being accessed is the last question in the quiz.
     */
    public void nextQuestion() {
        if (currentQuestion + 1 < numQuestions && validateQuestion()) {
            //Cannot navigate further than the max number of added questions and current question must be valid before moving on
        } else {
            save();
            currentQuestion++;
            refresh();
        }
    }

    /**
     * Saves current question data and moves to the previous question provided that the current question being accessed is the first question in the quiz.
     */
    public void prevQuestion() {
        if (currentQuestion -1 < 0 && validateQuestion()){
            //Cannot navigate into -1 question and current question must be valid before moving to the previous question
        } else {
            save();
            currentQuestion--;
            refresh();
        }
    }
    
    /**
     * Validates the current question to make sure that all entered data is valid against specified constraints
     * @return A boolean determining whether the question is valid or not
     */
    public boolean validateQuestion(){
        boolean valid = true;
        int numAnswers = 0;
        for(int i=0;i<8;i++){
            if(answers[currentQuestion] != null){
                numAnswers++;
                if(answers[currentQuestion][i].length()>150){
                    valid = false;
                }
            }
        }
        if(questions[currentQuestion].length() > 400 || correctAnswers[currentQuestion] == null || numAnswers < 2){
            valid = false;
        }
        return valid;
    }
    
    /**
     * Sort all answers for the current question being accessed so that there are no null gaps between entered answers.
     */
    public void sortAnswers(){
        boolean sorted = false;
        while(!sorted){
            sorted = true;
            for(int i=0;i<7;i++){
                if(answers[currentQuestion][i] == null && answers[currentQuestion][i+1] != null){
                    String temp = answers[currentQuestion][i];
                    answers[currentQuestion][i] = answers[currentQuestion][i+1];
                    answers[currentQuestion][i+1] = temp;
                    sorted = false;
                }
            }
            
        }
    }
    
    /**
     * Save and submit the quizzes to the database to be attempted by the student.
     */
    public void saveAndSubmit(){
        HashMap<String,String> quizMap = new HashMap<>();
        quizMap.put("usrID",username);
        quizMap.put("timeLimit", Integer.toString(sldTime.getValue()));
        quizMap.put("draft", "false");
        quizMap.put("name", getQuizName());
        
        HashMap<String,String> questionMap = new HashMap<>();
        questionMap.put("usrID",username);
        questionMap.put("topic",getQuizTopic());
        
        try{
            Quiz newQuiz = Quiz.create(quizMap);
           
        } catch (SQLException ex){
            System.out.println("[WARN] QuizSystem.GUI.lecturer.CreateQuiz encountered SQLException:");
            System.out.println(ex);
        }
        dispose();
        
    }
    
    /**
     * Save and submit the quizzes to the database to be edited later.
     */
    public void saveToDraft(){
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngpCorrectAnswer = new javax.swing.ButtonGroup();
        pnlDetails = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        chbTimer = new javax.swing.JCheckBox();
        sldTime = new javax.swing.JSlider();
        lblSelectTimeInMins = new javax.swing.JLabel();
        lblTopic = new javax.swing.JLabel();
        tfTopic = new javax.swing.JTextField();
        scrpnlQuestion = new javax.swing.JScrollPane();
        taQuestion = new javax.swing.JTextArea();
        btnNextQuestion = new javax.swing.JButton();
        btnPreviousQuestion = new javax.swing.JButton();
        pnlAnswers = new javax.swing.JPanel();
        tfAnswer1 = new javax.swing.JTextField();
        tfAnswer2 = new javax.swing.JTextField();
        tfAnswer3 = new javax.swing.JTextField();
        tfAnswer4 = new javax.swing.JTextField();
        tfAnswer5 = new javax.swing.JTextField();
        tfAnswer6 = new javax.swing.JTextField();
        tfAnswer7 = new javax.swing.JTextField();
        tfAnswer8 = new javax.swing.JTextField();
        lblAnswer1 = new javax.swing.JLabel();
        lblAnswer2 = new javax.swing.JLabel();
        lblAnswer3 = new javax.swing.JLabel();
        lblAnswer4 = new javax.swing.JLabel();
        lblAnswer5 = new javax.swing.JLabel();
        lblAnswer6 = new javax.swing.JLabel();
        lblAnswer7 = new javax.swing.JLabel();
        lblAnswer8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rbAnswer1 = new javax.swing.JRadioButton();
        rbAnswer2 = new javax.swing.JRadioButton();
        rbAnswer3 = new javax.swing.JRadioButton();
        rbAnswer4 = new javax.swing.JRadioButton();
        rbAnswer5 = new javax.swing.JRadioButton();
        rbAnswer6 = new javax.swing.JRadioButton();
        rbAnswer7 = new javax.swing.JRadioButton();
        rbAnswer8 = new javax.swing.JRadioButton();
        btnSaveSubmit = new javax.swing.JButton();
        btnSaveDraft = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblQuestionNumInfo = new javax.swing.JLabel();
        lblQuestionNum = new javax.swing.JLabel();
        btnAddQuestion = new javax.swing.JButton();
        btnRemoveQuestion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlDetails.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblName.setText("Name: ");

        chbTimer.setText("Timer?");
        chbTimer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbTimerItemStateChanged(evt);
            }
        });

        sldTime.setMajorTickSpacing(2);
        sldTime.setMaximum(30);
        sldTime.setMinimum(10);
        sldTime.setPaintLabels(true);
        sldTime.setPaintTicks(true);
        sldTime.setSnapToTicks(true);
        sldTime.setEnabled(false);

        lblSelectTimeInMins.setText("Select Time (in minutes)");

        lblTopic.setText("Topic: ");

        javax.swing.GroupLayout pnlDetailsLayout = new javax.swing.GroupLayout(pnlDetails);
        pnlDetails.setLayout(pnlDetailsLayout);
        pnlDetailsLayout.setHorizontalGroup(
            pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addComponent(lblName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addComponent(chbTimer)
                        .addGap(18, 18, 18)
                        .addComponent(lblSelectTimeInMins)
                        .addGap(18, 18, 18)
                        .addComponent(sldTime, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addComponent(lblTopic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDetailsLayout.setVerticalGroup(
            pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetailsLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chbTimer)
                            .addComponent(lblSelectTimeInMins)))
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sldTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTopic)
                    .addComponent(tfTopic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        taQuestion.setColumns(20);
        taQuestion.setRows(5);
        scrpnlQuestion.setViewportView(taQuestion);

        btnNextQuestion.setText("Next Question");
        btnNextQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextQuestionActionPerformed(evt);
            }
        });

        btnPreviousQuestion.setText("Previous Question");

        pnlAnswers.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAnswer1.setText("1)");

        lblAnswer2.setText("2)");

        lblAnswer3.setText("3)");

        lblAnswer4.setText("4)");

        lblAnswer5.setText("5)");

        lblAnswer6.setText("6)");

        lblAnswer7.setText("7)");

        lblAnswer8.setText("8)");

        jLabel1.setText("Please enter each answer you want, leave blank for any unwanted answer and select the correct answer with the radio buttons on the right");

        btngpCorrectAnswer.add(rbAnswer1);

        btngpCorrectAnswer.add(rbAnswer2);

        btngpCorrectAnswer.add(rbAnswer3);

        btngpCorrectAnswer.add(rbAnswer4);

        btngpCorrectAnswer.add(rbAnswer5);

        btngpCorrectAnswer.add(rbAnswer6);

        btngpCorrectAnswer.add(rbAnswer7);

        btngpCorrectAnswer.add(rbAnswer8);

        javax.swing.GroupLayout pnlAnswersLayout = new javax.swing.GroupLayout(pnlAnswers);
        pnlAnswers.setLayout(pnlAnswersLayout);
        pnlAnswersLayout.setHorizontalGroup(
            pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnswersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAnswersLayout.createSequentialGroup()
                        .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAnswer1)
                            .addComponent(lblAnswer2)
                            .addComponent(lblAnswer3)
                            .addComponent(lblAnswer4)
                            .addComponent(lblAnswer5)
                            .addComponent(lblAnswer6)
                            .addComponent(lblAnswer7)
                            .addComponent(lblAnswer8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAnswersLayout.createSequentialGroup()
                                .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfAnswer7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfAnswer6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfAnswer5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfAnswer4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfAnswer3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfAnswer8)
                                    .addComponent(tfAnswer2, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbAnswer2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rbAnswer3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rbAnswer4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rbAnswer5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rbAnswer6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rbAnswer7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rbAnswer8, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(pnlAnswersLayout.createSequentialGroup()
                                .addComponent(tfAnswer1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbAnswer1))))
                    .addGroup(pnlAnswersLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlAnswersLayout.setVerticalGroup(
            pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAnswersLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rbAnswer8)
                    .addGroup(pnlAnswersLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlAnswersLayout.createSequentialGroup()
                                .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlAnswersLayout.createSequentialGroup()
                                        .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnlAnswersLayout.createSequentialGroup()
                                                .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(pnlAnswersLayout.createSequentialGroup()
                                                        .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(pnlAnswersLayout.createSequentialGroup()
                                                                .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addGroup(pnlAnswersLayout.createSequentialGroup()
                                                                        .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(tfAnswer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(lblAnswer1))
                                                                            .addComponent(rbAnswer1))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(tfAnswer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(lblAnswer2)))
                                                                    .addComponent(rbAnswer2))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(tfAnswer3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(lblAnswer3)))
                                                            .addComponent(rbAnswer3))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(tfAnswer4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(lblAnswer4)))
                                                    .addComponent(rbAnswer4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(tfAnswer5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblAnswer5)))
                                            .addComponent(rbAnswer5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tfAnswer6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblAnswer6)))
                                    .addComponent(rbAnswer6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfAnswer7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAnswer7)))
                            .addComponent(rbAnswer7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlAnswersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfAnswer8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAnswer8))))
                .addContainerGap())
        );

        btnSaveSubmit.setText("Save and Submit");
        btnSaveSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSubmitActionPerformed(evt);
            }
        });

        btnSaveDraft.setText("Save to Draft");

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblQuestionNumInfo.setText("Question:");

        lblQuestionNum.setText("1:5");

        btnAddQuestion.setText("Add Question");
        btnAddQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddQuestionActionPerformed(evt);
            }
        });

        btnRemoveQuestion.setText("Remove Last Question");
        btnRemoveQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveQuestionActionPerformed(evt);
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
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                        .addComponent(btnNextQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPreviousQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveDraft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveSubmit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(scrpnlQuestion)
                                .addComponent(pnlAnswers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblQuestionNumInfo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblQuestionNum, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAddQuestion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRemoveQuestion)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuestionNumInfo)
                    .addComponent(lblQuestionNum)
                    .addComponent(btnAddQuestion)
                    .addComponent(btnRemoveQuestion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrpnlQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlAnswers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNextQuestion)
                    .addComponent(btnPreviousQuestion)
                    .addComponent(btnSaveSubmit)
                    .addComponent(btnSaveDraft)
                    .addComponent(btnExit))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void chbTimerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbTimerItemStateChanged
        if (chbTimer.isSelected() == true) {
            sldTime.setEnabled(true);

        } else {
            sldTime.setEnabled(false);
        }
    }//GEN-LAST:event_chbTimerItemStateChanged

    private void btnNextQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextQuestionActionPerformed
        nextQuestion();
    }//GEN-LAST:event_btnNextQuestionActionPerformed

    private void btnAddQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddQuestionActionPerformed
        if (numQuestions+1 > 30) {
            //Number of questions cannot exceed 30
            //Tell the user they cannot exceed this number of questions
        } else {
            numQuestions++;
        }
    }//GEN-LAST:event_btnAddQuestionActionPerformed

    private void btnRemoveQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveQuestionActionPerformed
        if (numQuestions == 5) {
            //Do not let the user delete the question
        } else {
            questions[numQuestions] = null;
            for (int i = 0; i < 8; i++) {
                answers[numQuestions][i] = null;
            }
            if(currentQuestion == numQuestions){
                currentQuestion--;
                refresh();
            }
            numQuestions--;
        }
    }//GEN-LAST:event_btnRemoveQuestionActionPerformed

    private void btnSaveSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSubmitActionPerformed
       saveAndSubmit();
    }//GEN-LAST:event_btnSaveSubmitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddQuestion;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNextQuestion;
    private javax.swing.JButton btnPreviousQuestion;
    private javax.swing.JButton btnRemoveQuestion;
    private javax.swing.JButton btnSaveDraft;
    private javax.swing.JButton btnSaveSubmit;
    private javax.swing.ButtonGroup btngpCorrectAnswer;
    private javax.swing.JCheckBox chbTimer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAnswer1;
    private javax.swing.JLabel lblAnswer2;
    private javax.swing.JLabel lblAnswer3;
    private javax.swing.JLabel lblAnswer4;
    private javax.swing.JLabel lblAnswer5;
    private javax.swing.JLabel lblAnswer6;
    private javax.swing.JLabel lblAnswer7;
    private javax.swing.JLabel lblAnswer8;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblQuestionNum;
    private javax.swing.JLabel lblQuestionNumInfo;
    private javax.swing.JLabel lblSelectTimeInMins;
    private javax.swing.JLabel lblTopic;
    private javax.swing.JPanel pnlAnswers;
    private javax.swing.JPanel pnlDetails;
    private javax.swing.JRadioButton rbAnswer1;
    private javax.swing.JRadioButton rbAnswer2;
    private javax.swing.JRadioButton rbAnswer3;
    private javax.swing.JRadioButton rbAnswer4;
    private javax.swing.JRadioButton rbAnswer5;
    private javax.swing.JRadioButton rbAnswer6;
    private javax.swing.JRadioButton rbAnswer7;
    private javax.swing.JRadioButton rbAnswer8;
    private javax.swing.JScrollPane scrpnlQuestion;
    private javax.swing.JSlider sldTime;
    private javax.swing.JTextArea taQuestion;
    private javax.swing.JTextField tfAnswer1;
    private javax.swing.JTextField tfAnswer2;
    private javax.swing.JTextField tfAnswer3;
    private javax.swing.JTextField tfAnswer4;
    private javax.swing.JTextField tfAnswer5;
    private javax.swing.JTextField tfAnswer6;
    private javax.swing.JTextField tfAnswer7;
    private javax.swing.JTextField tfAnswer8;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfTopic;
    // End of variables declaration//GEN-END:variables
}
