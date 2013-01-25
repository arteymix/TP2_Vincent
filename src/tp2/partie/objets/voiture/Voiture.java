/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie.objets.voiture;

import java.awt.Image;
import java.awt.Rectangle;
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
abstract public class Voiture extends ObjGenerique {

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

    private boolean testProblemeVoiture(Rectangle testRect) {
        if (Partie.getInstance().collisionVoiture(testRect, true) || !Partie.getInstance().collisionRoute(testRect) || Partie.getInstance().collisionJoueur(testRect)) {
            return true;
        }
        return false;
    }

    /**
     * Gestion des actions
     */
    @Override
    public void actions() {

        Joueur mJoueur = Partie.getInstance().getJoueur();

        Rectangle rect = Partie.getInstance().getRectRouteLocation(this.getY() + mJoueur.getVitessemax());

        if (rect.x + rect.width / 2 + this.getPositionideale(rect.width) - this.getX() < -1) {// Si la voiture est a droite de la position ideale sur la route
            this.setVx(-this.getDeplacementlateral());
        } else if (rect.x + rect.width / 2 + this.getPositionideale(rect.width) - this.getX() > 1) { // a gauche
            this.setVx(this.getDeplacementlateral());
        } else { // sur
            this.setVx(0);
        }


    }

    @Override
    public void preCollision(Collisionnable c) {

        System.out.println("precollision");
        
        Rectangle rToto = this.getCollisionRectangle();
        Joueur mJoueur = Partie.getInstance().getJoueur();


        Rectangle topRectangle = new Rectangle(rToto.x, rToto.y - mJoueur.getVitessemax() + 1, this.getLARGEUR(), this.getLONGUEUR());
        Rectangle bottomRectangle = new Rectangle(rToto.x - 24, rToto.y, this.getLARGEUR(), this.getLONGUEUR());
        Rectangle leftRectangle = new Rectangle(rToto.x - 24, rToto.y, this.getLARGEUR(), this.getLONGUEUR());
        Rectangle rightRectangle = new Rectangle(rToto.x + 24, rToto.y, this.getLARGEUR(), this.getLONGUEUR());

        if (this.getCollisionRectangle().intersects(topRectangle)) {
        }

        if (this.getCollisionRectangle().intersects(bottomRectangle)) {
        }

        if (this.getCollisionRectangle().intersects(leftRectangle)) {
            this.setVx(Math.abs(this.getDeplacementlateral()));
        }

        if (this.getCollisionRectangle().intersects(rightRectangle)) {
            this.setVx(-Math.abs(this.getDeplacementlateral()));
        }
        
        if(true) {
        return;
        }

        Rectangle rect = Partie.getInstance().getRectRouteLocation(this.getY() + mJoueur.getVitessemax());



        Rectangle testRect = new Rectangle(rToto.x, rToto.y - mJoueur.getVitessemax() + 1, this.getLARGEUR(), this.getLONGUEUR());



        boolean problemeHaut = testProblemeVoiture(testRect);
        testRect = new Rectangle(rToto.x, rToto.y + mJoueur.getVitessemax() - 1, this.getLARGEUR(), this.getLONGUEUR());
        boolean problemeBas = testProblemeVoiture(testRect);
        testRect = new Rectangle(rToto.x - 24, rToto.y, this.getLARGEUR(), this.getLONGUEUR());
        boolean problemeGauche = testProblemeVoiture(testRect);
        testRect = new Rectangle(rToto.x + 24, rToto.y, this.getLARGEUR(), this.getLONGUEUR());
        boolean problemeDroite = testProblemeVoiture(testRect);

        if (problemeDroite && problemeGauche) {
            if (Partie.getInstance().collisionVoiture(rect, problemeDroite)) {
            }
        } else if (problemeDroite && !problemeGauche) {
        } else if (!problemeDroite && problemeGauche) {
            this.setVx(this.getDeplacementlateral());
        }

    }
}
