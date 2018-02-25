package quizsystem.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Answer extends Model {
    public static List<Answer> answersForQuestions(DatabaseHandler handler, List<String> questionIDs)
      throws SQLException {
        String[] placeholders = new String[questionIDs.size()];
        Arrays.fill(placeholders, "?");
        String inClause = Arrays.toString(placeholders).replace('[', '(').replace(']', ')');
        String query = "SELECT * FROM answers WHERE questionID IN " + inClause + ";";
        
        List<ResultRow> results = handler.executeParameterized(query, questionIDs);
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            answers.add(new Answer(results.get(i)));
        }
        
        return answers;
    }
    
    public Answer(ResultRow row) throws SQLException {
        super(row);
    }
    
    public String getQuestionID() {
        return this.get("questionID");
    }
    
    public String getAnswerText() {
        return this.get("answer");
    }
}
