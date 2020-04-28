/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author nyong
 */
public class Analysis_designController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
  
    @FXML
    private Pane progressbarA;

    @FXML
    private Pane progressbarB;

    @FXML
    private Pane progressbarC;

    @FXML
    private Pane progressbarD;

    @FXML
    private Label lblPercentA;

    @FXML
    private Label lblPercentB;

    @FXML
    private Label lblPercentC;

    @FXML
    private Label lblPercentD;
    int optionA, optionB, optionC,optionD;
    double percent1, percent2,percent3,percent4;
    public double count;
    public double progress4;
    public double progress3;
    public double progress2;
    public double progress1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        Home_designController d = new Home_designController();
        setOptions(String.valueOf(d.optionA), String.valueOf(d.optionB), String.valueOf(d.optionC), String.valueOf(d.optionD), String.valueOf(d.answerCount));
       
       
        
        
        
        
    }    
    public void setOptions(String a,String b, String c,String d, String count){
        
        optionA = Integer.parseInt(a);
        optionB = Integer.parseInt(b);
        optionC = Integer.parseInt(c);
        optionD = Integer.parseInt(d);
        this.count = Integer.parseInt(count);
        
        
       calculatePercentage();
       setPercentage();
       calculateProgress();
       setProgress(progress1, progress2, progress3, progress4);
    }
    void calculatePercentage(){
        percent1 = optionA * 100 / count;
        percent2 = optionB * 100 / count;
        percent3 = optionC * 100 / count;
        percent4 = optionD * 100 / count;
        
        
    }
    void setPercentage(){
        lblPercentA.setText(String.format("%.1f", percent1)+"%");
        lblPercentB.setText(String.format("%.1f",percent2)+"%");
        lblPercentC.setText(String.format("%.1f",percent3)+"%");
        lblPercentD.setText(String.format("%.1f",percent4)+"%");
    }
    void calculateProgress(){
         progress1 = (percent1 / 100.0) * 300;
        progress2 = (percent2 / 100.0) * 300;
        progress3 = (percent3 / 100.0) * 300;
       progress4 = (percent4 / 100.0) *300;
    }
    void setProgress(double a,double b,double c,double  d){
        progressbarA.setPrefWidth(a);
        progressbarB.setPrefWidth(b);
        progressbarC.setPrefWidth(c);
        progressbarD.setPrefWidth(d);
    }
    
}
