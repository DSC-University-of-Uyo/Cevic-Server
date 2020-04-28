/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;

/**
 *
 * @author nyong
 */
public class QuizObject implements Serializable{
    String question, optionA,optionB, optionC, optionD, answer, time;
    public QuizObject(String question, String optionA,String optionB, String optionC, String optionD, String answer, String time){
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
        this.time = time;
    }
//    
//    public void setQuestion(String question){
//        
//    }
//     public void setOptionA(String question){
//        
//    }
//      public void setOptionB(String question){
//        
//    }
//       public void setoptionC(String question){
//        
//    }
//        public void setOptionD(String question){
//        
//    }
//         public void setAnswer(String question){
//        
//    }
//          public void setTime(String question){
//        
////    }
//    public String getQuestion(){
//        return question;
//    }
//    public String getOptionA(){
//        return optionA;
//    }
//    public String getOptionB(){
//        return optionB;
//    }
//    public String getoptionC(){
//        return optionC;
//    }
//    public String getOptionD(){
//        return optionD;
//    }
//    public String getAnswer(){
//        return answer;
//    }
//    public String setTime(){
//        return time;
//    }
//    
}
