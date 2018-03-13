package quizsystem.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Quiz extends Model {
    private final List<Question> _questions;
    private final HashMap<String, List<Answer>> _answers;
    private final Lecturer _lecturer;
    private int _currentQuestion;
    
    /**
     * Construct a Quiz instance from the provided ResultRow and retrieve associated data for it.
     * @param row the ResultRow returned by the database driver
     * @throws SQLException 
     */
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
        
        this._lecturer = Lecturer.forQuiz(handler, this.get("usrID"));
        
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
    
    /**
     * Get the texts of answers to the specified question number.
     * @param questionNumber the 1-based question index to find answers for
     * @return               a String array of answer texts
     */
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
    
    public String getTopic(){
        return this.get("topic");
    }
    
    public float getTimeLimit() {
        String limit = this.get("timeLimit");
        return Float.parseFloat(limit);
    }
    
    public boolean isDraft() {
        return this.get("draft").equals("1");
    }

    /**
     * Create an instance of Quiz using the specified attributes and persist it to the underlying database.
     * @param attributes a map of attributes where keys are database column names
     * @return           the constructed Quiz instance
     * @throws SQLException 
     */
    public static Quiz create(HashMap<String, String> attributes) throws SQLException {
        List<String> reselectors = Model.calculateReselectors(Arrays.asList("quizID", "usrID", "timeLimit",
          "topic", "draft"), attributes.keySet());
        
        if (attributes.containsKey("draft")) {
            attributes.put("draft", (attributes.get("draft").equals("true") ? "1" : "0"));
        }
        
        ResultRow row = Model.create("Quizzes", attributes, reselectors, "quizID");
        return row == null ? null : new Quiz(row);
    }
}
