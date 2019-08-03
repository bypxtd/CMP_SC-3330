//
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
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 *
 * @author bruce
 */
public class BypxtdDigitalStopWatch {
    
    private double tickTimeInSeconds = 0.01;  // change this to change resolution
    private double angleDeltaPerSeconds = 6.0;
    
    private double secondsElapsed = 0;
    
    private Timeline timeline;
    private KeyFrame keyFrame;
    
    private StackPane rootContainer;
    private ImageView dialImageView;
    private Image dialImage;
    private String dialImageName = "clockface.jpg";
    
    //  Additional Variables
    private GridPane gridrootContainer;
    private Label timeLabel;
    private double secondTimeOnes = 0;
    private double secondTimeTens = 0;
    private double minuteTimeOnes = 0;
    private double minuteTimeTens = 0;
    private double hourTimeOnes = 0;
    private double hourTimeTens = 0;

    public BypxtdDigitalStopWatch() {
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
        gridrootContainer.setHgap(60);
        gridrootContainer.setVgap(201);
        gridrootContainer.setAlignment(Pos.TOP_CENTER);
        
        // Creat label
        timeLabel = new Label();
        // Call button UI method
        buttonUI();
        
        rootContainer = new StackPane();
        dialImageView = new ImageView();
        dialImage = new Image(getClass().getResourceAsStream(dialImageName));
        dialImageView.setImage(dialImage);
        // Add grid pane and label to stack pane
        rootContainer.getChildren().addAll(gridrootContainer, timeLabel, dialImageView);
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
            // Udates label text during KeyFrame Action
            timeLabel.setFont(Font.font("Arial", 100));
            //timeLabel.setFill(Color.GREEN);
            timeLabel.setText(String.format("%.0f%.0f:%.0f%.0f:%.0f%.0f", hourTimeTens, hourTimeOnes, minuteTimeTens, minuteTimeOnes, secondTimeTens, secondTimeOnes));
        });
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE); 
        
        if (running) {
            timeline.play();
        }
    }
    
    private void update() {
        // Increment of time in HH:MM:SS format
        secondTimeOnes++;
        if(secondTimeOnes == 10)
        {
            secondTimeOnes = 0;
            secondTimeTens++;
            if(secondTimeTens == 6)
            {
                secondTimeTens = 0;
                minuteTimeOnes++;
                if(minuteTimeOnes == 10)
                {
                    minuteTimeOnes = 0;
                    minuteTimeTens++;
                    if(minuteTimeTens == 6)
                    {
                        minuteTimeTens =0;
                        hourTimeOnes++;
                        if(hourTimeOnes == 10)
                        {
                            hourTimeOnes = 0;
                            hourTimeTens++;
                        }
                    }
                }
            }
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
        // Resets timeLabel text to 00:00:00
        timeLabel.setText(String.format("%.0f%.0f:%.0f%.0f:%.0f%.0f", hourTimeTens = 0, hourTimeOnes = 0, minuteTimeTens = 0, minuteTimeOnes = 0, secondTimeTens = 0, secondTimeOnes = 0));
    }
    
    public void setTickTimeInSeconds(Double tickTimeInSeconds) {
        this.tickTimeInSeconds = tickTimeInSeconds;
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