package quizsystem;

import quizsystem.db.DatabaseHandler;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Login {
    private static final Random random = new SecureRandom();
    private static final int iterations = 10000;
    private static final int keyLength = 256;
    
    public String login(String userID, char[] password){
        String[] db_passwordAndSalt;
        db_passwordAndSalt = DatabaseHandler.getPasswordAndSalt(userID);
        byte[] Salt = db_passwordAndSalt[1].getBytes();
        byte[] hashPassword = db_passwordAndSalt[0].getBytes();

        if (DatabaseHandler.isUserRegistered()){
            if (isPasswordCorrect(password, Salt, hashPassword)){
                return "Sucsesfull login";
            }
            else {
                return "Incorect Password";
            }
        }
        else {
            return "User not registered";
        }
    }
    
    /**
     * Returns a random salt to be used to hash a password.
     *
     * @return a 16 bytes random salt
     */
    public static byte[] getNextSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
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
    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec key = new PBEKeySpec(password, salt, iterations, keyLength);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory sKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return sKey.generateSecret(key).getEncoded();
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        }
        finally {
            key.clearPassword();
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
    public static boolean isPasswordCorrect(char[] password, byte[] salt, byte[] expectedHash) {
        byte[] pwdHash = hash(password, salt);
        Arrays.fill(password, Character.MIN_VALUE);
        if (pwdHash.length != expectedHash.length) return false;
        for (int i = 0; i < pwdHash.length; i++) {
            if (pwdHash[i] != expectedHash[i]) return false;
        }
        return true;
    }
}
