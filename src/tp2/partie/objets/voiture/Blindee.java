/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.voiture;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Objet pour le véhicule blindé.
 * 
 * @author Usager
 */
public class Blindee extends Voiture implements Mechant{

    private static int PVinitial = 50;
    private static int vmax = Joueur.getVitesseMaxJoueur()*3 / 4;
    private final static int accelerationmax = 1;
    private final static int deplacementlateral = 5;
    private final static int LARGEUR = 31;
    private final static int LONGUEUR = 50;
    private static Image img;

    public Blindee(int positionX, int positionY) {
        super(PVinitial, vmax, deplacementlateral, accelerationmax, LARGEUR, LONGUEUR, positionX, positionY, 0, 20);
        img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/blindee.png"));
    }

    public Image getImg() {
        return img;
    }
    
    //Tir latéralement (donc tente de se placer à côté du joueur)
}
