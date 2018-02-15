/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizsystem;

public class Lecturer extends User{
    private String name;

        
    public Lecturer(String inEmail,String inPassword, String inCourse, String inName){
        super(inEmail,inPassword,inCourse);
        name = inName;
    }
    
    public Lecturer(String inEmail, String inPassword, String inCourse){
        super(inEmail,inPassword,inCourse);
    }
    
    public void genName(){
        String email = getEmail();
        String[] names = email.split(".");
        String firstname = names[0];
        String[] names2 = names[1].split("@");
        String lastname = names2[1];
        name = firstname + " " + lastname;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
