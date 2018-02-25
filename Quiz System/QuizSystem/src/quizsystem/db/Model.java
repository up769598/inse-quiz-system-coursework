package quizsystem.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

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
}
