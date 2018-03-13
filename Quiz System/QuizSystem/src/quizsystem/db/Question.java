package quizsystem.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Question extends Model {
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
    
    public String getQuestionID() {
        return this.get("questionID");
    }
    
    public String getQuestionText() {
        return this.get("question");
    }
    
    public static Question create(HashMap<String, String> attributes) throws SQLException {
        List<String> reselectors = Model.calculateReselectors(Arrays.asList("questionID", "quizID", "usrID",
          "category", "title", "question"), attributes.keySet());
        ResultRow row = Model.create("Questions", attributes, reselectors, "questionID");
        return row == null ? null : new Question(row);
    }
}
