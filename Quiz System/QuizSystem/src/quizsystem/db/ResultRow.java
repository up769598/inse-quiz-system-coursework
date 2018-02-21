package quizsystem.db;

import java.util.HashMap;

public class ResultRow {
    private HashMap<String, String> columns;
    
    public ResultRow() {}
    
    void addPair(String colName, String value) {
        columns.put(colName, value);
    }
    
    public String get(String colName) {
        return columns.get(colName);
    }
}
