package quizsystem;

import java.io.UnsupportedEncodingException;
import quizsystem.db.DatabaseHandler;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * A class to handle logging into the system, including handling encryption
 */
public class Login {
    private static final Random random = new SecureRandom();
    private static final int iterations = 4096;
    private static final int keyLength = 256;

    /**
     * Create a new instance of Login.
     */
    public Login(){    
    }    

    private DatabaseHandler _handler;
    
    /**
     * Create a new instance of Login
     * @param handler An instance of database handler to avoid creating a new one
     */
    public Login(DatabaseHandler handler) {
        this._handler = handler;
    }

    /**
     * Attempt to login to the system with the users credentials
     * @param email A users email
     * @param password A users password
     * @return A description of the success/failure of the login. Gives a description on why the login failed if failed.
     * @throws SQLException
     */
    public static String login(String email, String password) throws SQLException {
        DatabaseHandler db = new DatabaseHandler();
        try {
            if (db.isUserRegistered(email)){
                String[] db_passwordAndSalt = db.getPasswordAndSalt(email);
                String salt = db_passwordAndSalt[1];
                String hashPassword = db_passwordAndSalt[0];
                System.out.println("Login salt; " + salt);

                if (isPasswordCorrect(password, salt, hashPassword)){
                    return "Successful login";
                }
                else {
                    return "Incorrect Password";
                }
            }
            else {
                return "User not registered";
            }
        }
        catch (SQLException ex) {
            return "Database connection failed";
        }
        catch (NullPointerException ex) {
            return "User not registered";
        }
    }
    
    /**
     * Returns a random salt to be used to hash a password.
     *
     * @return a 16 bytes random salt
     */
    public static String getNextSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        try {
            return new String(salt, "US-ASCII");
        }
        catch (UnsupportedEncodingException ex) {
            System.out.println("System doesn't support US-ASCII... what is this, 1960?");
            return null;
        }
    }

    /**
     * Returns a salted and hashed password using the provided hash.<br>
     * Note - side effect: the password is destroyed (the char[] is filled with zeros)
     *
     * @param password the password to be hashed
     * @param salt     a 16 bytes salt, ideally obtained with the getNextSalt method
     *
     * @return the hashed password with a pinch of salt
     */
    public static String hash(String password, String salt) {
        PBEKeySpec key = null;
        try {
            key = new PBEKeySpec(password.toCharArray(), salt.getBytes("US-ASCII"), iterations, keyLength);
            SecretKeyFactory sKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return new String(sKey.generateSecret(key).getEncoded(), "US-ASCII");
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException | UnsupportedEncodingException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        }
        finally {
            if (key != null) {
                key.clearPassword();
            }
        }
    }

    /**
     * Returns true if the given password and salt match the hashed value, false otherwise.<br>
     * Note - side effect: the password is cleared (the char[] is filled with zeros)
     *
     * @param password     the password to check
     * @param salt         the salt used to hash the password initially 
     * @param expectedHash the expected hashed value of the password
     *
     * @return true if the given password and salt match the hashed value, false otherwise
     */
    public static boolean isPasswordCorrect(String password, String salt, String expectedHash) {
        String pwdHash = hash(password, salt);
        return pwdHash.equals(expectedHash);
    }
}
