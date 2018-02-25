package quizsystem.db;

import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultRow {
    private HashMap<String, String> columns;
    private ResultSet parentSet;
    private ResultSetMetaData meta;
    
    public ResultRow(ResultSet parent, ResultSetMetaData meta) {
        this.parentSet = parent;
        this.meta = meta;
    }
    
    public boolean supportsConcurrentUpdate() {
        try {
            return parentSet.getConcurrency() == ResultSet.CONCUR_UPDATABLE;
        }
        catch (SQLException ex) {
            System.out.println("[WARN] ResultRow.supportsConcurrentUpdate encountered SQLException:");
            System.out.println(ex);
            return false;
        }
    }
    
    void addPair(String colName, String value) {
        columns.put(colName, value);
    }
    
    void set(String attrName, String value) throws SQLException {
        this.parentSet.updateString(attrName, value);
        this.parentSet.updateRow();
        this.columns.put(attrName, value);
    }
    
    void set(HashMap<String, String> values) throws SQLException {
        List<String> keySet = new ArrayList<>(values.keySet());
        for (int i = 0; i < values.size(); i++) {
            String attrName = keySet.get(i);
            String value = values.get(attrName);
            this.parentSet.updateString(attrName, value);
        }
        this.parentSet.updateRow();
    }
    
    public int getColumnCount() throws SQLException {
        return meta.getColumnCount();
    }
    
    public List<String> getColumnNames() throws SQLException {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < meta.getColumnCount(); i++) {
            names.add(meta.getColumnName(i + 1));
        }
        return names;
    }
    
    public String get(String colName) {
        return columns.get(colName);
    }
}
