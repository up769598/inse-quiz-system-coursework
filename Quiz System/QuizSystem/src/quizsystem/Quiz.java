/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizsystem;

/**
 *
 * @author James
 */


public class Quiz {
   protected int quizID;
   protected String[] questions;
   /**
    * first is for the question number second is the answer number
    */
   protected String[][] answers;
   protected int correctAnswerIndex;
   protected int length;
    
   public Quiz(){
    
}
 
   
   public Quiz(int db_QuizID, String[] db_Questions, String[][] db_Answers, int db_CorrectAnswer){
       this.length = db_Questions.length;
       this.questions = db_Questions;
       this.correctAnswerIndex = db_CorrectAnswer;
       this.quizID = db_QuizID;    
   }



public String getQuestion (int questionNo){
       return this.questions[questionNo];
}

public String[] getAnswers (int questionNo){
    return this.answers[questionNo];
}


}