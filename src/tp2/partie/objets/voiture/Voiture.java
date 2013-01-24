/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.voiture;

import java.util.Random;
import tp2.partie.Partie;
import tp2.partie.collisions.Collisionnable;
import tp2.partie.objets.armes.Huile;
import tp2.partie.objets.autres.Explosion;
import tp2.partie.objets.autres.ObjGenerique;

/**
 *
 * @author Vincent
 */
public class Voiture extends ObjGenerique {

    private int pv;
    private int vitessemax;
    private int deplacementlateral;
    private int accelerationmax;
    protected int dvy = 0;
    protected boolean contactroute;
    private boolean vivant = true;
    private int vitessemin = 0;
    private int positioncentre = 0; // Position idéale par rapport au centre de la route.

    public Voiture(int PV, int vmax, int deplacementlateral, int acc, int largeur, int longueur, int x, int y, int vx, int vy) {
        super(largeur, longueur, x, y, vx, vy);
        this.pv = PV;
        this.vitessemax = vmax;
        this.deplacementlateral = deplacementlateral;
        this.accelerationmax = acc;

        Random r = new Random();
        positioncentre = r.nextInt(101);

        if (r.nextInt(2) == 0) {
            positioncentre = -positioncentre;
        }

    }

    @Override
    public void collision(Collisionnable c) {

        if (c instanceof Huile) {
            return;
        }

        // Autrement, mort
        this.setVivant(false);

        Partie.getInstance().getExplosions().add(new Explosion(this.getX(), this.getY()));
       
        
        Partie.getInstance().getVoitures().remove(this);

    }

    @Override
    public void deplacement() {

        if (contactroute && vx > deplacementlateral) {
            vx = deplacementlateral;
        } else if (!contactroute && vx > deplacementlateral / 2) {
            vx = deplacementlateral / 2;
        } else if (contactroute && vx < -deplacementlateral) {
            vx = -deplacementlateral;
        } else if (!contactroute && vx < -deplacementlateral / 2) {
            vx = -deplacementlateral / 2;
        }

        vy = vy + dvy;

        if (contactroute && vy > vitessemax) {
            vy = vitessemax;
        } else if (!contactroute && vy > vitessemax / 2) {
            vy = vitessemax / 2;
        } else if (contactroute && vy < vitessemin) {
            vy = vitessemin;
        } else if (!contactroute && vy < vitessemin / 2) {
            vy = vitessemin / 2;
        }

        x = x + vx;
        y = y + Joueur.getVitessejoueur() - vy;

    }

    public int getPositionideale(int largeurroute) {

        int positionideale = ((largeurroute - 150) / 2 * positioncentre) / 100;

        return positionideale;
    }

    public int getAccelerationMax() {
        return accelerationmax;
    }

    public int getDeplacementlateral() {
        return deplacementlateral;
    }

    public int getVitessemax() {
        return vitessemax;
    }

    public boolean isContactroute() {
        return contactroute;
    }

    public void setContactroute(boolean contactroute) {
        this.contactroute = contactroute;
        if (contactroute) {
            vy = vy * 2;
        } else {
            vy = vy / 2;
        }
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public boolean isVivant() {
        return vivant;
    }

    public void setVivant(boolean vivant) {
        this.vivant = vivant;
    }

    public void setAcceleration(int acceleration) {
        this.dvy = acceleration;
    }
}
