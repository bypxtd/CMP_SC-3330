/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bypxtdfretboard;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.Random;

/**
 *
 * @author bruce
 */
public class Guitar implements Instrument{
        
    private final String name = "Guitar";
    
    private Integer numFrets;
    private Integer numStrings;
    private AnchorPane vizPane;
    
    private String vizPaneInitialStyle = "";
    
    private final Double bandHeightPercentage = 1.0;
    private Double width = 0.0;
    private Double height = 0.0;
    private Double bandWidth = 0.0;
    private Double bandHeight = 0.0;
    private Double halfBandWidth = 0.0;
    private Double halfBandHeight = 0.0;
    
    // Shape variables
    private Line[] verticalLines;
    private Line[] horizontalLines;
    private Circle circles;    
    //private Random rand;
    
    private final int[] circleX = {10, 43, 87, 130, 174, 218, 262, 306, 350, 394, 437, 481, 515};
    private final int[] circleY = {47, 72, 97, 122, 147, 172};
    private int z;
    
    public Guitar() {
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void start(Integer numFrets, Integer numStrings, AnchorPane vizPane) {
        end();
        
        vizPaneInitialStyle = vizPane.getStyle();
        
        this.numFrets = numFrets;
        this.numStrings = numStrings;
        this.vizPane = vizPane;
        
        height = vizPane.getHeight();
        width = vizPane.getWidth();
        
        bandWidth = width / numFrets;
        bandHeight = height * bandHeightPercentage;
        halfBandWidth = bandWidth / 2;
        halfBandHeight = bandHeight / 2;
        verticalLines = new Line[numFrets];
        horizontalLines = new Line[numStrings];
                
        for (int i = 0; i < numFrets; i++) {
            Line verticalLine = new Line();
            
            verticalLine.setTranslateX((halfBandWidth + bandWidth * i));
            verticalLine.setTranslateY(40);
            verticalLine.setStartY(0);
            verticalLine.setEndY(140);
            verticalLine.setStroke(Color.BLACK);    
            vizPane.getChildren().add(verticalLine);
            verticalLines[i] = verticalLine;
        }
        
        for(int i = 0; i < numStrings; i++)
        {
            Line horizontalLine = new Line();
            horizontalLine.setTranslateX(0);
            horizontalLine.setTranslateY(25 * i);
            horizontalLine.setStartX(0);
            horizontalLine.setEndX(525);
            horizontalLine.setStartY(47);
            horizontalLine.setEndY(47);
            horizontalLine.setStroke(Color.BLACK);
            vizPane.getChildren().add(horizontalLine);
            horizontalLines[i] = horizontalLine;
        }

        circle();
    }
    
    @Override
    public Integer circle() {
        Circle circle = new Circle();
        Random rand = new Random();

        int x = (rand.nextInt(13)+0);
        circle.setCenterX(circleX[x]);
        
        int y = (rand.nextInt(6)+0);
        circle.setCenterY(circleY[y]);
            
        z = circleX[x] + circleY[y];

        circle.setRadius(5);
        vizPane.getChildren().add(circle);
        circles = circle;   
        
        return z;
    }
    
    public Integer getZ()
    {
        return z;
    }

    @Override
    public void end() {
         if (verticalLines != null) {
             for (Line line : verticalLines) {
                 vizPane.getChildren().remove(line);
             }
            verticalLines = null;
        }
         
        if (horizontalLines != null) {
             for (Line line : horizontalLines) {
                 vizPane.getChildren().remove(line);
             }
            horizontalLines = null;
        }
        
        if (circles != null) {
                vizPane.getChildren().remove(circles);
        }            
            circles = null;
    }
    
    @Override
    public void update() {
        if (circles != null) {
            vizPane.getChildren().remove(circles);
        }            
            circles = null;

        circle();
    }
}
