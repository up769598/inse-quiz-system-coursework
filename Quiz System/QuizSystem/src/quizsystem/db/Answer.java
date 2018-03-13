package quizsystem.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Answer extends Model {
    /**
     * Given a list of question IDs, find all answers associated with those questions.
     * @param handler     a DatabaseHandler instance
     * @param questionIDs a List of question IDs to retrieve associated answers for
     * @return            a List of Answers
     * @throws SQLException 
     */
    public static List<Answer> answersForQuestions(DatabaseHandler handler, List<String> questionIDs)
      throws SQLException {
        if (questionIDs.size() <= 0) {
            return new ArrayList<>();
        }
        
        String[] placeholders = new String[questionIDs.size()];
        Arrays.fill(placeholders, "?");
        String inClause = Arrays.toString(placeholders).replace('[', '(').replace(']', ')');
        String query = "SELECT * FROM Answers WHERE questionID IN " + inClause + ";";
        
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
    
    /**
     * Create an instance of Answer using the specified attributes and persist it to the underlying database.
     * @param attributes a map of attributes where keys are database column names
     * @return           the constructed Answer instance
     * @throws SQLException 
     */
    public static Answer create(HashMap<String, String> attributes) throws SQLException {
        List<String> reselectors = Model.calculateReselectors(Arrays.asList("answerID", "category", "questionID",
          "answer", "correct"), attributes.keySet());
        
        if (attributes.containsKey("correct")) {
            attributes.put("correct", (attributes.get("correct").equals("true") ? "1" : "0"));
        }
        
        ResultRow row = Model.create("Answers", attributes, reselectors, "answerID");
        return row == null ? null : new Answer(row);
    }
}
