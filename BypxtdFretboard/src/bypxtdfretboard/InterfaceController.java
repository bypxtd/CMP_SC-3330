/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bypxtdfretboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.text.TextBuilder;

/**
 * FXML Controller class
 */
public class InterfaceController implements Initializable {
    
    // Panes
    @FXML
    private VBox root;    
    @FXML
    private AnchorPane vizPane;
    
    // Menus
    @FXML
    private Menu instrumentsMenu;  
    @FXML
    private Menu settingsMenu;
    @FXML
    private Menu fretsMenu;
    @FXML
    private CheckMenuItem fretsCheckMenu;
    @FXML
    private Menu stringsMenu;
    @FXML
    private Menu stringsCheckMenu;
    
    // Text
    @FXML
    private Text instrumentText;
    @FXML
    private Text fretsText;
    @FXML
    private Text stringsText;
    @FXML
    private Text fret1Text;
    @FXML
    private Text fret2Text;
    @FXML
    private Text fret3Text; 
    @FXML
    private Text fret4Text;   
    @FXML
    private Text fret5Text;   
    @FXML
    private Text fret6Text;   
    @FXML
    private Text fret7Text;   
    @FXML
    private Text fret8Text;       
    @FXML
    private Text fret9Text;       
    @FXML
    private Text fret10Text;       
    @FXML
    private Text fret11Text;       
    @FXML
    private Text fret12Text;
    @FXML
    private Text userText;    
    @FXML
    private Text answerText;
    @FXML
    private Text aboutText;
   
    // Labels
    @FXML
    private Label timeLabel;
    @FXML
    private Label scoreLabel;
//    @FXML
//    private Label percentLabel;
    
    // Instrument variables
    private Integer numFrets = 12;
    private Integer numStrings = 6;
//    private ArrayList<Integer> circleZ;
    private ArrayList<Instrument> instruments;
    private Instrument currentInstrument;
    private final Integer[] fretsList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private final Integer[] stringsList = {1, 2, 3, 4, 5, 6};
    
    // Time variables and function
    private double tickTimeInSeconds = 1.0;
    private double minutesElapsed = 0;
    private double secondsElapsed = 0;
    private Timeline timeline;
    private KeyFrame keyFrame;
    // Keeps track of time elapsed 
    private void time() {
        secondsElapsed += tickTimeInSeconds;
        int hours = (int)secondsElapsed / 3600;
        int minutes = (int)secondsElapsed / 60;
        int seconds = (int)secondsElapsed % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timeLabel.setText(timeString);
    }
   
    // Score variables and function
    private int total = 0;
    private int correct = 0;
    // Keeps track of user score
    private void score() {
        String scoreString = String.format("%d/%d", correct, total);
        scoreLabel.setText(scoreString);
    }
    
    // Percentage variables and function
// <Label fx:id="percentLabel" layoutX="636.0" layoutY="42.0" text="0%" />
//    private double percent = ((double)correct/(double)total) * 100;
    // Calculates percentage of score
//    private void percent() {
//        String percentString = String.format("%f", percent);
//        percentLabel.setText(percentString);
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Time
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), 
        (ActionEvent event) -> {
            time();
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);    
        
        // Initialize array of instruments
        instruments = new ArrayList<>();
        instruments.add(new Guitar());
        instruments.add(new Ukulele());
                
        // Initialize instrument menu
        for (Instrument instrument : instruments) {
            MenuItem menuItem = new MenuItem(instrument.getName());
            menuItem.setUserData(instrument);
            menuItem.setOnAction((ActionEvent event) -> {
                selectInstrument(event);
            });
            instrumentsMenu.getItems().add(menuItem);
        }
        currentInstrument = instruments.get(0);
        
        // Initialize frets menu
        for (Integer frets : fretsList) {
            CheckMenuItem checkmenuItem = new CheckMenuItem(Integer.toString(frets));
            checkmenuItem.setSelected(true);
            checkmenuItem.setUserData(frets);
//            menuItem.setOnAction((ActionEvent event) -> {
//                selectFrets(event);
//            });
            fretsMenu.getItems().add(checkmenuItem);
        }
        
        // Initialize strings menu
        for (Integer strings : stringsList) {
        CheckMenuItem checkmenuItem = new CheckMenuItem(Integer.toString(strings));
        checkmenuItem.setSelected(true);
        checkmenuItem.setUserData(strings);
//            menuItem.setOnAction((ActionEvent event) -> {
//                selectStrings(event);
//            });
            stringsMenu.getItems().add(checkmenuItem);
        }
    }
    
    // Visual display and information of the selected information
    private void selectInstrument(ActionEvent event) {
        MenuItem menuItem = (MenuItem)event.getSource();
        Instrument instrument = (Instrument)menuItem.getUserData();
        changeInstrument(instrument);
        fretsText.setText(Integer.toString(numFrets));
        stringsText.setText(Integer.toString(numStrings));
        fret1Text.setText("1");
        fret2Text.setText("2");
        fret3Text.setText("3");
        fret4Text.setText("4");
        fret5Text.setText("5");
        fret6Text.setText("6");
        fret7Text.setText("7");
        fret8Text.setText("8");
        fret9Text.setText("9");
        fret10Text.setText("10");
        fret11Text.setText("11");
        fret12Text.setText("12");
        timeline.play();
    }
    
    // Create action for selecting frets
    private void selectFrets(ActionEvent event) {
        MenuItem menuItem = (MenuItem)event.getSource();
        numFrets = (Integer)menuItem.getUserData();
        if (currentInstrument != null) {
                currentInstrument.start(numFrets, numStrings, vizPane);
        }
        fretsText.setText(Integer.toString(numFrets));
    }
    
    // Create action for selecting strings
    private void selectStrings(ActionEvent event) {
        MenuItem menuItem = (MenuItem)event.getSource();
        numFrets = (Integer)menuItem.getUserData();
        if (currentInstrument != null) {
                currentInstrument.start(numFrets, numStrings, vizPane);
        }
        stringsText.setText(Integer.toString(numStrings));
    }
    
    // Change instruments
    private void changeInstrument(Instrument instrument) {
        // Call end() to clear pane of the current instrument
        if (currentInstrument != null) {
            currentInstrument.end();
        }
        // Make current instrument the seleted instrument
        currentInstrument = instrument;
        currentInstrument.start(numFrets, numStrings, vizPane);
        instrumentText.setText(currentInstrument.getName());
        // Call reset() to resest score
        reset();
//        if(currentInstrument == instruments.get(0))
//        {
//            
//        }
//        
//        if(currentInstrument == instruments.get(1))
//        {
//            
//        }
    }
    
    // Resets time, score, user choice and answer
    @FXML
    private void reset()
    {
        timeline.stop();
        secondsElapsed = 0;
        timeLabel.setText("00:00:00");

        correct = 0;
        total = 0;
        scoreLabel.setText("0/0");
        
//        percent = ((double)correct/(double)total) *100;
//        percentLabel.setText("0%");
        userText.setText("");
        answerText.setText("");
    }
    
    // Displays the answer of the note depeding on the value of getZ()
    @FXML
    private void answer()
    {
        // C
        if(currentInstrument.getZ() == 115 || currentInstrument.getZ() == 277 || currentInstrument.getZ() == 315 || currentInstrument.getZ() == 397 || currentInstrument.getZ() == 522 || currentInstrument.getZ() == 559)
        {
            answerText.setText("The answer was C");
        }

        // C#/Db
        if(currentInstrument.getZ() == 159 || currentInstrument.getZ() == 321 || currentInstrument.getZ() == 359 || currentInstrument.getZ() == 441 || currentInstrument.getZ() == 566 || currentInstrument.getZ() == 603)
        {
            answerText.setText("The answer was C#/Db");
        }        
        
        // D
        if(currentInstrument.getZ() == 132 || currentInstrument.getZ() == 202 || currentInstrument.getZ() == 365 || currentInstrument.getZ() == 403 || currentInstrument.getZ() == 484 || currentInstrument.getZ() == 609 || currentInstrument.getZ() == 637)
        {
            answerText.setText("The answer was D");
        }
        
        // D#/Eb
        if(currentInstrument.getZ() == 165 || currentInstrument.getZ() == 246 || currentInstrument.getZ() == 409 || currentInstrument.getZ() == 447 || currentInstrument.getZ() == 528 || currentInstrument.getZ() == 653)
        {
            answerText.setText("The answer was D#/Eb");
        }
        
        // E
        if(currentInstrument.getZ() == 57 || currentInstrument.getZ() == 182 || currentInstrument.getZ() == 209 || currentInstrument.getZ() == 290 || currentInstrument.getZ() == 453 || currentInstrument.getZ() == 491 || currentInstrument.getZ() == 562 || currentInstrument.getZ() == 687)
        {
            answerText.setText("The answer was E");
        }
        
        // F
        if(currentInstrument.getZ() == 90 || currentInstrument.getZ() == 215 || currentInstrument.getZ() == 252 || currentInstrument.getZ() == 334 || currentInstrument.getZ() == 497 || currentInstrument.getZ() == 534)
        {
            answerText.setText("The answer was F");
        }
        
        // F#/Gb
        if(currentInstrument.getZ() == 134 || currentInstrument.getZ() == 259 || currentInstrument.getZ() == 296 || currentInstrument.getZ() == 378 || currentInstrument.getZ() == 541 || currentInstrument.getZ() == 578)
        {
            answerText.setText("The answer was F#/Gb");
        }

        // G
        if(currentInstrument.getZ() ==  107 || currentInstrument.getZ() == 177 || currentInstrument.getZ() == 302 || currentInstrument.getZ() == 340 || currentInstrument.getZ() == 422 || currentInstrument.getZ() == 584 || currentInstrument.getZ() == 612)
        {
            answerText.setText("The answer was G");
        }
        
        // G#/Ab
        if(currentInstrument.getZ() ==  140 || currentInstrument.getZ() == 221 || currentInstrument.getZ() == 346 || currentInstrument.getZ() == 384 || currentInstrument.getZ() == 466 || currentInstrument.getZ() == 628)
        {
            answerText.setText("The answer was G#/Ab");
        }
       
        // A
        if(currentInstrument.getZ() == 157 || currentInstrument.getZ() == 184 || currentInstrument.getZ() == 265 || currentInstrument.getZ() == 390 || currentInstrument.getZ() == 428 || currentInstrument.getZ() == 509 || currentInstrument.getZ() == 662)
        {
            answerText.setText("The answer was A");
        }
        
        // A#/Bb
        if(currentInstrument.getZ() == 190 || currentInstrument.getZ() == 227 || currentInstrument.getZ() == 309 || currentInstrument.getZ() == 434 || currentInstrument.getZ() == 472 || currentInstrument.getZ() == 553)
        {
            answerText.setText("The answer was A#/Bb");
        }
        
        // B
        if(currentInstrument.getZ() == 82 || currentInstrument.getZ() == 234 || currentInstrument.getZ() == 271 || currentInstrument.getZ() == 353 || currentInstrument.getZ() == 478 || currentInstrument.getZ() == 516 || currentInstrument.getZ() == 587)
        {
            answerText.setText("The answer was B");
        }
    }
    
 // Previous button 
// Interface.fxml: <Button layoutX="10.0" layoutY="156.0" mnemonicParsing="false" onAction="#handlePrevious" prefHeight="40.0" prefWidth="40.0" text="&lt;" />
//    @FXML
//    private void handlePrevious(ActionEvent event) {
//        
//        circleZ.get(i);
//                    
//        System.out.printf("Previous = %d\n", currentInstrument.getZ());
//        currentInstrument.update();
//    }
            
    // Calls update() to 'skip' or go to the next question
    @FXML
    private void handleNext(ActionEvent event) {            
//        circleZ = new ArrayList<>();
//        circleZ.add(currentInstrument.getZ());
        
        currentInstrument.update();
    }
    


    // Create action for buttons
    // Each one varies slightly so only C will be commented
    @FXML
    private void handleC(ActionEvent event){
        // Values of C
        if(currentInstrument.getZ() == 115 || currentInstrument.getZ() == 277 || currentInstrument.getZ() == 315 || currentInstrument.getZ() == 397 || currentInstrument.getZ() == 522 || currentInstrument.getZ() == 559)
        {
            userText.setText("Correct!");
            // Calls answer() to display answer
            answer();
            // Calls update() to update pane and display the next question
            currentInstrument.update();
            // Increment variables to update score()
            correct += 1;
            total += 1;
            score();
            //percent();
        }
        else
        {
            userText.setText("Incorrect, your answer was C");
            answer();
            currentInstrument.update();
            total += 1;
            score();
            //percent();
        }
    }
        
    @FXML
    private void handleCD(ActionEvent event){
        if(currentInstrument.getZ() == 159 || currentInstrument.getZ() == 321 || currentInstrument.getZ() == 359 || currentInstrument.getZ() == 441 || currentInstrument.getZ() == 566 || currentInstrument.getZ() == 603)
        {
            userText.setText("Correct");
            answer();
            currentInstrument.update();
            correct += 1;
            total += 1;
            score();
            //percent();
        }
        else
        {
            userText.setText("Incorrect, your answer was C#/Db");
            answer();
            currentInstrument.update();
            total += 1;
            score();
            //percent();
        }
    }
        
    @FXML
    private void handleD(ActionEvent event){
        if(currentInstrument.getZ() == 132 || currentInstrument.getZ() == 202 || currentInstrument.getZ() == 365 || currentInstrument.getZ() == 403 || currentInstrument.getZ() == 484 || currentInstrument.getZ() == 609 || currentInstrument.getZ() == 637)
        {
            userText.setText("Correct!");
            answer();
            currentInstrument.update();
            correct += 1;
            total += 1;
            score();
            //percent();
        }
        else
        {
            userText.setText("Incorrect, your answer was D");
            answer();
            currentInstrument.update();
            total += 1;
            score();
            //percent();
        }
    }
        
    @FXML
    private void handleDE(ActionEvent event){
        if(currentInstrument.getZ() == 165 || currentInstrument.getZ() == 246 || currentInstrument.getZ() == 409 || currentInstrument.getZ() == 447 || currentInstrument.getZ() == 528 || currentInstrument.getZ() == 653)
        {
            userText.setText("Correct!");
            answer();
            currentInstrument.update();
            correct += 1;
            total += 1;
            score();
            //percent();
        }
        else
        {
            userText.setText("Incorrect, your answer was D#/Eb");
            answer();            
            currentInstrument.update();
            total += 1;
            score();
            //percent();
        }       
    }    
    
    @FXML
    private void handleE(ActionEvent event){
        if(currentInstrument.getZ() == 57 || currentInstrument.getZ() == 182 || currentInstrument.getZ() == 209 || currentInstrument.getZ() == 290 || currentInstrument.getZ() == 453 || currentInstrument.getZ() == 491 || currentInstrument.getZ() == 562 || currentInstrument.getZ() == 687)
        {
            userText.setText("Correct!");
            answer();
            currentInstrument.update();
            correct += 1;
            total += 1;
            score();
            //percent();
        }
        else
        {
            userText.setText("Incorrect, your answer was E");
            answer();
            currentInstrument.update();
            total += 1;
            score();
            //percent();
        }
    }
        
    @FXML
    private void handleF(ActionEvent event){
        if(currentInstrument.getZ() == 90 || currentInstrument.getZ() == 215 || currentInstrument.getZ() == 252 || currentInstrument.getZ() == 334 || currentInstrument.getZ() == 497 || currentInstrument.getZ() == 534)
        {
            userText.setText("Correct!");
            answer();
            currentInstrument.update();
            correct += 1;
            total += 1;
            score();
            //percent();
        }
        else
        {
            userText.setText("Incorrect, your answer was F");
            answer();
            currentInstrument.update();
            total += 1;
            score();
            //percent();
        }        
    }
        
    @FXML
    private void handleFG(ActionEvent event){
        if(currentInstrument.getZ() == 134 || currentInstrument.getZ() == 259 || currentInstrument.getZ() == 296 || currentInstrument.getZ() == 378 || currentInstrument.getZ() == 541 || currentInstrument.getZ() == 578)
        {
            userText.setText("Correct!");
            answer();
            currentInstrument.update();
            correct += 1;
            total += 1;
            score();
            //percent();
        }
        else
        {
            userText.setText("Incorrect, your answer was F#/Gb");
            answer();
            currentInstrument.update();
            total += 1;
            score();
            //percent();
        }       
    }
        
    @FXML
    private void handleG(ActionEvent event){
        if(currentInstrument.getZ() ==  107 || currentInstrument.getZ() == 177 || currentInstrument.getZ() == 302 || currentInstrument.getZ() == 340 || currentInstrument.getZ() == 422 || currentInstrument.getZ() == 584 || currentInstrument.getZ() == 612)
        {
            userText.setText("Correct!");
            answer();
            currentInstrument.update();
            correct += 1;
            total += 1;
            score();
            //percent();
        }
        else
        {
            userText.setText("Incorrect, your answer was G");
            answer();
            currentInstrument.update();
            total += 1;
            score();
            //percent();
        }        
    }
        
    @FXML
    private void handleGA(ActionEvent event){
        if(currentInstrument.getZ() ==  140 || currentInstrument.getZ() == 221 || currentInstrument.getZ() == 346 || currentInstrument.getZ() == 384 || currentInstrument.getZ() == 466 || currentInstrument.getZ() == 628)
        {
            userText.setText("Correct!");
            answer();
            currentInstrument.update();
            correct += 1;
            total += 1;
            score();
            //percent();
        }
        else
        {
            userText.setText("Incorrect, your answer was G#/Ab");
            answer();
            currentInstrument.update();
            total += 1;
            score();
            //percent();
        }                
    }
        
    @FXML
    private void handleA(ActionEvent event){
         if(currentInstrument.getZ() == 157 || currentInstrument.getZ() == 184 || currentInstrument.getZ() == 265 || currentInstrument.getZ() == 390 || currentInstrument.getZ() == 428 || currentInstrument.getZ() == 509 || currentInstrument.getZ() == 662)
        {
            userText.setText("Correct!");
            answer();
            currentInstrument.update();
            correct += 1;
            total += 1;
            score();
            //percent();
        }
        else
        {
            userText.setText("Incorrect, your answer was A");
            answer();
            currentInstrument.update();
            total += 1;
            score();
            //percent();
        }               
    }    
    
    @FXML
    private void handleAB(ActionEvent event){
        if(currentInstrument.getZ() == 190 || currentInstrument.getZ() == 227 || currentInstrument.getZ() == 309 || currentInstrument.getZ() == 434 || currentInstrument.getZ() == 472 || currentInstrument.getZ() == 553)
        {
            userText.setText("Correct!");
            answer();
            currentInstrument.update();
            correct += 1;
            total += 1;
            score();
            //percent();
        }
        else
        {
            userText.setText("Incorrect, your answer was A#/Bb");
            answer();
            currentInstrument.update();
            total += 1;
            score();
            //percent();
        }                
    }
    
    @FXML
    private void handleB(ActionEvent event){
         if(currentInstrument.getZ() == 82 || currentInstrument.getZ() == 234 || currentInstrument.getZ() == 271 || currentInstrument.getZ() == 353 || currentInstrument.getZ() == 478 || currentInstrument.getZ() == 516 || currentInstrument.getZ() == 587)
        {
            userText.setText("Correct!");
            answer();
            currentInstrument.update();
            correct += 1;
            total += 1;
            score();
            //percent();
        }
        else
        {
            userText.setText("Incorrect, your answer was B");
            answer();
            currentInstrument.update();
            total += 1;
            score();
            //percent();
        }               
    }
    
    // Calls reset()
    @FXML
    private void handleReset(ActionEvent event) {
        reset();
    }
    
    // Calls answer(), however also update the total and adds it onto the score
    @FXML
    private void handleAnswer(ActionEvent event) {
        // Sets userText to "" because no answer was chosen
        userText.setText("");
        total += 1;
        score();
        answer();
    }
    
    // Creat action for about
    @FXML
    private void handleAbout(ActionEvent event) {
        // Calls end() to end instrument
        currentInstrument.end();
        // Set text frets to ""
        fret1Text.setText("");
        fret2Text.setText("");
        fret3Text.setText("");
        fret4Text.setText("");
        fret5Text.setText("");
        fret6Text.setText("");
        fret7Text.setText("");
        fret8Text.setText("");
        fret9Text.setText("");
        fret10Text.setText("");
        fret11Text.setText("");
        fret12Text.setText("");
        
        // Set About text
        aboutText.setText("This app is a fretboard designed to help aspiring musicians, hobbyists, music theory nerds, \n"
                + "or anyone with a general interest in music improve their knowledge of instruments with a fretboard. \n\n"
                + "Unfortunately, I was unable to finish all the features I would have hoped to have implemented by this time.\n\n"
                + "However, I hope for whoever is reading this that music has been as much an important part of your life\n"
                + "as it has mine.\n\n"
                + "So, for now I'll leave you with this - Bruce Phommaly\n");
        
    }
    
    // File opening code is from TextEditor and is not mine
    // Opens Text file to load data
    @FXML
    public void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) root.getScene().getWindow();
        
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files","*.txt", "*.html", "*.c"));
        
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            
            BufferedReader bufferedReader = null;
                    
            try {
                bufferedReader = new BufferedReader(new FileReader(file));

                String document = "";
                String line = "";
                
                while ( (line = bufferedReader.readLine()) != null) {
                    document += line + "\n";
                }
                                
            } catch (FileNotFoundException fnfex) {
                displayExceptionAlert(fnfex);
            } catch (IOException ioex) {
                displayExceptionAlert(ioex);
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception ex) {
                        displayExceptionAlert(ex);
                    }
                }
            }
        }

    }

    private void displayExceptionAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Exception!");
        alert.setContentText(ex.getMessage());
        
        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
    
    // Append Results to textArea in order to save to textfile
//    @FXML
//    private TextArea textArea;
//    
//    private StringBuilder results = new StringBuilder(""); 
//    {
//        results.append(currentInstrument.getName());
//        results.append("\n");
//        results.append(correct+"/"+total);
//        results.append("\n");
//
//        textArea.setText(results.toString());
//    }
//    
//    @FXML
//    private void handleSave() {
//            FileChooser fileChooser = new FileChooser();
//                     Stage stage = (Stage) root.getScene().getWindow();
//
//            //Set extension filter
//            FileChooser.ExtensionFilter extFilter = 
//            new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
//            fileChooser.getExtensionFilters().add(extFilter);
//             
//            //Show save file dialog
//            File file = fileChooser.showSaveDialog(stage);
//             
//            if(file != null){
//                SaveFile(textArea.getText(), file);
//            }
//          }
//
//    private void SaveFile(String content, File file){
//        try {
//            FileWriter fileWriter;
//              
//            fileWriter = new FileWriter(file);
//            fileWriter.write(content);
//            fileWriter.close();
//        } catch (IOException ex) {
//            Logger.getLogger(BypxtdFretboard.class
//                .getName()).log(Level.SEVERE, null, ex);
//        }
//          
//    }
}
