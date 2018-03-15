package quizsystem.db;

import java.sql.SQLException;
import java.util.HashMap;

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
     * @throws java.sql.SQLException
     */
    public void setEmail(String email) throws SQLException {
        this.email = email;
        HashMap<String, String> updatedAttributes = new HashMap<>();
        updatedAttributes.put("email", email);
        this.update(updatedAttributes);
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
     * @throws java.sql.SQLException
     */
    public void setPassword(String password) throws SQLException {
        this.password = password;
        HashMap<String, String> updatedAttributes = new HashMap<>();
        updatedAttributes.put("password", password);
        this.update(updatedAttributes);
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
     * @throws java.sql.SQLException
     */
    public void setCourse(String course) throws SQLException {
        this.course = course;
        HashMap<String, String> updatedAttributes = new HashMap<>();
        updatedAttributes.put("course", course);
        this.update(updatedAttributes);
    }
    
    /**
     * Update the current User with new attributes.
     * @param newAttributes new attributes to set
     * @throws SQLException 
     */
    public void update(HashMap<String, String> newAttributes) throws SQLException {
        super.update("Users", "usrID", this.get("usrID"), newAttributes);
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
