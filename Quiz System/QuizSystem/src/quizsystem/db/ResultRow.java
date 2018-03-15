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
        this.columns = new HashMap<>();
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
