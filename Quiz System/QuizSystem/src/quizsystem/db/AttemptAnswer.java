package quizsystem.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a user's attempt at a single question of a quiz, referencing a quiz, user, question, and selected answer.
 */
public class AttemptAnswer extends Model {
    private DatabaseHandler _handler;
    
    /**
     * Constructs an instance of AttemptAnswer using the given ResultRow by delegation to Model.
     * @param row a ResultRow
     * @throws SQLException 
     */
    public AttemptAnswer(ResultRow row) throws SQLException {
        super(row);
        _handler = new DatabaseHandler();
    }
    
    /**
     * Given a table and column name, fetches the row in that table associated with the current object.
     * @param tableName     the name of the table to fetch a row from
     * @param attributeName the column name containing a unique identifier for the referenced table
     * @return              a single ResultRow
     * @throws SQLException 
     */
    private ResultRow getAssociation(String tableName, String attributeName)
      throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = ? LIMIT 1;", tableName, attributeName);
        List<String> params = Arrays.asList(this.get(attributeName));
        ArrayList<ResultRow> rows = _handler.executeParameterized(query, params);
        
        if (rows.size() > 0) {
            return rows.get(0);
        }
        else {
            return null;
        }
    }
    
    /**
     * Get the Quiz associated with this attempted answer.
     * @return Quiz
     * @throws SQLException 
     */
    public Quiz getQuiz() throws SQLException {
        ResultRow row = this.getAssociation("Quizzes", "quizID");
        return row == null ? null : new Quiz(row);
    }
    
    /**
     * Get the User associated with this attempted answer.
     * @return User
     * @throws SQLException 
     */
    public User getUser() throws SQLException {
        ResultRow row = this.getAssociation("Users", "usrID");
        return row == null ? null : new User(row);
    }
    
    /**
     * Get the Question associated with this attempted answer.
     * @return Question
     * @throws SQLException 
     */
    public Question getQuestion() throws SQLException {
        ResultRow row = this.getAssociation("Questions", "questionID");
        return row == null ? null : new Question(row);
    }
    
    /**
     * Get the Answer associated with this attempted answer.
     * @return Answer
     * @throws SQLException 
     */
    public Answer getAnswer() throws SQLException {
        ResultRow row = this.getAssociation("Answers", "answerID");
        return row == null ? null : new Answer(row);
    }
    
    /**
     * Get the number of marks awarded for this attempted answer.
     * @return Integer
     */
    public int getMarks() {
        return Integer.parseInt(this.get("marks"), 10);
    }
}
