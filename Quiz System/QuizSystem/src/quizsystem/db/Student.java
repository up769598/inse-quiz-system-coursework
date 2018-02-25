/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizsystem.db;

import java.sql.SQLException;

/**
 *
 * @author Jack
 */
public class Student extends User {
    public Student(ResultRow row) throws SQLException {
        super(row);
    }
}
