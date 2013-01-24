/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.collisions;

import java.awt.Rectangle;

/**
 *
 * @author Vincent
 */
public interface Collisionnable {
    
    /**
     * Entre en collision avec l'objet collisionnable en parametre
     */
    public void collision(Collisionnable C);
    
    public Rectangle getCollisionRectangle();
    
}
