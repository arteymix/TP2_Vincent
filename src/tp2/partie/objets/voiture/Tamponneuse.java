/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.voiture;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Usager
 */
public class Tamponneuse extends Voiture implements Mechant {

    private static int PVinitial = 50;
    private static int vmax = Joueur.getVitessejoueur() * 7 / 8;
    private final static int accelerationmax = 1;
    private final static int deplacementlateral = 6;
    private final static int LARGEUR = 25;
    private final static int LONGUEUR = 50;
    private static Image img;

    public Tamponneuse(int positionX, int positionY) {
        super(PVinitial, vmax, deplacementlateral, accelerationmax, LARGEUR, LONGUEUR, positionX, positionY, 0, 20);
        img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/tamponneuse.png"));
    }

    public static Image getImg() {
        return img;
    }
    
    // Essaye de faire des contacts latéraux pour faire du dégat (25 PV / seconde)
}
