package quizsystem.db;

import java.sql.SQLException;

public class User extends Model {
    private String email;
    private String password;
    private String course;

    /**
     * Constructs a User instance based on a result obtained directly from the database. If the provided ResultRow
     * supports concurrent update, then this instance will also support it via its setters.
     * @param row a ResultRow instance obtained from the database handler
     * @throws java.sql.SQLException
     */
    public User(ResultRow row) throws SQLException {
        super(row);
        
        this.email = this.get("email");
        this.password = this.get("password");
        this.course = this.get("course");
    }

    /**
     * Get the user's email address.
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Change the user's email address.
     * @param email the new email address to set
     * @return a boolean indicating whether the new value was persisted to the database successfully
     */
    public boolean setEmail(String email) {
        this.email = email;
        return this.set("email", email);
    }

    /**
     * Get the user's password.
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Change the user's password.
     * @param password the new password to set - any hashing must be done by calling code
     * @return a boolean indicating whether the new value was persisted to the database successfully
     */
    public boolean setPassword(String password) {
        this.password = password;
        return this.set("password", password);
    }

    /**
     * Get the user's course.
     * @return String
     */
    public String getCourse() {
        return course;
    }

    /**
     * Change the user's course.
     * @param course the new course to set
     * @return a boolean indicating whether the new value was persisted to the database successfully
     */
    public boolean setCourse(String course) {
        this.course = course;
        return this.set("course", course);
    }
    
    /**
     * Find a user by their user ID.
     * @param id the user ID to search for
     * @return   a User record for the requested user, or null if none was found
     * @throws SQLException 
     */
    public static User getById(String id) throws SQLException {
        ResultRow row = Model.getById("Users", "usrID", id);
        return row == null ? null : new User(row);
    }
    
    /**
     * Find a user by their email address.
     * @param email the email address to search for
     * @return      a User record for the requested user, or null if none was found
     * @throws SQLException 
     */
    public static User getByEmail(String email) throws SQLException {
        ResultRow row = Model.getById("Users", "email", email);
        return row == null ? null : new User(row);
    }
}
