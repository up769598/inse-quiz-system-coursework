package quizsystem.db;

import java.sql.SQLException;

public class Answer extends Model {
    public Answer(ResultRow row) throws SQLException {
        super(row);
    }
}
