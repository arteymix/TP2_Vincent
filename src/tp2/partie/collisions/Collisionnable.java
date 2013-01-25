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
    
    public boolean inCollision = false;

    /**
     * Entre en collision avec l'objet collisionnable en parametre
     */
    public void collision(Collisionnable C);

    public void preCollision(Collisionnable c2);

    public Rectangle getCollisionRectangle();
}
