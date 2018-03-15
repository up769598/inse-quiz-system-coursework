package quizsystem.db;

import java.io.InvalidObjectException;
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
            questionIDs.add(questions.get(i).getQuestionId());
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
        String id = question.getQuestionId();
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
    
    public String getQuizID(){
        return this.get("quizID");
    }
    
    /**
     * Update the current Quiz with new attributes.
     * @param newAttributes a HashMap of new values to set, where keys are column names
     * @throws SQLException
     */
    public void update(HashMap<String, String> newAttributes) throws SQLException {
        if (newAttributes.containsKey("draft")) {
            newAttributes.put("draft", (newAttributes.get("draft").equals("true") ? "1" : "0"));
        }
        
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
     * Create a record of a quiz attempt.
     * @param student               the completing student
     * @param chosenAnswers         a map between questions in the quiz and the answers the student chose
     * @param allQuestionsCompleted whether or not all questions have been completed
     * @throws SQLException
     */
    public void addAttempt(User student, HashMap<Question, Answer> chosenAnswers, boolean allQuestionsCompleted)
      throws SQLException {
        List<Question> keys = new ArrayList<>(chosenAnswers.keySet());
        String quizID = keys.get(0).getQuizId();
        List<String> insertValues = new ArrayList<>();
        List<String> params = new ArrayList<>();
        for (int i = 0; i < chosenAnswers.size(); i++) {
            Question q = keys.get(i);
            Answer a = chosenAnswers.get(q);
            
            String marks = a.isCorrect() ? "1" : "0";
            params.addAll(Arrays.asList(quizID, student.getUserId(), q.getQuestionId(), a.getAnswerId(), marks));
            List<String> values = Arrays.asList("?", "?", "?", "?", "?");
            
            String insert = "(" + DatabaseHandler.join(values, ", ") + ")";
            insertValues.add(insert);
        }
        
        String insertClause = DatabaseHandler.join(insertValues, ", ");
        String query = "INSERT INTO AttemptAnswers (quizID, usrID, questionID, answerID, marks) VALUES " +
                insertClause + ";";
        DatabaseHandler handler = new DatabaseHandler();
        handler.executeManipulator(query, params);
        
        if (allQuestionsCompleted) {
            List<String> completedParams = Arrays.asList(quizID, student.getUserId());
            String completedInsert = "INSERT INTO QuizCompletions (quizID, usrID) VALUES (?, ?);";
            handler.executeManipulator(completedInsert, completedParams);
        }
    }
    
    /**
     * Delete all associated questions and answers on a quiz.
     * @throws InvalidObjectException
     * @throws SQLException
     */
    public void deleteAssociated() throws InvalidObjectException, SQLException {
        if (this.isDraft()) {
            List<String> params = Arrays.asList(this.get("quizID"));
            String deleteAnswers = "DELETE FROM Answers AS a INNER JOIN Questions AS q WHERE q.quizID = ?;";
            String deleteQuestions = "DELETE FROM Questions WHERE quizID = ?;";
            DatabaseHandler handler = new DatabaseHandler();
            
            handler.executeManipulator(deleteAnswers, params);
            handler.executeManipulator(deleteQuestions, params);
        }
        else {
            throw new InvalidObjectException("Quiz must be in a draft state to delete associations.");
        }
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
        List<String> params = new ArrayList<>();
        params.add(usrID);
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
