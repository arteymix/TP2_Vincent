package tp2.partie.objets.autres;

import java.awt.Rectangle;
import tp2.partie.Partie;
import tp2.partie.collisions.Collisionnable;
import tp2.partie.objets.voiture.Joueur;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vincent
 */
public abstract class ObjGenerique implements Collisionnable {

    protected int x;
    protected int y;
    protected int vx; // vx et vy correspondent à la vitesse relative à l'affichage sur l'écran.
    protected int vy;
    private Rectangle CONTOUR = null;
    private final int LARGEUR;
    private final int LONGUEUR;

    public ObjGenerique(int largeur, int longueur, int x, int y, int vx, int vy) {

        this.LARGEUR = largeur;
        this.LONGUEUR = longueur;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public void deplacement() {
        x = x + vx;
        y = y + Joueur.getVitessejoueur() - vy;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.getCONTOUR();
    }

    @Override
    public void collision(Collisionnable C) {
        return;
    }

    public Rectangle getCONTOUR() {
        this.CONTOUR = new Rectangle(x, y, LARGEUR, LONGUEUR);
        return CONTOUR;
    }

    public int getLARGEUR() {
        return LARGEUR;
    }

    public int getLONGUEUR() {
        return LONGUEUR;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public int getVy() {
        return vy;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void actions() {
        
    }

    public void deplacements() {
    }
}
