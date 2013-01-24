/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.route;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Vincent
 */
public interface Route {
    
    public void deplacement();
    
    public int getLocationY();
    
    public int getMilieuX();
    
    public ArrayList<Rectangle> getBounds();

}
