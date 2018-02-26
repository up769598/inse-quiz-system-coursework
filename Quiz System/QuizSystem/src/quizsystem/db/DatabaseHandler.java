package quizsystem.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles connections to the quiz database and converting database data into usable native types.
 */
public class DatabaseHandler {
    private Connection connection;

    /**
     * Construct a new DatabaseHandler instance that can be used for SQL-independent database querying.
     * @throws SQLException 
     */
    public DatabaseHandler() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://52.91.67.192:3306/inse", "inse", "Wv7q7hG9");
        this.connection = con;
    }
    
    /**
     * Execute an SQL query.
     * @param query the raw SQL to execute.
     * @return an ArrayList of ResultRows as returned by the database driver.
     * @throws SQLException 
     */
    protected ArrayList<ResultRow> execute(String query) throws SQLException {
        return execute(query, ResultSet.CONCUR_READ_ONLY);
    }
    
    /**
     * Execute an SQL query with specified result set concurrency.
     * @param query the raw SQL to execute.
     * @param resultSetConcurrency a result set concurrency to apply to the internal ResultSet instance.
     * @return an ArrayList of ResultRows as returned by the database driver.
     * @throws SQLException 
     */
    protected ArrayList<ResultRow> execute(String query, int resultSetConcurrency) throws SQLException {
        Statement stmt = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, resultSetConcurrency);
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData meta = rs.getMetaData();
        ArrayList<ResultRow> rows = new ArrayList<>();

        int colCount = meta.getColumnCount();
        while (rs.next()) {
            ResultRow row = new ResultRow(rs, meta);
            for (int i = 1; i <= colCount; i++) {
                row.addPair(meta.getColumnLabel(i), rs.getString(i));
            }
            rows.add(row);
        }

        return rows;
    }
    
    /**
     * Execute a safely-parameterized SQL query.
     * @param query the raw SQL to execute, with parameters indicated by ?
     * @param parameters a list of parameters to insert at their respective placeholders
     * @return an ArrayList of ResultRows as returned by the database driver
     * @throws SQLException 
     */
    protected ArrayList<ResultRow> executeParameterized(String query, List<String> parameters) throws SQLException {
        return this.executeParameterized(query, ResultSet.CONCUR_READ_ONLY, parameters);
    }
    
    /**
     * Execute a safely-parameterized SQL query with the specified result set concurrency.
     * @param query the raw SQL to execute, with parameters indicated by ?
     * @param resultSetConcurrency a result set concurrency to apply to the internal ResultSet instance
     * @param parameters a list of parameters to insert at their respective placeholders
     * @return an ArrayList of ResultRows as returned by the database driver
     * @throws SQLException 
     */
    protected ArrayList<ResultRow> executeParameterized(String query, int resultSetConcurrency, List<String> parameters)
      throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
          resultSetConcurrency);
        for (int i = 0; i < parameters.size(); i++) {
            stmt.setString(i + 1, parameters.get(i));
        }

        ResultSet rs = stmt.executeQuery();
        ResultSetMetaData meta = rs.getMetaData();
        ArrayList<ResultRow> rows = new ArrayList<>();

        int colCount = meta.getColumnCount();
        while (rs.next()) {
            ResultRow row = new ResultRow(rs, meta);
            for (int i = 1; i <= colCount; i++) {
                row.addPair(meta.getColumnLabel(i), rs.getString(i));
            }
            rows.add(row);
        }

        return rows;
    }

    /**
     * Get the password and salt for a specified user ID.
     * @param userID a string containing the user ID whose details are being requested
     * @return If the user exists, a String[2] - password comes first, followed by salt. If the user does not exist,
     *         will return null.
     * @throws SQLException
     */
    public String[] getPasswordAndSalt(String userID) throws SQLException {
        String query = "SELECT usrID, password, salt FROM users WHERE usrID = ?;";
        List<String> params = Arrays.asList(userID);
        ArrayList<ResultRow> results = this.executeParameterized(query, params);
        
        if (results.size() >= 1) {
            ResultRow row = results.get(0);
            return new String[] { row.get("password"), row.get("salt") };
        }
        else {
            return null;
        }
    }

    /**
     * Check if the specified user ID is already registered.
     * @param userID the user ID to check for registration
     * @return a boolean
     * @throws SQLException
     */
    public boolean isUserRegistered(String userID) throws SQLException {
        String query = "SELECT usrID FROM users WHERE usrID = " + userID +  ";";
        List<String> params = Arrays.asList(userID);
        ArrayList<ResultRow> results = this.executeParameterized(query, params);
        
        return results.size() > 0;
    }
    
    /**
     * Get a list of quizzes, with reference to a particular student, that are in the specified state.
     * @param userID the student ID to get quizzes for
     * @param state the state returned quizzes must be in - completed or not
     * @return a list of result rows
     * @throws SQLException
     */
    // TODO: Make this return a Quiz object when we have enough database structure.
    public ArrayList<ResultRow> getQuizzesForStudent(String userID, QuizState state) throws SQLException {
        String query;
        if (state == QuizState.INCOMPLETE) {
            query = "SELECT * FROM quizzes AS q LEFT JOIN quiz_completions AS qc ON qc.quiz_id = q.id" +
                    "AND qc.user_id = ? WHERE qc.id IS NULL;";
        }
        else if (state == QuizState.COMPLETED) {
            query = "SELECT * FROM quizzes AS q INNER JOIN quiz_completions AS qc ON qc.quiz_id = q.id" +
                    "WHERE qc.user_id = ?";
        }
        else {
            throw new IllegalStateException("That's not even meant to exist.");
        }
        
        List<String> params = Arrays.asList(userID);
        return this.executeParameterized(query, params);
    }
    
    public void addUser(String userID, char[] password, byte[] salt, String course) {
        /*
        * Used to add new users to the database
        */
    }
}
