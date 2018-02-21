package quizsystem.db;

import java.sql.*;
import java.util.ArrayList;

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

    public static String[] getPasswordAndSalt(String userID) {
        // Temporary shim until I can write a working implementation.
        return new String[0];
    }

    public static boolean isUserRegistered() {
        // Ditto. Temporary.
        return false;
    }
}
