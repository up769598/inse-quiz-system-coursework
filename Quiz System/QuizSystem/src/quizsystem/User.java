package quizsystem;

public class User {
    private String email;
    private String password;
    private String course;

    public User(String inEmail, String inPassword, String inCourse){
        email = inEmail;
        password = inPassword;
        course = inCourse;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
