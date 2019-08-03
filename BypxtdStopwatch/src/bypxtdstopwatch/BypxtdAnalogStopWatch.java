/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bypxtdstopwatch;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

// Additional imports
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
/**
 *
 * @author bruce
 */
public class BypxtdAnalogStopWatch {
    
    private double tickTimeInSeconds = 0.01;  // change this to change resolution
    private double tickTimeInMinutes = 0.01;
    private double tickTimeInHours = 0.01;
    
    private double angleDeltaPerSeconds = 6.0;
    private double angleDeltaPerMinutes = 6.0;
    private double angleDeltaPerHours = 6.0;
    
    private double secondsElapsed = 0;
    private double minutesElapsed = 0;
    private double hoursElapsed = 0;
    
    private Timeline timeline;
    private KeyFrame keyFrame;
    
    private StackPane rootContainer;
    private ImageView dialImageView;
    private ImageView secondshandImageView;
    private ImageView minuteshandImageView;
    private ImageView hourshandImageView;
    private Image dialImage;
    private Image secondshandImage;
    private Image minuteshandImage;
    private Image hourshandImage;
    private String dialImageName = "analogclockface.png";
    private String secondshandImageName = "analoghandseconds.png";
    private String minuteshandImageName = "analoghandminute.png";
    private String hourshandImageName = "analoghandhours.png";
        
    //  Additional Variables
    private GridPane gridrootContainer;

    public BypxtdAnalogStopWatch() {
        setupUI();
        setupTimer();
    }
    
    // Button UI method
    private void buttonUI()
    {
        // Create Start button, set action and parameters, and add to grid pane
        Button StartButton = new Button("Start");
        StartButton.setOnAction((ActionEvent event) -> 
        {
            // Call start method
            start();
        });
        GridPane.setColumnIndex(StartButton, 1);
        GridPane.setRowIndex(StartButton, 1);
        gridrootContainer.getChildren().add(StartButton);
       
        // Create Stop button, set action and parameters, and add to grid pane
        Button StopButton = new Button("Stop");
        StopButton.setOnAction((ActionEvent event) -> 
        {
            // Call stop method
            stop();
        });
        GridPane.setColumnIndex(StopButton, 2);
        GridPane.setRowIndex(StopButton, 1);
        gridrootContainer.getChildren().add(StopButton);
       
        // Create Reset button, set action and parameters, and add to grid pane
        Button ResetButton = new Button("Reset");
        ResetButton.setOnAction((ActionEvent event) -> 
        {
            // Call reset method
            reset();
        });
        GridPane.setColumnIndex(ResetButton, 3);
        GridPane.setRowIndex(ResetButton, 1);
        gridrootContainer.getChildren().add(ResetButton);
    }
    
    private void setupUI() {
        // Create grid pane, set parameters, and align
        gridrootContainer = new GridPane();
        gridrootContainer.setHgap(15);
        gridrootContainer.setAlignment(Pos.TOP_CENTER);

        // Call button UI method
        buttonUI();
        
        rootContainer = new StackPane();
        dialImageView = new ImageView();
        secondshandImageView = new ImageView(); 
        minuteshandImageView = new ImageView();
        hourshandImageView = new ImageView();
        dialImage = new Image(getClass().getResourceAsStream(dialImageName));
        secondshandImage = new Image(getClass().getResourceAsStream(secondshandImageName));
        minuteshandImage = new Image(getClass().getResourceAsStream(minuteshandImageName));
        hourshandImage = new Image(getClass().getResourceAsStream(hourshandImageName));
        dialImageView.setImage(dialImage);
        secondshandImageView.setImage(secondshandImage);
        minuteshandImageView.setImage(minuteshandImage);
        hourshandImageView.setImage(hourshandImage);
        // Add grid pane to stack pane
        rootContainer.getChildren().addAll(gridrootContainer, dialImageView, secondshandImageView, minuteshandImageView, hourshandImageView);
    }
    
    public void setupTimer() {
        boolean running = false;
        if (timeline != null) {
            if (timeline.getStatus() == Status.RUNNING) {
                running = true;
                timeline.stop();
            }
        }

        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            update();
        });
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE); 
        
        if (running) {
            timeline.play();
        }
    }
    
    // Incomplete
    private void update() {
        // Seconds update
        secondsElapsed += tickTimeInSeconds;
        double secondsrotation = secondsElapsed * angleDeltaPerSeconds;
        secondshandImageView.setRotate(secondsrotation);
        
        // Minutes update
        if(secondsElapsed == 60)
        {
            minutesElapsed += tickTimeInMinutes;        
            double minutesrotation = minutesElapsed * angleDeltaPerMinutes;
            minuteshandImageView.setRotate(minutesrotation);
        }
        
        // Hours update
        if(minutesElapsed == 60)
        {
            hoursElapsed += tickTimeInHours;
            double hoursrotation = hoursElapsed * angleDeltaPerHours;
            hourshandImageView.setRotate(hoursrotation);
        }
    }
    
    // return type is Parent so that any type of JavaFX container
    // an be returned.  StackPane (and GridPane and AnchorPane and ...)
    // all inherit from Parent
    public Parent getRootContainer() {
        return rootContainer;
    }
    
    public Double getWidth() {
        if (dialImage != null) return dialImage.getWidth();
        else return 0.0;
    }
    
    public Double getHeight() {
        if (dialImage != null) return dialImage.getHeight();
        else return 0.0;       
    }
    
    public void start() {
        timeline.play();
    }
    
    public void stop() {
        timeline.stop();
    }
    
    public void reset() {
        stop();
        secondsElapsed = 0;
        secondshandImageView.setRotate(0);
        minuteshandImageView.setRotate(0);
        hourshandImageView.setRotate(0);
    }
    
    public void setTickTimeInSeconds(Double tickTimeInSeconds) {
        this.tickTimeInSeconds = tickTimeInSeconds;
        setupTimer();
    }
    
    public void setTickTimeInMinutess(Double tickTimeInMinutess) {
        this.tickTimeInMinutes = tickTimeInMinutes;
        setupTimer();
    }
    
    public void setTickTimeInHours(Double tickTimeInHours) {
        this.tickTimeInHours = tickTimeInHours;
        setupTimer();
    }
    
    public boolean isRunning() {
        if (timeline != null) {
            if (timeline.getStatus() == Status.RUNNING) {
                return true;
            }
        } 
        return false;
    } 
}
