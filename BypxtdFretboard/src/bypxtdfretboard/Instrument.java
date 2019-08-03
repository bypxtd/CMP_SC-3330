/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bypxtdfretboard;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author bruce
 */
public interface Instrument {
    public String getName();
    public void start(Integer numFrets, Integer numStrings, AnchorPane vizPane);
    public Integer circle();
    //public class Array<getZ>{};
    public Integer getZ();
    public void end();    
    public void update();
}
