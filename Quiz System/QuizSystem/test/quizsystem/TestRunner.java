package quizsystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author James
 */
public class TestRunner {
    
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(LoginTest.class);
            System.out.println(failure.toString());
    }
    
}
