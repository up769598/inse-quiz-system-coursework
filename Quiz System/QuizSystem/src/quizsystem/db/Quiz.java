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
     * Update the current Quiz with new attributes.
     * @param newAttributes a HashMap of new values to set, where keys are column names
     * @throws SQLException
     */
    public void update(HashMap<String, String> newAttributes) throws SQLException {
        super.update("Quizzes", "quizID", this.get("quizID"), newAttributes);
    }
    
    /**
     * Revert the current quiz to a draft state and delete all results data belonging to it.
     * @throws SQLException 
     */
    public void revertToDraft() throws SQLException {
        HashMap<String, String> updatedAttributes = new HashMap<>();
        updatedAttributes.put("draft", "1");
        this.update(updatedAttributes);
        
        String deleteAttempts = "DELETE FROM AttemptAnswers WHERE quizID = ?;";
        String deleteCompletions = "DELETE FROM QuizCompletions WHERE quizID = ?;";
        List<String> params = Arrays.asList(this.get("quizID"));
        DatabaseHandler handler = new DatabaseHandler();
        
        handler.executeManipulator(deleteAttempts, params);
        handler.executeManipulator(deleteCompletions, params);
    }
    
    /**
     * Find a quiz by its ID.
     * @param id the quiz ID to search for
     * @return   a Quiz object for the requested quiz, or null if none was found
     * @throws SQLException 
     */
    public static Quiz getById(String id) throws SQLException {
        ResultRow row = Model.getById("Quizzes", "quizID", id);
        return row == null ? null : new Quiz(row);
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
    
    /**
     * Get a list of quizzes created by the specified lecturer.
     * @param usrID  the user ID of the lecturer
     * @param drafts a DraftState - ANY for all quizzes, DRAFT for just drafts, or LIVE for just live quizzes
     * @return       a List of Quizzes
     * @throws SQLException
     */
    public static List<Quiz> getQuizzesForLecturer(String usrID, DraftState drafts) throws SQLException {
        String query = "SELECT * FROM Quizzes WHERE usrID = ?";
        List<String> params = Arrays.asList(usrID);
        if (drafts != DraftState.ANY) {
            query += " AND draft = ?;";
            params.add(drafts == DraftState.DRAFT ? "1" : "0");
        }
        else {
            query += ";";
        }
        DatabaseHandler handler = new DatabaseHandler();
        
        ArrayList<ResultRow> rows = handler.executeParameterized(query, params);
        List<Quiz> quizzes = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            quizzes.add(new Quiz(rows.get(i)));
        }
        return quizzes;
    }
}
