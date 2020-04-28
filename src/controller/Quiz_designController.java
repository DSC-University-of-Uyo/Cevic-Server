/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author nyong
 */
public class Quiz_designController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private JFXTextArea txtQuestion;

    @FXML
    private JFXTextField txtOptionA;

    @FXML
    private JFXTextField txtOptionB;

    @FXML
    private JFXTextField txtOptionC;

    @FXML
    private JFXTextField txtOptionD;

    @FXML
    private ComboBox<String> cmbTime;

    @FXML
    private ComboBox<String> cmbAnswer;

    Socket socket;
    List<Socket> allTheSockets;
   
    String question, optionA, optionB, optionC,optionD,answer,time, title;
    ObservableList<String> options = FXCollections.observableArrayList();
    ObservableList<String> optionTime = FXCollections.observableArrayList();
    HashMap<String, String> questionObject = new HashMap<>();
     AnchorPane ap;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // try {
//             TODO
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/design/home_design.fxml"));
//            ap = loader.load();
//            control = loader.getController();
//            allTheSockets = control.allSockets;
            setData();
       // } catch (IOException ex) {
        //    Logger.getLogger(Quiz_designController.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }   
    
    @FXML
    void sendQuiz(ActionEvent event) throws IOException {
       title = JOptionPane.showInputDialog(null, "Enter course Title: ");
       question = txtQuestion.getText();
       optionA  =txtOptionA.getText();
       optionB =txtOptionB.getText();
       optionC = txtOptionC.getText();
       optionD = txtOptionD.getText();
       answer = cmbAnswer.getValue();
       time = cmbTime.getValue();
       String t = String.valueOf(Integer.parseInt(time) * 60);
       questionObject.put("title", title);
       questionObject.put("question",question);
       questionObject.put("optionA",optionA);
       questionObject.put("optionB",optionB);
       questionObject.put("optionC",optionC);
       questionObject.put("optionD",optionD);
       questionObject.put("answer",answer);
       questionObject.put("time",t);
        
            allTheSockets.stream().forEach((Socket x) -> {
                ObjectOutputStream oos = null;
                try {
                    oos = new ObjectOutputStream(x.getOutputStream());
                    oos.writeObject(questionObject);
                    oos.flush();
                    JOptionPane.showMessageDialog(null, "Quiz sent...");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                }
           });
          
        
}
    void initIt(List<Socket> socks){
        allTheSockets = socks;
        
    }
    void setData(){
        options.addAll("A","B","C","D");
        cmbAnswer.setItems(options);
        optionTime.addAll("1","2","3","4","5","6","7","8","9","10");
        cmbTime.setItems(optionTime);
    }

    }
    

