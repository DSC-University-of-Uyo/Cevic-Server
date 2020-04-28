/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author nyong
 */
public class Home_designController implements Initializable {
      @FXML
    private JFXButton btnQuizAnalysis;

    @FXML
    private JFXButton btnSetQuiz;

    @FXML
    private JFXButton btnMessage;

    @FXML
    private TextArea incoming;
    
    
    @FXML
    private TextArea taIp;
    @FXML
    private Label quizLabel;

    @FXML
    private Label lblHead;
    
    @FXML
    private ImageView imgAttendance;

    @FXML
    private ImageView imgPause;
    
    @FXML
    private Label lblAttendance;

    @FXML
    private Label lblPause;


    @FXML
    private ImageView backButton;
      String quizTime = "0", status = "play";
      int optionA = 0, optionB = 0, optionC = 0,optionD =0, quizC = 0;
      List<Socket> allSockets = new ArrayList<>();

       FileWriter fw;
    BufferedWriter bw;
    File file;
      int answerCount = 0;
      List<String> clients = new ArrayList<>();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        startServer();
        GetMyIpAddress getMyIpAddress = new GetMyIpAddress();
        getMyIpAddress.ipAddress();
        String ipsp[] = getMyIpAddress.ipAddresses;
//        StringBuilder builder = new StringBuilder();
//          for (String ipsp1 : ipsp) {
//              builder.append(ipsp1);
//          }
        taIp.setText("Your IP address is: \n"+ipsp[0]);
        
        
    }    
    
    @FXML
    void analyseResult(ActionEvent event) {
           
          try {
              
              FXMLLoader loader = new FXMLLoader();
              loader.setLocation(getClass().getResource("/design/analysis_design.fxml"));
              Parent ap = loader.load();
              Analysis_designController control;
              control = loader.getController();
              control.setOptions(String.valueOf(optionA),String.valueOf(optionB),String.valueOf(optionC),String.valueOf(optionD),String.valueOf(answerCount));
              Scene scene = new Scene(ap);
              Stage stage = new Stage();
              stage.setScene(scene);
              stage.show();
          } catch (IOException ex) {
              Logger.getLogger(Home_designController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
      @FXML
    void generate(MouseEvent event) throws IOException {
try{
         JFileChooser chooser = new JFileChooser();
            int retrieval = chooser.showSaveDialog(null);
            if (retrieval == JFileChooser.APPROVE_OPTION){
                 file = new File(chooser.getSelectedFile() + ".txt");
           
                fw = new FileWriter(file);
                bw = new  BufferedWriter(fw);
                bw.write("\n");
                bw.write(String.format("%-80s%-60s", " ","ATTENDANCE" ));
                bw.newLine();
                bw.newLine();
                
                bw.write(String.format("%-30s%-60s%-60s"," ", "S/N ","NAME"));
                bw.newLine();
                bw.newLine();
                for (String cl: clients){
                    StringTokenizer st = new StringTokenizer(cl,"#");
                    bw.write(String.format("%-30s%-60s%-60s", " ",clients.indexOf(cl)+1,st.nextToken()));
                    bw.newLine();
                }
                
                }}
        finally{
            try {
                    if (bw != null)
			bw.close();

                    if (fw != null)
			fw.close();

            } catch (IOException ex) {
			}
                
            
            }
            JOptionPane.showMessageDialog(null, "Generated Successfully");
    }
     @FXML
    void pauseScreen(MouseEvent event) {
           if (status.equals("play")){
               status ="pause";
        Image image = new Image(getClass().getResourceAsStream("/img/Play_52px.png"));
        imgPause.setImage(image);
               //imgPause.setImage("/img/play_52px.png");
           }else{
               status = "play";
                Image image = new Image(getClass().getResourceAsStream("/img/Pause_50px_1.png"));
        imgPause.setImage(image);
           }
    }

    @FXML
    void messages(ActionEvent event) {
        incoming.setVisible(true);
        backButton.setVisible(true);
        lblHead.setText("Messages");
        btnMessage.setVisible(false);
        btnQuizAnalysis.setVisible(false);
        btnSetQuiz.setVisible(false);
        taIp.setVisible(false);
         imgAttendance.setVisible(false);
        imgPause.setVisible(false);
        lblPause.setVisible(false);
        lblAttendance.setVisible(false);
        quizLabel.setVisible(false);
        
        
    }

    @FXML
    void setQuiz(ActionEvent event) {

          try {
            // TODO
            
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/design/quiz_design.fxml"));
            AnchorPane ap = loader.load();
            Quiz_designController control;
            control = loader.getController();
            control.initIt(allSockets);
            Scene scene = new Scene(ap);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Landing_designController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      @FXML
    void goBack(MouseEvent event) {
        incoming.setVisible(false);
        backButton.setVisible(false);
        lblHead.setText("Home");
        btnMessage.setVisible(true);
        btnQuizAnalysis.setVisible(true);
        btnSetQuiz.setVisible(true);
        taIp.setVisible(true);
        imgAttendance.setVisible(true);
        imgPause.setVisible(true);
        lblPause.setVisible(true);
        lblAttendance.setVisible(true);
        quizLabel.setVisible(true);
        
    }
    
    
    void switchFxmlFile(String filePath){
         try {
            // TODO
            
            Parent parent = FXMLLoader.load(getClass().getResource(filePath));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Landing_designController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void startServer(){
        new Thread(() -> {
            try {
                
// Create a server socket
                ServerSocket serverSocket = new ServerSocket(8300);
                
//                ta.setText("MultiThreadServer started at "
//                        + new Date() + '\n');
                while (!serverSocket.isClosed()) {
// Listen for a new connection request
                    Socket socket = serverSocket.accept();
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    Platform.runLater(() -> {
                        
                    allSockets.add(socket);
                    });
                    
//
                    String user = inputStream.readUTF();
                    clients.add(user);
                    
//                    outputStream.writeUTF("1");
                    
                    // Create and start a new thread for the connection
                    new Thread(new HandleAClient(socket)).start();
                    new Thread(new ChatHandler(socket)).start();
                   
//                    }else outputStream.writeUTF("0");
//

                }
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }).start();

    }
    
    
    
    
    class HandleAClient implements Runnable {

    private final Socket socket; // A connected socket

    private Robot rob;
    private final Rectangle screenRectangle;

    BufferedImage imageInit;
    int imageInitLength;
    BufferedInputStream inputFromClient; // inputStream from client
    ObjectOutputStream outputToClient; // outputStream to client

    public HandleAClient(Socket socket) {
        
        System.out.println("I am here");

        this.socket = socket;
        screenRectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

//        this.imageInit = imageInit;
//        this.rob = robot;
//        this.screenRectangle = rec;
    }

    @Override
    public void run() {

        try {

            System.out.println("I am here 2");
            imageInit = captureScreen(screenRectangle);

            imageInitLength = getBufferLength(imageInit);

            try {
                sendImage(socket, imageInit);
            } catch (AWTException ex) {
                Logger.getLogger(HandleAClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("image Init sent and Legtn = " + imageInitLength);
            long beforeTime = System.currentTimeMillis();

            int count = 0;
            // Continuously serve the client
            while (socket.isConnected()) {

                count++;
// Receive radius from the client

//                System.out.println("preparing to send image...");
                BufferedImage capture = captureScreen(screenRectangle);

                int captureLength = getBufferLength(capture);

//                DataBuffer dbb = capture.getData().getDataBuffer();
                System.out.println("captured image legth :" + captureLength);

                if (imageInitLength == captureLength) {

                    System.out.println("image  same!");

//                   
                } else {
                    System.out.println("image not same!");
//                    imageInit = capture;
                    imageInitLength = captureLength;

                    try {
                        if (status.equals("play")){
                            sendImage(socket, capture);
                        }
                        else{
                            
                        }
                        
                    } catch (AWTException ex) {
                        Logger.getLogger(HandleAClient.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

//                  
            }
            double time = System.currentTimeMillis() - beforeTime;

            System.out.println("Seconds it took for " + count + " screen captures: " + time / 1000);

        } catch (IOException ex) {
//            Logger.getLogger(HandleAClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            //finally close int input stream
            try {

                if (inputFromClient != null) {
                    inputFromClient.close();
                    outputToClient.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(HandleAClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public BufferedImage captureScreen(Rectangle screenRectangle) {

        try {
            rob = new Robot();

            BufferedImage capture = rob.createScreenCapture(screenRectangle);
            return capture;

        } catch (AWTException ex) {
            Logger.getLogger(HandleAClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void sendImage(Socket socketForClient, BufferedImage capture) throws AWTException {

        try {

            //set up robot class for screen capture
//                
            outputToClient = new ObjectOutputStream(new BufferedOutputStream(socketForClient.getOutputStream()));

            byte[] buffer;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(capture, "jpg", baos);
                baos.flush();
//                System.out.println("Size of baos = " + baos.size() / 1024 + "kb");
                buffer = baos.toByteArray();
//                System.out.println("Size of buffer = " + buffer.length / 1024 + "kb");
            }
            outputToClient.writeObject(buffer);
            outputToClient.flush();  
        } catch (IOException ex) {
            System.out.println("an error : " + ex.getLocalizedMessage());
        } finally {

        }
//        System.out.println("Now false");

    }

    //compare screen capture
    boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public int getBufferLength(BufferedImage capture) throws IOException {

        byte[] buffer;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(capture, "jpg", baos);
            baos.flush();
//            System.out.println("Size of baos = " + baos.size() / 1024 + "kb");
            buffer = baos.toByteArray();
//            System.out.println("Size of buffer = " + buffer.length);
        }

        return buffer.length;

    }

}

    
    
    
public class ChatHandler implements Runnable{
      private Socket Socket;
      public String allMessages = "", userName = "";
      StringBuilder stringBuilder = new StringBuilder(allMessages);
       

      
    public ChatHandler(Socket socket){
        this.Socket = socket;
    }
    public ChatHandler(){
      }
        @Override
        public void run() {
       
            DataInputStream dInputStream;
            while(Socket.isConnected()){
            try {
                String quiztime = quizLabel.getText();
                dInputStream = new DataInputStream(Socket.getInputStream());
                while (true) {
                String message = dInputStream.readUTF();
                Toolkit.getDefaultToolkit().beep();
                
                
                
                Platform.runLater(()->{
                    switch (message.toUpperCase()) {
                        case "A":
                            optionA++;
                            answerCount++;
                            break;
                        case "B":
                            optionB++;
                            answerCount++;
                            break;
                        case "C":
                            optionC++;
                            answerCount++;
                            break;
                        case "D":
                            optionD++;
                            answerCount++;
                            break;
                        default:
                            StringTokenizer st = new StringTokenizer(message, "#");
                            String a[] = new String[2];
                            a[0] = st.nextToken();
                            if (st.hasMoreTokens()){
                                a[1] = st.nextToken();
                            }       stringBuilder.append(a[1]).append(": ").append(a[0]);
                            stringBuilder.append("\n======================================================================================================================================================\n");
                            //   messageCount++;
                            //    msgcount.setText(String.valueOf(messageCount));
                            incoming.setText(stringBuilder.toString());
                            quizC++;
                            quizLabel.setText(quizC + "");
                            break;
                    }
                });
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            }
            }
       
        }
    }

    
}


