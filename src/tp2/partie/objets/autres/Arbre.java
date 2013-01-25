/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.autres;

import java.awt.Image;
import java.awt.Toolkit;
import tp2.partie.collisions.Collisionnable;
import tp2.partie.objets.autres.ObjGenerique;

/**
 *
 * @author user1
 */
public class Arbre extends ObjGenerique {

    private static Image img;
    private static final int LARGEUR = 50;
    private static final int LONGUEUR = 50;
    private static final int VX = 0;
    private static final int VY = 0;

    public Arbre(int positionX, int positionY) {
        super(LARGEUR, LONGUEUR, positionX, positionY, VX, VY);
        img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/arbre.png"));
    }

    public Image getImg() {
        return img;
    }

    public static int getArbreLARGEUR() {
        return LARGEUR;
    }


   
}
