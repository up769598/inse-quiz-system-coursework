/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizsystem.db;

import java.sql.SQLException;

public class Lecturer extends User {
    private String name;
        
    public Lecturer(ResultRow row) throws SQLException {
        super(row);
        this.name = this.getNameFromEmail();
    }
    
    private String getNameFromEmail() {
        String email = getEmail();
        String[] emailSplit = email.split("@");
        String[] namesSplit = emailSplit[0].split(".");
        String firstName = namesSplit[0];
        String lastName = namesSplit[1];
        return firstName + " " + lastName;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
