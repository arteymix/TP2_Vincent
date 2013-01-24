/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.voiture;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author Vincent
 */
public class Joueur extends Voiture {

    private int nbVie = 3;
    private final Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("./Ressources/charJoueur.png"));
    private static int vitessejoueur = 5; // la vitesse en Y de l'auto du joueur (pas affichee) influence la vitesse affichee des autre objets. 
    private final static int vitessemin = 5;
    private final static int vitessemax = 50;
    private final static int PVinitial = 100;
    private final static int positionY = 500;
    private final static int accelerationmax = 1;
    private final static int deplacementlateral = 7;
    private final static int LARGEUR = 25;
    private final static int LONGUEUR = 50;
    private boolean tirgauche = true;

    public Joueur() {
        super(PVinitial, vitessemax, deplacementlateral, accelerationmax, LARGEUR, LONGUEUR, 500, positionY, 0, 0); 
    }

    @Override
    public void deplacement() {

        if (x <= Math.abs(vx) && vx < 0) {
        } else if (x + 25 > 1000 && vx > 0) {
        } //Largeur carte = 1000
        else {
            x = x + vx;
        }

        vitessejoueur = vitessejoueur + dvy;

        if (contactroute && vitessejoueur > vitessemax) {
            vitessejoueur = vitessemax;
        } else if (!contactroute && vitessejoueur > vitessemax / 2) {
            vitessejoueur = vitessemax / 2;
        } else if (contactroute && vitessejoueur < vitessemin) {
            vitessejoueur = vitessemin;
        } else if (!contactroute && vitessejoueur < vitessemin / 2) {
            vitessejoueur = vitessemin / 2;
        }


    }
// getter et setter

    @Override
    public void setContactroute(boolean contactroute) {
        this.contactroute = contactroute;
        if (contactroute) {
            vitessejoueur = vitessejoueur * 2;
        } else {
            vitessejoueur = vitessejoueur / 2;
        }
    }

    public boolean isTirgauche() {
        return tirgauche;
    }

    public void setTirgauche(boolean tirgauche) {
        this.tirgauche = tirgauche;
    }

    public static int getVitessejoueur() {
        return vitessejoueur;
    }

    public static void setVitessejoueur(int vitessejoueur) {
        Joueur.vitessejoueur = vitessejoueur;
    }

    public int getNbVie() {
        return nbVie;
    }

    public void setNbVie(int nbVie) {
        this.nbVie = nbVie;
    }

    public Image getImg() {
        return img;
    }

}
