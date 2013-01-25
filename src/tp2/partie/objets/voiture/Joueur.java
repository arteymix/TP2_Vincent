package tp2.partie.objets.voiture;

import java.awt.Image;
import java.awt.Toolkit;
import tp2.partie.Partie;
import tp2.partie.objets.armes.Huile;

/**
 * Classe représentant un humain.
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
    private boolean lacheHuile = false;
    private int nbHuileRestant;
    private int compteurHuile = 10;

    public Joueur(int positionX) {
        super(PVinitial, vitessemax, deplacementlateral, accelerationmax, LARGEUR, LONGUEUR, positionX, positionY, 0, 0);

    }

    @Override
    public void deplacement() {

        if (x <= Math.abs(vx) && vx < 0) {
        } else if (x + 25 > 1000 && vx > 0) {
        } else {
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

    public static int getVitesseMaxJoueur() {
        return vitessemax;
    }

    public static void setVitessejoueur(int vitessejoueur) {
        Joueur.vitessejoueur = vitessejoueur;
    }

    public int getNbVie() {
        return nbVie;
    }

    public void addHuile() {
        if (compteurHuile != 0) {
            Partie.getInstance().getmListeHuile().add(new Huile(this));
            compteurHuile--;
        } else {
            compteurHuile = 10;
            lacheHuile = false;
        }
    }

    public boolean isLacheHuile() {
        return lacheHuile;
    }

    public void setLacheHuile(boolean lacheHuile) {
        this.lacheHuile = lacheHuile;
    }

    public void retirerUneVie(int positionX) {
        nbVie--;


        if (nbVie <= 0) {
            // Partie.getInstance().setPause(tirgauche);
        }
    }

    @Override
    public Image getImg() {
        return img;
    }
}
