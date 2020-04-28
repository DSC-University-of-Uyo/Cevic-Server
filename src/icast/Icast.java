/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icast;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author nyong
 */
public class Icast extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent parent = FXMLLoader.load(getClass().getResource("/design/landing_design.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Home Screen");
        primaryStage.setOnCloseRequest(e-> {
            e.consume();
            try {
                closeRequest();
            } catch (InterruptedException ex) {
                Logger.getLogger(Icast.class.getName()).log(Level.SEVERE, null, ex);
            }
});
        primaryStage.show();
    }
     void closeRequest() throws InterruptedException{
        System.exit(0);
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

 
    
}
