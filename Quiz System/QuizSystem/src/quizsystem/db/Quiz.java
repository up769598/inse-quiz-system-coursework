package quizsystem.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class Quiz extends Model {
    private List<String> _questions;
    private HashMap<Integer, List<String>> _answers;
    
    public Quiz(ResultRow row) throws SQLException {
        super(row);
        
        // TODO grab questions & answers, plus any other applicable relations
    }
//    protected int quizID;
//    protected String[] questions;
//
//    /**
//     * first is for the question number second is the answer number
//     */
//    protected String[][] answers;
//    protected int[] correctAnswerIndex;
//    protected int length;
//    protected int score;
//    protected int currentQuestion;
//    protected String name;
//    protected String lecturerName;
//    protected float timelimit;
//
//    public Quiz() {
//
//    }
//
//    public Quiz(int db_QuizID, String[] db_Questions, String[][] db_Answers, int[] db_CorrectAnswer, String db_Name, String db_LecturerName, float db_Timelimit) {
//        this.length = db_Questions.length;
//        this.questions = db_Questions;
//        this.correctAnswerIndex = db_CorrectAnswer;
//        this.quizID = db_QuizID;
//        this.score = 0;
//        this.currentQuestion = 0;
//        this.name = db_Name;
//        this.lecturerName = db_LecturerName;
//        this.timelimit = db_Timelimit;
//    }
//
//    public float getTimelimit() {
//        return timelimit;
//    }
//
//    public void setTimelimit(float timelimit) {
//        this.timelimit = timelimit;
//    }
//    
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLecturerName() {
//        return lecturerName;
//    }
//
//    public void setLecturerName(String lecturerName) {
//        this.lecturerName = lecturerName;
//    }
//
//    public String[] getQuestions() {
//        return questions;
//    }
//
//    public void setQuestions(String[] questions) {
//        this.questions = questions;
//    }
//
//    public String getQuestion(int questionNo) {
//        return this.questions[questionNo];
//    }
//
//    public String[] getAnswers(int questionNo) {
//        return this.answers[questionNo];
//    }
//
//    public boolean checkAnswer(int questionNo, int chosenAnswer) {
//        return correctAnswerIndex[questionNo] == chosenAnswer;
//    }
//
//    public void upScore() {
//        this.score++;
//    }
//
//    public int getScore() {
//        return this.score;
//    }
//
//    public int getCurrentQuestion() {
//        return this.currentQuestion;
//    }
//
//    public void setCurrentQuestion(int questionIndex) {
//        this.currentQuestion = questionIndex;
//    }
}
