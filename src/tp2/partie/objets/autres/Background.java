/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.autres;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Vincent
 */
public class Background extends ObjGenerique {

    private final static int LARGEUR = 1280;
    private final static int LONGUEUR = 1920;
    private Image img;

    public Background(int positionY) {
        super( LARGEUR,LONGUEUR,0, positionY, 0,0);
        img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/grass.png"));
    }

    public Image getImg() {
        return img;
    }

    public static int getLongueur(){
        return LONGUEUR;
    }

}
