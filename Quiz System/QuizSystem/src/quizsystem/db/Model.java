package quizsystem.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Model {
    protected HashMap<String, String> _values;
    private final ResultRow _row;
    private final boolean _isReadonlyInstance;
    
    /**
     * Construct a new model instance based on a ResultRow directly from the database.
     * @param row the ResultRow to extract data from
     * @throws SQLException 
     */
    public Model(ResultRow row) throws SQLException {
        this._values = new HashMap<>();
        List<String> names = row.getColumnNames();
        for (int i = 0; i < row.getColumnCount(); i++) {
            String name = names.get(i);
            this._values.put(name, row.get(name));
        }
        
        this._row = row;
        this._isReadonlyInstance = !row.supportsConcurrentUpdate();
    }
    
    /**
     * Pass a setter operation on to the underlying ResultRow if the current instance supports concurrent updates.
     * @param attrName the column name to update
     * @param value the new value to update to
     * @return a boolean indicating whether the new value was persisted to the database successfully
     */
    protected boolean set(String attrName, String value) {
        if (!this._isReadonlyInstance) {
            try {
                this._row.set(attrName, value);
                return true;
            }
            catch (SQLException ex) {
                System.out.println("[WARN] Model.set encountered SQLException:");
                System.out.println(ex);
                return false;
            }
        }
        return false;
    }
    
    /**
     * Get the value of a column specified by name.
     * @param name the column name to retrieve a value from
     * @return String
     */
    protected String get(String name) {
        return this._values.get(name);
    }
    
    /**
     * Using the provided attributes, create a database row in the specified table.
     * @param tableName   the table name to create a row in
     * @param attributes  a HashMap of database column names to new row values
     * @param reselectors a List of column names that can be used to reselect the record after insertion - if the table
     *                    has a unique index, use that, else specify all keys from attributes. Must be a subset of
     *                    attributes.keySet().
     * @param primaryKey  a column name to use for ordering the reselection query
     * @return            a ResultRow for the created record, or null if the operation failed
     * @throws SQLException 
     */
    protected static ResultRow create(String tableName, HashMap<String, String> attributes, List<String> reselectors,
      String primaryKey) throws SQLException {
        // SQL is annoyingly string-based, so turn all our data structures into strings in the right formats first
        String columns = "(" + DatabaseHandler.join(attributes.keySet(), ", ") + ")";
        String[] placeholders = new String[attributes.values().size()];
        Arrays.fill(placeholders, "?");
        List<String> placeholderList = Arrays.asList(placeholders);
        String values = "(" + DatabaseHandler.join(placeholderList, ", ") + ")";
        List<String> params = new ArrayList<>();
        params.addAll(attributes.values());
        
        // We have a runnable query now; run it.
        String query = "INSERT INTO " + tableName + " " + columns + " VALUES " + values + ";";
        DatabaseHandler handler = new DatabaseHandler();
        handler.executeManipulator(query, params);
        
        // The data is in the database now, but we don't yet have a row to return. Re-select it out of the DB.
        List<String> rsParams = new ArrayList<>();
        for (int i = 0; i < reselectors.size(); i++) {
            reselectors.set(i, reselectors.get(i) + " = ?");
            rsParams.add(attributes.get(reselectors.get(i)));
        }
        String reselectConditions = DatabaseHandler.join(reselectors, " AND ");
        String rsQuery = "SELECT * FROM " + tableName + " WHERE " + reselectConditions + " ORDER BY " +
          primaryKey + " DESC LIMIT 1";
        ArrayList<ResultRow> rows = handler.executeParameterized(rsQuery, rsParams);
        
        if (rows.size() > 0) {
            return rows.get(0);
        }
        else {
            return null;
        }
    }
    
    protected static List<String> calculateReselectors(List<String> potential, Set<String> keys) {
        Set<String> intersection = new HashSet<>(potential);
        intersection.retainAll(keys);
        return new ArrayList<>(intersection);
    }
}
