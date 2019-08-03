/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bypxtdaudioviz;

import static java.lang.Integer.min;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 *
 * @author dale
 */
public class BypxtdSuperVisual implements Visualizer {
    
    
    private final String name = "Bypxtd Super Visual";
    
    private Integer numBands;
    private AnchorPane vizPane;
    
    private String vizPaneInitialStyle = "";
    
    private final Double bandHeightPercentage = 1.0;
    private final Double minRectangleRadius = 50.0;
    
    private Double width = 0.0;
    private Double height = 0.0;
    
    private Double bandWidth = 0.0;
    private Double bandHeight = 0.0;
    private Double halfBandWidth = 0.0;
    private Double halfBandHeight = 0.0;
    
    private final Double startHue = 50.0;
    
    private Rectangle[] rectangles;
    
    private Double Rotate = 0.0;
    
    public BypxtdSuperVisual() {
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void start(Integer numBands, AnchorPane vizPane) {
        end();
        
        vizPaneInitialStyle = vizPane.getStyle();
        
        this.numBands = numBands;
        this.vizPane = vizPane;
        
        height = vizPane.getHeight();
        width = vizPane.getWidth();
        
        Rectangle clip = new Rectangle(width, height);
        clip.setLayoutX(0);
        clip.setLayoutY(0);
        vizPane.setClip(clip);
        
        bandWidth = width / numBands;
        bandHeight = height * bandHeightPercentage;
        halfBandWidth = bandWidth / 2;
        halfBandHeight = bandHeight / 2;
        rectangles = new Rectangle[numBands];
                
        for (int i = 0; i < numBands; i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.setX(halfBandWidth + bandWidth * i);
            rectangle.setY(halfBandHeight);
            rectangle.setWidth(halfBandWidth);
            rectangle.setHeight(minRectangleRadius);
            rectangle.setFill(Color.GOLD);
            vizPane.getChildren().add(rectangle);
            rectangles[i] = rectangle;
        }

    }
    
    @Override
    public void end() {
         if (rectangles != null) {
             for (Rectangle rectangle : rectangles) {
                 vizPane.getChildren().remove(rectangle);
             }
            rectangles = null;
            vizPane.setClip(null);
            vizPane.setStyle(vizPaneInitialStyle);
        } 
    }
    
    @Override
    public void update(double timestamp, double duration, float[] magnitudes, float[] phases) {
        if (rectangles == null) {
            return;
        }
        
        Integer num = min(rectangles.length, magnitudes.length);
        
        for (int i = 0; i < num; i++) {
            rectangles[i].setHeight(((60.0 + magnitudes[i])/60.0) * halfBandHeight + minRectangleRadius);
            rectangles[i].setFill(Color.hsb(startHue + (magnitudes[i] * 6.0), 1.0, 1.0, 1.0));

        }
         
        vizPane.setStyle("-fx-background-color: #000000;" );

    }
}
