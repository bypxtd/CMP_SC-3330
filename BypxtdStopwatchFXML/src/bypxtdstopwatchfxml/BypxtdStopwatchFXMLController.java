/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bypxtdstopwatchfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

// Additional imports
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author bruce
 */
public class BypxtdStopwatchFXMLController implements Initializable {
    
    @FXML
    private VBox rootContainer;
    
    @FXML
    private StackPane analogstackpane;
    @FXML
    private ImageView analogclockhandhours;
    
    @FXML
    private ImageView analogclockhandminutes;
    
    @FXML
    private ImageView analogclockhandseconds;
    
    @FXML
    private StackPane digitalstackpane;
    
    @FXML
    private Label digitalclocklabel;
    
    @FXML 
    private HBox buttonContainer;
    
    // Create action for Start Button by calling start();
    @FXML
    private void handleStartButtonAction(ActionEvent event) {
        start();
    }
    
    // Create action for Stop Button by calling stop();
    @FXML
    private void handleStopButtonAction(ActionEvent event) {
        stop();
    }
    
    // Create action for Reset Button by calling reset();
    @FXML
    private void handleResetButtonAction(ActionEvent event) {
        reset();
    }
    
    private double tickTimeInSeconds = 1.0;  // change this to change resolution
    private double angleDeltaPerHours = 0.00166667;
    private double angleDeltaPerMinutes = 0.1;
    private double angleDeltaPerSeconds = 6.0;

    private double minutesElapsed = 0;
    private double secondsElapsed = 0;
    
    private Timeline timeline;
    private KeyFrame keyFrame;
        
    // Increment seconds, rotates hand, and updates and displays digitallabel
    private void update() {
        secondsElapsed += tickTimeInSeconds;
        
        double hoursangle = secondsElapsed * angleDeltaPerHours;
        double minutesangle = secondsElapsed * angleDeltaPerMinutes;
        double secondsangle = secondsElapsed * angleDeltaPerSeconds;
        analogclockhandhours.setRotate(hoursangle);
        analogclockhandminutes.setRotate(minutesangle);
        analogclockhandseconds.setRotate(secondsangle);
        
        int hours = (int)secondsElapsed / 3600;
        int minutes = (int)secondsElapsed / 60;
        int seconds = (int)secondsElapsed % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        digitalclocklabel.setText(timeString);
    }
    
    // Starts the timeline
    private void start() {
        timeline.play();
    }
    
    // Stops the timeline
    private void stop() {
        timeline.stop();
    }
    
    // Stops the timeline, sets hands back to 0, and sets label back to 00:00:00
    private void reset() {
        timeline.stop();
        secondsElapsed = 0;
        analogclockhandhours.setRotate(0);
        analogclockhandminutes.setRotate(0);
        analogclockhandseconds.setRotate(0);
        digitalclocklabel.setText("00:00:00");
    }
    
    /**
     * Initializes the controller class.
     */
    // Sets up timer
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), 
        (ActionEvent event) -> {
            update();
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }    
    
}
