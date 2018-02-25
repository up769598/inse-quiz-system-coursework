package quizsystem.db;

import java.sql.SQLException;

public class Question extends Model {
    public Question(ResultRow row) throws SQLException {
        super(row);
    }
}
