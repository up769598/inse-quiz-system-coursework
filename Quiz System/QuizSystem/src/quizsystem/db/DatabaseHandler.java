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
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://52.91.67.192:3306/inse", "inse", "Wv7q7hG9");
            this.connection = con;
        } catch (SQLException ex) {
            System.out.println(ex);
            throw ex; // Re-throw so that calling code (ultimately the UI layer) can handle and display error messages
        }
    }
    
    /**
     * Execute an SQL query.
     * @param query the raw SQL to execute.
     * @return an ArrayList of ResultRows as returned by the database driver.
     * @throws SQLException 
     */
    private ArrayList<ResultRow> execute(String query) throws SQLException {
        return execute(query, ResultSet.CONCUR_READ_ONLY);
    }
    
    /**
     * Execute an SQL query with specified result set concurrency.
     * @param query the raw SQL to execute.
     * @param resultSetConcurrency a result set concurrency to apply to the internal ResultSet instance.
     * @return an ArrayList of ResultRows as returned by the database driver.
     * @throws SQLException 
     */
    private ArrayList<ResultRow> execute(String query, int resultSetConcurrency) throws SQLException {
        try {
            Statement stmt = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData meta = rs.getMetaData();
            ArrayList<ResultRow> rows = new ArrayList<>();
            
            int colCount = meta.getColumnCount();
            while (rs.next()) {
                ResultRow row = new ResultRow();
                for (int i = 1; i <= colCount; i++) {
                    row.addPair(meta.getColumnLabel(i), rs.getString(i));
                }
                rows.add(row);
            }
            
            return rows;
        }
        catch (SQLException ex) {
            System.out.println(ex);
            throw ex;
        }
    }
    
    /**
     * Execute a safely-parameterized SQL query.
     * @param query the raw SQL to execute, with parameters indicated by ?
     * @param parameters a list of parameters to insert at their respective placeholders
     * @return an ArrayList of ResultRows as returned by the database driver
     * @throws SQLException 
     */
    private ArrayList<ResultRow> executeParameterized(String query, List<String> parameters) throws SQLException {
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
    private ArrayList<ResultRow> executeParameterized(String query, int resultSetConcurrency, List<String> parameters)
      throws SQLException {
        try {
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
                ResultRow row = new ResultRow();
                for (int i = 1; i <= colCount; i++) {
                    row.addPair(meta.getColumnLabel(i), rs.getString(i));
                }
                rows.add(row);
            }
            
            return rows;
        }
        catch (SQLException ex) {
            System.out.println(ex);
            throw ex;
        }
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
     */
    public boolean isUserRegistered(String userID) {
        String query = "SELECT usrID FROM users WHERE usrID = ?;";
        List<String> params = Arrays.asList(userID);
        ArrayList<ResultRow> results = this.executeParameterized(query, params);
        
        return results.size() > 0;
    }
}
