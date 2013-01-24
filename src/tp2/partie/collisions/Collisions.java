/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.collisions;

import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vincent
 */
public class Collisions extends Thread {

    private HashSet<Collisionnable> mCollisionnables = new HashSet<Collisionnable>();

    @Override
    public void run() {

        while (true) {
            for (Collisionnable c : mCollisionnables) {
                for (Collisionnable c2 : mCollisionnables) {
                    if (c != c2 && c.getCollisionRectangle().intersects(c2.getCollisionRectangle())) {
                        c.collision(c2);
                    }
                }
            }
            try {
                sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Collisions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public synchronized void registerCollisionnable(Collisionnable c) {
        this.mCollisionnables.add(c);

    }
}
