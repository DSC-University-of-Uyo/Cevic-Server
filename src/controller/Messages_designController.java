/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultEditorKit;

/**
 * FXML Controller class
 *
 * @author nyong
 */
public class Messages_designController implements Initializable {
    @FXML
    private TextArea txtMessage;
   
    DefaultEditorKit.BeepAction beep =  new DefaultEditorKit.BeepAction();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
 
    
}

