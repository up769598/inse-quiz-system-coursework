package quizsystem;

/**
 *
 * @author 769598
 */


public class Quiz {
   protected int quizID;
   protected String[] questions;
   /**
    * first is for the question number second is the answer number
    */
   protected String[][] answers;
   protected int[] correctAnswerIndex;
   protected int length;
   protected int score;
   protected int currentQuestion;
    
   public Quiz(){
    
}
 
   
   public Quiz(int db_QuizID, String[] db_Questions, String[][] db_Answers, int[] db_CorrectAnswer){
       this.length = db_Questions.length;
       this.questions = db_Questions;
       this.correctAnswerIndex = db_CorrectAnswer;
       this.quizID = db_QuizID;
       this.score = 0;
       this.currentQuestion = 0;
   }

public String getQuestion (int questionNo){
       return this.questions[questionNo];
}

public String[] getAnswers (int questionNo){
    return this.answers[questionNo];
}

public boolean checkAnswer (int questionNo,int chosenAnswer ){
    return correctAnswerIndex[questionNo] == chosenAnswer;
}

public void upScore(){
    this.score++;
}
public int getScore(){
    return this.score;
}

public int getCurrentQuestion(){
    return this.currentQuestion;
}

public void setCurrentQuestion(int questionIndex){
    this.currentQuestion = questionIndex;
}

}