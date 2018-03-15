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
     * Update the current model instance with new attributes.
     * @param tableName  the table name to update
     * @param idColumn   the primary key or other unique column on the specified table
     * @param id         the value of idColumn to key the update to
     * @param attributes new attributes to set
     * @throws SQLException 
     */
    protected void update(String tableName, String idColumn, String id, HashMap<String, String> attributes)
     throws SQLException {
        List<String> params = new ArrayList<>();
        List<String> columns = new ArrayList<>(attributes.keySet());
        List<String> values = new ArrayList<>(attributes.values());
        for (int i = 0; i < columns.size(); i++) {
            columns.set(i, columns.get(i) + " = ?");
            params.add(values.get(i));
        }
        params.add(id);
        
        String setClause = DatabaseHandler.join(columns, ", ");
        String query = "UPDATE " + tableName + " SET " + setClause + " WHERE " + idColumn + " = ?;";
        DatabaseHandler handler = new DatabaseHandler();
        handler.executeManipulator(query, params);
        
        this._values.putAll(attributes);
    }
    
    /**
     * Retrieve a database row from the specified table with the given primary ID.
     * @param tableName the table name to select from
     * @param idColumn  the name of the primary key column or other single unique column to index by
     * @param id        the value of idColumn to search for
     * @return          a ResultRow for the requested record, or null if none could be found
     * @throws SQLException
     */
    protected static ResultRow getById(String tableName, String idColumn, String id) throws SQLException {
        String query = "SELECT * FROM " + tableName + " WHERE " + idColumn + " = ? LIMIT 1;";
        List<String> params = Arrays.asList(id);
        DatabaseHandler handler = new DatabaseHandler();
        ArrayList<ResultRow> rows = handler.executeParameterized(query, params);
        
        if (rows.size() > 0) {
            return rows.get(0);
        }
        else {
            return null;
        }
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
            rsParams.add(attributes.get(reselectors.get(i)));
            reselectors.set(i, reselectors.get(i) + " = ?");
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
    
    /**
     * Takes the intersection of two collections for use as reselectors to Model.create.
     * @param potential all possible selectors - i.e. all columns on the relevant table
     * @param keys      all selectors to be used in the Model.create call (usually attributes.keySet())
     * @return          a List containing the intersection that can be used for reselection
     */
    protected static List<String> calculateReselectors(List<String> potential, Set<String> keys) {
        Set<String> intersection = new HashSet<>(potential);
        intersection.retainAll(keys);
        return new ArrayList<>(intersection);
    }
}
