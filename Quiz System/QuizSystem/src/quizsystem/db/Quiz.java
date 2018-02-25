package quizsystem.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Quiz extends Model {
    private List<Question> _questions;
    private HashMap<String, List<Answer>> _answers;
    private Lecturer _lecturer;
    private int _currentQuestion;
    
    public Quiz(ResultRow row) throws SQLException {
        super(row);
        
        this._answers = new HashMap<>();
        DatabaseHandler handler = new DatabaseHandler();
        
        List<Question> questions = Question.questionsInQuiz(handler, this.get("quizID"));
        this._questions = questions;
        List<String> questionIDs = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            questionIDs.add(questions.get(i).getQuestionID());
        }
        
        List<Answer> answers = Answer.answersForQuestions(handler, questionIDs);
        for (int i = 0; i < answers.size(); i++) {
            String questionID = answers.get(i).getQuestionID();
            
            if (!this._answers.containsKey(questionID)) {
                this._answers.put(questionID, new ArrayList<>());
            }
            
            this._answers.get(questionID).add(answers.get(i));
        }
        
        this._lecturer = Lecturer.forQuiz(handler, this.get("lecturerID"));
        
        this._currentQuestion = 1;
    }
    
    public int getCurrentQuestion() {
        return this._currentQuestion;
    }
    
    public void setCurrentQuestion(int newQuestion) {
        this._currentQuestion = newQuestion;
    }
    
    public List<Question> getQuestions() {
        return this._questions;
    }
    
    public String getQuestion(int questionNumber) {
        return this._questions.get(questionNumber - 1).getQuestionText();
    }
    
    public String[] getAnswers(int questionNumber) {
        Question question = this._questions.get(questionNumber - 1);
        String id = question.getQuestionID();
        List<Answer> answers = this._answers.get(id);
        List<String> texts = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            texts.add(answers.get(i).getAnswerText());
        }
        return (String[])texts.toArray();
    }
    
    public String getName() {
        return this.get("name");
    }
    
    public String getLecturerName() {
        return this._lecturer.getName();
    }
    
    public float getTimeLimit() {
        String limit = this.get("timeLimit");
        return Float.parseFloat(limit);
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
//    protected int _currentQuestion;
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
//        this._currentQuestion = 0;
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
//        return this._currentQuestion;
//    }
//
//    public void setCurrentQuestion(int questionIndex) {
//        this._currentQuestion = questionIndex;
//    }
}
