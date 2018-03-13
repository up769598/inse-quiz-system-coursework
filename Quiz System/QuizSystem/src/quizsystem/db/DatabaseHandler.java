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
        Connection con = DriverManager.getConnection("jdbc:mysql://52.91.67.192:3306/QuizSystem", "inse", "Wv7q7hG9");
        this.connection = con;
    }
    
    /**
     * Execute an SQL query.
     * @param query the raw SQL to execute.
     * @return an ArrayList of ResultRows as returned by the database driver.
     * @throws SQLException 
     */
    protected ArrayList<ResultRow> execute(String query) throws SQLException {
        return execute(query, ResultSet.CONCUR_UPDATABLE);
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
        return this.executeParameterized(query, ResultSet.CONCUR_UPDATABLE, parameters);
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
     * Execute a safely-parameterized SQL manipulation statement.
     * @param query the raw SQL to execute, with parameters indicated by ?
     * @param parameters a list of parameters to insert at their respective placeholders
     * @return an ArrayList of ResultRows as returned by the database driver
     * @throws SQLException 
     */
    protected boolean executeManipulator(String query, List<String> parameters)
      throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement(query);
        for (int i = 0; i < parameters.size(); i++) {
            stmt.setString(i + 1, parameters.get(i));
        }

        return stmt.execute();
    }

    /**
     * Get the password and salt for a specified user ID.
     * @param email a string containing the email address for the user whose details are being requested
     * @return If the user exists, a String[2] - password comes first, followed by salt. If the user does not exist,
     *         will return null.
     * @throws SQLException
     */
    public String[] getPasswordAndSalt(String email) throws SQLException {
        String query = "SELECT usrID, password, salt FROM Users WHERE email = ?;";
        List<String> params = Arrays.asList(email);
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
     * @param email the email address to check for registration
     * @return a boolean
     * @throws SQLException
     */
    public boolean isUserRegistered(String email) throws SQLException {
        String query = "SELECT usrID FROM Users WHERE email = ?;";
        List<String> params = Arrays.asList(email);
        ArrayList<ResultRow> results = this.executeParameterized(query, params);
        
        return results.size() > 0;
    }
    
    /**
     * Get a list of quizzes, with reference to a particular student, that are in the specified state.
     * @param userID the student ID to get quizzes for
     * @param state the state returned quizzes must be in - completed or not
     * @return a list of quizzes
     * @throws SQLException
     */
    public ArrayList<Quiz> getQuizzesForStudent(String userID, QuizState state) throws SQLException {
        String query;
        if (state == null) {
            throw new IllegalStateException("That's not even meant to exist.");
        }
        else switch (state) {
            case INCOMPLETE:
                query = "SELECT q.* FROM Quizzes AS q LEFT JOIN QuizCompletions AS qc ON qc.quizID = q.quizID " +
                  "AND qc.usrID = ? WHERE qc.quizID IS NULL;";
                break;
            case COMPLETED:
                query = "SELECT * FROM Quizzes AS q INNER JOIN QuizCompletions AS qc ON qc.quizID = q.quizID " +
                  "WHERE qc.usrID = ?";
                break;
            case STARTED:
                query = "SELECT * FROM Quizzes WHERE quizID IN (SELECT DISTINCT quizID FROM AttemptAnswers " +
                  "WHERE usrID = ?);";
            default:
                throw new IllegalStateException("That's not even meant to exist.");
        }
        
        List<String> params = Arrays.asList(userID);
        ArrayList<ResultRow> rows = this.executeParameterized(query, params);
        ArrayList<Quiz> quizzes = new ArrayList<>();
        
        for (int i = 0; i < rows.size(); i++) {
            quizzes.add(new Quiz(rows.get(i)));
        }
        return quizzes;
    }
    
    /**
     * Creates a user row using the specified data and adds it to the database.
     * @param userType L or S, for lecturer or student
     * @param email    an email address for the user
     * @param password a pre-hashed password
     * @param salt     the salt used to hash the password
     * @param course   the name of the course the user is on
     * @return         a User record for the created user
     * @throws SQLException 
     */
    public User addUser(String userType, String email, String password, String salt, String course)
      throws SQLException {
        String query = "INSERT INTO Users (usrType, email, password, salt, course) VALUES (?, ?, ?, ?, ?);";
        List<String> params = Arrays.asList(userType, email, password, salt, course);
        this.executeManipulator(query, params);
        
        String reselectQuery = "SELECT * FROM Users WHERE email = ?;";
        List<String> reselectParams = Arrays.asList(email);
        ArrayList<ResultRow> rows = this.executeParameterized(reselectQuery, reselectParams);
        if (rows.size() > 0) {
            if (userType.equals("S")) {
                return new Student(rows.get(0));
            }
            else if (userType.equals("L")) {
                return new Lecturer(rows.get(0));
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }
    
    /**
     * Given a quiz and user ID, get the current state of the user's attempt at the specified quiz.
     * @param quizID the quiz ID to query for
     * @param usrID  the user whose attempt to find
     * @return       a List of AttemptAnswer objects, in questionID order
     * @throws SQLException 
     */
    public List<AttemptAnswer> getQuizAttempt(String quizID, String usrID) throws SQLException {
        String query = "SELECT * FROM AttemptAnswers WHERE quizID = ? AND usrID = ? ORDER BY questionID ASC;";
        List<String> params = Arrays.asList(quizID, usrID);
        ArrayList<ResultRow> rows = this.executeParameterized(query, params);
        
        List<AttemptAnswer> attempts = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            attempts.add(new AttemptAnswer(rows.get(i)));
        }
        return attempts;
    }
}
