package quizsystem.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Question extends Model {
    /**
     * Given a quiz ID, find all questions associated with that quiz.
     * @param handler a DatabaseHandler instance
     * @param quizID  the quiz ID to retrieve questions for
     * @return        a List of Questions
     * @throws SQLException 
     */
    public static List<Question> questionsInQuiz(DatabaseHandler handler, String quizID) throws SQLException {
        String query = "SELECT * FROM Questions WHERE quizID = ?;";
        List<String> params = Arrays.asList(quizID);
        List<ResultRow> results = handler.executeParameterized(query, params);
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            questions.add(new Question(results.get(i)));
        }
        return questions;
    }
    
    public Question(ResultRow row) throws SQLException {
        super(row);
    }
    
    public String getQuestionId() {
        return this.get("questionID");
    }
    
    public String getQuestionText() {
        return this.get("question");
    }
    
    public String getQuizId() {
        return this.get("quizID");
    }
    
    /**
     * Update the current Question with new attributes.
     * @param newAttributes new attributes to set
     * @throws SQLException 
     */
    public void update(HashMap<String, String> newAttributes) throws SQLException {
        super.update("Questions", "questionID", this.get("questionID"), newAttributes);
    }
    
    /**
     * Create an instance of Question using the specified attributes and persist it to the underlying database.
     * @param attributes a map of attributes where keys are database column names
     * @return           the constructed Question instance
     * @throws SQLException 
     */
    public static Question create(HashMap<String, String> attributes) throws SQLException {
        List<String> reselectors = Model.calculateReselectors(Arrays.asList("questionID", "quizID", "usrID",
          "category", "title", "question"), attributes.keySet());
        ResultRow row = Model.create("Questions", attributes, reselectors, "questionID");
        return row == null ? null : new Question(row);
    }
}
