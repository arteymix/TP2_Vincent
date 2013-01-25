package tp2.partie;

import java.awt.Dimension;
import tp2.partie.son.Son;
import tp2.partie.objets.autres.Arbre;
import tp2.partie.objets.armes.Tir;
import tp2.partie.objets.autres.Explosion;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import tp2.graphique.Carte;
import tp2.graphique.FramePartie;
import tp2.graphique.Stats;
import tp2.partie.collisions.Collisionnable;
import tp2.partie.objets.armes.Huile;
import tp2.partie.objets.autres.Background;
import tp2.partie.objets.autres.ObjGenerique;
import tp2.partie.objets.voiture.Civil;
import tp2.partie.objets.voiture.Joueur;
import tp2.partie.objets.route.Route;
import tp2.partie.objets.route.RouteDroite;
import tp2.partie.objets.route.RouteTransition;
import tp2.partie.objets.voiture.Voiture;

/**
 *
 * @author Vincent
 */
public class Partie extends Thread {

    private final static Dimension mDimension = new Dimension(1000, 700);
    private static int RETRAITHAUT;
    private static int RETRAITBAS;
    private static Partie instance;
    private Son mSon;
    private FramePartie mFramePartie;
    private final CopyOnWriteArrayList<Arbre> arbres = new CopyOnWriteArrayList<Arbre>();
    private final CopyOnWriteArrayList<Tir> tirs = new CopyOnWriteArrayList<Tir>();
    private final CopyOnWriteArrayList<Voiture> mVoitures = new CopyOnWriteArrayList<Voiture>();//Auto civiles
    private final CopyOnWriteArrayList<Route> routes = new CopyOnWriteArrayList<Route>();
    private final CopyOnWriteArrayList<Explosion> explosions = new CopyOnWriteArrayList<Explosion>();
    private final HashSet<ObjGenerique> mObjGeneriques = new HashSet<ObjGenerique>();
    private CopyOnWriteArrayList<Huile> mListeHuile = new CopyOnWriteArrayList<Huile>();
    private Joueur mJoueur;
    private ArrayList<Background> pnlBackground = new ArrayList<Background>();
    private static boolean pause;
    private Collisions mCollisions = new Collisions();
    private Retirer mRetirer = new Retirer();
    /**
     * Détermine si le jeu est en cours
     */
    private boolean mRunning = true;

    public Joueur getJoueur() {
        return mJoueur;
    }

    private Partie() {

         
               RouteDroite rDini = new RouteDroite(mDimension);
        rDini.setPasladernierroute(true);

        RouteTransition.setLongueur(mDimension.height * 3);
        

        
        int decalageY = (RouteTransition.getLongueur() + RouteDroite.getLongueur() * 2 / 3);
        RouteDroite rDfinal = new RouteDroite(decalageY, rDini);


        RouteTransition pRouteTransition = new RouteTransition(rDini, rDfinal, RouteDroite.getLongueur() * 2 / 3);
        routes.add(rDini);
        routes.add(pRouteTransition);
        routes.add(rDfinal);

        RETRAITHAUT = -2 * RouteDroite.getLongueur() - RouteTransition.getLongueur();
        RETRAITBAS = RouteDroite.getLongueur() + RouteTransition.getLongueur();

        mJoueur = new Joueur(getRectRouteLocation(500).x + getRectRouteLocation(500).width / 2);
        Rectangle rectJoueur = getRectRouteLocation(mJoueur.getY());
        mJoueur.setX(rectJoueur.width / 2 + rectJoueur.x);
        
        mVoitures.add(mJoueur);

        genererArbre(rDini);
        genererArbre(pRouteTransition);
        genererArbre(rDfinal);
        genererVoiture();

        pnlBackground.add(new Background(0));
        pnlBackground.add(new Background(-Background.getLongueur()));

        mFramePartie = new FramePartie(mDimension);

        try {
            mSon = new Son();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        // to do : enlever le muteAll avant la remise
        //  s.muteAll();
        this.start();



    }

    @Override
    public void run() {

        mRetirer.start();
        mCollisions.start();

        while (mRunning) {

            try {

                for (Voiture toto : mVoitures) {
                    toto.actions();
                }

                deplacement();
                detecterCollision();
                generer();
                retirer();
                mFramePartie.invalidate();
                mFramePartie.repaint();

                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Carte.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }

    /**
     * Mets les déplacements a jour
     *
     */
    public void deplacement() {
        mJoueur.deplacement();

        for (Background background : pnlBackground) {
            background.deplacement();
        }

        for (Route route : routes) {
            route.deplacement();
        }

        for (Arbre branche : arbres) {
            branche.deplacement();
        }

        for (Tir pewpew : tirs) {
            pewpew.deplacement();
        }

        synchronized (mVoitures) {
            for (Voiture toto : mVoitures) {
                toto.deplacement();
            }
        }
        for (Explosion explo : explosions) {
            explo.deplacement();
        }

        for (Huile pHuile : mListeHuile) {
            pHuile.deplacement();

        }
    }

    public void detecterCollision() {
        // Joueur
        Rectangle rJoueur = mJoueur.getCONTOUR();
        if (collisionArbre(rJoueur)) {
            explosions.add(new Explosion(mJoueur.getX(), mJoueur.getY()));
        }
        if (collisionVoiture(rJoueur, false)) {
            System.out.println("Collision Joueur - Voiture");
        }
        if (mJoueur.isContactroute() && !collisionRoute(rJoueur)) {
            mJoueur.setContactroute(false);
        } else if (!mJoueur.isContactroute() && collisionRoute(rJoueur)) {
            mJoueur.setContactroute(true);
        }

        // Tir
        for (Tir tir : tirs) {
            Rectangle rTir = tir.getCONTOUR();
            if (collisionVoiture(rTir, false)) {
                System.out.println("Collision Tir - Voiture");
            }
            if (collisionArbre(rTir)) {
                System.out.println("Collision Tir - Arbre");
            }
            // Colision tir-route pas pertinente a faire.
        }
        // Voiture
        for (Voiture toto : Partie.getInstance().getVoitures()) {
            Rectangle rVoiture = toto.getCONTOUR();
            if (collisionVoiture(rVoiture, true)) {
                System.out.println("Collision Voiture - Voiture");
            }
            if (collisionArbre(rVoiture)) {
                mortVoiture(toto);
            }

            if (toto.isContactroute() && !collisionRoute(rVoiture)) {
                toto.setContactroute(false);
            } else if (!toto.isContactroute() && collisionRoute(rVoiture)) {
                toto.setContactroute(true);

            }
            if (rVoiture.intersects(rJoueur)) {
                System.out.println("Collision Voiture - Joueur");
            }

        }
    }

    public boolean collisionRoute(Rectangle pRectangle) {
        synchronized (routes) {

            for (Route route : routes) {
                ArrayList<Rectangle> rRoutelist = route.getBounds();
                for (Rectangle rRoute : rRoutelist) {
                    if (pRectangle.intersects(rRoute)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public boolean collisionArbre(Rectangle rObjgenerique) {
        synchronized (arbres) {
            for (Arbre branche : arbres) {
                Rectangle rArbre = branche.getCONTOUR();
                if (rObjgenerique.intersects(rArbre)) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean collisionVoiture(Rectangle rObjgenerique, boolean comparaisonVoitureVoiture) {
        int i = 0;
        for (Voiture toto : mVoitures) {
            Rectangle rVoiture = toto.getCONTOUR();
            if (rObjgenerique.intersects(rVoiture)) {
                i++;

            }
        }

        if (i >= 2 && comparaisonVoitureVoiture) {
            return true;
        } else {
            return false;
        }
    }

    public boolean collisionTir(Rectangle rObjgenerique) {
        for (Tir tir : tirs) {
            Rectangle rTir = tir.getCONTOUR();
            if (rObjgenerique.intersects(rTir)) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionJoueur(Rectangle rObjgenerique) {
        Rectangle rJoueur = mJoueur.getCONTOUR();
        if (rObjgenerique.intersects(rJoueur)) {
            return true;
        }
        return false;
    }

    private void generer() {
        RouteDroite rDfinal = null;
        Route rTr = null;
        for (Route route : routes) {
            if (route instanceof RouteDroite && route.getLocationY() >= -RouteDroite.getLongueur()) {
                if (!((RouteDroite) route).isPasladernierroute()) {
                    RouteDroite rDini = (RouteDroite) route;
                    rDfinal = new RouteDroite(RouteTransition.getLongueur() - rDini.getLocationY(), rDini);
                    rTr = new RouteTransition(rDini, rDfinal, -rDini.getLocationY());
                    rDini.setPasladernierroute(true);
                }
            }
        }
        if (rDfinal != null && rTr != null) {
            routes.add(rTr);
            routes.add((Route) rDfinal);
            genererArbre(rTr);
            genererArbre(rDfinal);
        }
        genererVoiture();
        genererHuile();
    }
    // generer un nombre aleatoire entre 0 et 29 de voiture a chaque creation de route pour un maximum de 30 auto dans la partie.

    private void genererHuile() {
        if (mJoueur.isLacheHuile()) {
            mJoueur.addHuile();
        }
    }

    private void genererVoiture() {
        Random r = new Random();
        if (r.nextInt(30) == 0) {

            int i = r.nextInt(30);
            if (i + mVoitures.size() > 30) {
                i = 30 - mVoitures.size();
            }
            for (; i > 0; i = i - 1) {
                Voiture toto;
                Rectangle rect;
                int locationYdepart;
                switch (r.nextInt(2)) {
                    case 0:
                        locationYdepart = -r.nextInt(-RETRAITHAUT);
                        rect = getRectRouteLocation(locationYdepart);
                        toto = new Civil((rect.x + rect.width / 2), locationYdepart);
                        addNewVoiture(toto);
                        break;
                    case 1:
                    default:
                        locationYdepart = r.nextInt(RETRAITBAS - mDimension.height) + mDimension.height;
                        rect = getRectRouteLocation(locationYdepart);
                        toto = new Civil((rect.x + rect.width / 2), locationYdepart);
                        addNewVoiture(toto);
                }

            }
        }
    }

    private void addNewVoiture(Voiture toto) {
        Rectangle rToto = toto.getCONTOUR();
        if (!collisionVoiture(rToto, false) && !collisionTir(rToto)) {
            synchronized (mVoitures) {
                mVoitures.add(toto);
            }
        }
    }

    /**
     * asjdfhiajsdhf
     *
     * @param route
     */
    private void genererArbre(Route route) {

        Random r = new Random();
        int xyz;
        if (route instanceof RouteDroite) {
            for (int y = route.getLocationY() + RouteDroite.getLongueur(); y >= route.getLocationY(); y = y - Arbre.getArbreLARGEUR()) {

                for (int positionX = -r.nextInt(51); positionX < mDimension.width; positionX = positionX + Arbre.getArbreLARGEUR() + r.nextInt(51)) {

                    xyz = r.nextInt(7);
                    if (xyz == 0) {
                        Arbre branche = new Arbre(positionX, y);
                        if (!collisionRoute(branche.getCONTOUR())) {
                            arbres.add(branche);
                        }
                    }
                }
            }
        } else {
            ArrayList<Rectangle> rRoute = route.getBounds();
            for (int i = 0; i < rRoute.size(); i = i + Arbre.getArbreLARGEUR() / RouteTransition.getHAUTEURRECTTRANSITION()) {
                Rectangle rect = rRoute.get(i);

                for (int positionX = -r.nextInt(51); positionX < mDimension.width; positionX = positionX + Arbre.getArbreLARGEUR() + r.nextInt(51)) {

                    xyz = r.nextInt(7);
                    if (xyz == 0) {
                        Arbre branche = new Arbre(positionX, rect.y);
                        if (!collisionRoute(branche.getCONTOUR())) {
                            arbres.add(branche);
                        }
                    }
                }
            }
        }
    }

    private void retirer() {

        if (routes.get(0).getLocationY() > RETRAITBAS) {
            routes.remove(0);
        }

        if (arbres.get(0).getY() > RETRAITBAS) {
            arbres.remove(0);
        }

        for (Voiture toto : mVoitures) {
            if (toto.getY() > RETRAITBAS || toto.getY() < RETRAITHAUT || !toto.isVivant()) {
                synchronized (mVoitures) {
                    mVoitures.remove(toto);
                }
            }
        }

        for (Tir pewpew : tirs) {
            if (pewpew.getY() < RETRAITHAUT) {
                tirs.remove(pewpew);
            }
        }

        if (pnlBackground.get(0).getY() > mFramePartie.getPnlBoard().getHeight()) {
            Background b = pnlBackground.get(0);
            pnlBackground.remove(0);
            pnlBackground.add(b);
            pnlBackground.get(1).setY(pnlBackground.get(0).getY() - Background.getLongueur());
        }
    }

    private void mortVoiture(Voiture toto) {
        explosions.add(new Explosion(toto.getX(), toto.getY()));
        toto.setVivant(false);
        synchronized (mVoitures) {
            mVoitures.remove(toto);
        }
    }

    public void tirJoueur() {

        int add = 0;
        if (mJoueur.isTirgauche()) {
            add = 5;
            mJoueur.setTirgauche(false);
        } else {
            add = -5;
            mJoueur.setTirgauche(true);
        }

        tirs.add(new Tir((mJoueur.getX() + add + mJoueur.getLARGEUR() / 2), mJoueur.getY()));
    }

    private boolean testProblemeVoiture(Rectangle testRect) {
        if (collisionVoiture(testRect, true) || !collisionRoute(testRect) || collisionJoueur(testRect)) {
            return true;
        }
        return false;
    }

    // Getter et Setter
    public Rectangle getRectRouteLocation(int locationYcherche) {
        Route r = null;
        for (Route route : routes) {
            if (locationYcherche > route.getLocationY()) {
                r = route;
                break;
            }
        }

        if (r instanceof RouteTransition) {
            for (Rectangle rect : r.getBounds()) {
                if (r.getLocationY() - rect.y < 2 && r.getLocationY() - rect.y >= 0) {
                    return rect;
                }
            }
        } else if (r == null) {
            return (new Rectangle(0, 0, mDimension.width, 2));
        }

        return r.getBounds().get(0);

    }

    public FramePartie getFp() {
        return mFramePartie;
    }

    public void setPause(boolean isPause) {
        pause = isPause;
        mFramePartie.setPause(isPause);
    }

    public static Partie getInstance() {

        if (instance == null) {
            instance = new Partie();
        }

        return instance;
    }

    public static Partie newInstance() {        
        instance = new Partie();
        return instance;
    }

    public Joueur getHeros() {
        return mJoueur;
    }

    public CopyOnWriteArrayList<Arbre> getArbres() {
        return arbres;
    }

    public CopyOnWriteArrayList<Tir> getTirs() {
        return tirs;
    }

    public CopyOnWriteArrayList<Voiture> getVoitures() {
        return mVoitures;
    }

    public CopyOnWriteArrayList<Route> getRoutes() {
        return routes;
    }

    public CopyOnWriteArrayList<Explosion> getExplosions() {
        return explosions;
    }

    public Son getS() {
        return mSon;
    }

    public ArrayList<Background> getPnlBackground() {
        return pnlBackground;
    }

    public CopyOnWriteArrayList<Huile> getmListeHuile() {
        return mListeHuile;
    }

    /**
     * Gestion des retraits asynchrome.
     */
    public class Retirer extends Thread {

        @Override
        public void run() {

            while (mRunning) {

                System.out.println("haha");

                if (routes.get(0).getLocationY() > RETRAITBAS) {
                    routes.remove(0);
                }


                if (arbres.get(0).getY() > RETRAITBAS) {
                    arbres.remove(0);
                }


                for (Voiture toto : mVoitures) {
                    if (toto.getY() > RETRAITBAS || toto.getY() < RETRAITHAUT || !toto.isVivant()) {
                        mVoitures.remove(toto);
                    }
                }



                for (Tir pewpew : tirs) {
                    if (pewpew.getY() < RETRAITHAUT) {
                        tirs.remove(pewpew);
                    }
                }


                if (pnlBackground.get(0).getY() > mFramePartie.getPnlBoard().getHeight()) {
                    Background b = pnlBackground.get(0);
                    pnlBackground.remove(0);
                    pnlBackground.add(b);
                    pnlBackground.get(1).setY(pnlBackground.get(0).getY() - Background.getLongueur());
                }
                try {
                    sleep(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     *
     * @author Vincent
     */
    public class Collisions extends Thread {

        @Override
        public void run() {

            while (mRunning) {

                for (Collisionnable c : mVoitures) {

                    for (Collisionnable c2 : mVoitures) {

                        final Rectangle target = c2.getCollisionRectangle();


                        if (c != c2 && c.getCollisionRectangle().intersects(target)) {
                            c.collision(c2);
                            System.out.println("collision");
                            return;
                        }

                        final Rectangle preRectangle = new Rectangle(c.getCollisionRectangle().x - 24, c.getCollisionRectangle().y - (mJoueur.getVitessemax() - 1), c.getCollisionRectangle().width + 48, c.getCollisionRectangle().height + (mJoueur.getVitessemax() - 1));


                        if (c != c2 && c.getCollisionRectangle().intersects(preRectangle)) {
                            c.preCollision(c2);
                        }



                    }
                }
                try {
                    sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(tp2.partie.collisions.Collisions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}
