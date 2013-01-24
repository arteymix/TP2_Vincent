/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.armes;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import tp2.partie.collisions.Collisionnable;
import tp2.partie.objets.autres.ObjGenerique;
import tp2.partie.objets.autres.ObjGenerique;

/**
 *
 * @author Vincent
 */
public class Tir extends ObjGenerique {

    private final static int VITESSE_TIR = 25;
    private final static int portee = 30; // Les balles ont une portee limitee (de 30 m pour le joueur). 
    private final Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/balle.png"));

    ;
    
    public Tir(int x, int y) {
        super(5, 5, x, y, 0, VITESSE_TIR);
    }

    public Image getImage() {
        return img;
    }
    
    @Override
    public void deplacement(){
        y = y-VITESSE_TIR;
    }

    @Override
    public void collision(Collisionnable C) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Rectangle getCollisionRectangle() {        
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
