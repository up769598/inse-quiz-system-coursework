package quizsystem;

public class User {
    
    private String email;
    private String password;
    private String course;
    private boolean lecturer;
    
    public User(String inEmail, String inPassword, String inCourse, boolean inLecturer){
        email = inEmail;
        password = inPassword;
        course = inCourse;
        lecturer = inLecturer;
    }
    
}
