/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bypxtdnotifier;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
// TextField
import javafx.scene.control.TextField;
// GridPane
import javafx.scene.layout.GridPane;
// Pos
import javafx.geometry.Pos;


/**
 *
 * @author bruce
 */
public class BypxtdNotifier extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Create TextField
        TextField TextField = new TextField();
        
        // GridPane Parameters
        GridPane GridRoot = new GridPane();        
        GridRoot.setVgap(10);
        GridRoot.setHgap(10);
        GridRoot.setAlignment(Pos.CENTER);
     
        // Create Notify Button
        Button NotifyButton = new Button("Notify");
        // Create Clear Button
        Button ClearButton = new Button(" Clear ");
        
        // Create Action for Notify Button
        NotifyButton.setOnAction((ActionEvent event) -> 
        {
            TextField.setText("You have been notified!");
        });
        
        // Create Action for Clear Button
        ClearButton.setOnAction((ActionEvent event) -> 
        {
            TextField.setText("");
        });
          
        // TextField Parameters
        GridPane.setColumnIndex(TextField, 0);
        GridPane.setRowIndex(TextField, 0);
        GridRoot.getChildren().add(TextField);

        // Notify Button Parameters
        GridPane.setColumnIndex(NotifyButton, 1);
        GridPane.setRowIndex(NotifyButton, 0);
        GridRoot.getChildren().add(NotifyButton);
          
        // Clear Button Parameters
        GridPane.setColumnIndex(ClearButton, 1);
        GridPane.setRowIndex(ClearButton, 1);
        GridRoot.getChildren().add(ClearButton);

        // Create Scene and set Scene Parameters
        Scene scene = new Scene(GridRoot, 400, 250);
        
        primaryStage.setTitle("Notifier");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
