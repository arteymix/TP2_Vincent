/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.graphique;

import tp2.graphique.panels.PanelPause;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tp2.partie.Partie;

/**
 * Vue pour le menu.
 * 
 * @author Vincent
 */
public class FrameMenu extends JFrame implements KeyListener {

    /**
     * Singleton.
     */
    private static FrameMenu instance = new FrameMenu();

    public static FrameMenu getInstance() {
        return instance;
    }
    /**
     * Dimensions
     */
    private Dimension mDimension = new Dimension(1000, 700);
    private Image mBackground, mFleche;
    
    /**
     * Panneau du jeu en pause
     */
    private PanelPause mPanelPause = new PanelPause();
    
    /**
     * Position de la fèche
     */
    private int mFlecheX = 20, mFlecheY = 480;
    
    /**
     * Menu sélectionné
     */
    private ChoixMenu mChoixMenu = ChoixMenu.COMMENCER;

    // private Image background;
    // private Image fleche;
    private FrameMenu() {

        mBackground = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Ressources/menu.png"));
        mFleche = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Ressources/flèche.png"));
        // Gestion des entrées du clavier
        addKeyListener(this);

        mPanelPause.setVisible(false);

        add(mPanelPause);

        setTitle("SpyHunter");
        setSize(mDimension);
        setPreferredSize(mDimension);
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(mBackground, 0, 0, this);
        g.drawImage(mFleche, mFlecheX, mFlecheY, this);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Dans tous les cas, on désaffiche le paneau de highscores

        mPanelPause.setVisible(false);

        switch (e.getKeyCode()) {

            case KeyEvent.VK_ENTER:


                switch (getChoixMenu()) {

                    case COMMENCER:
                        this.setVisible(false);
                        Partie.newInstance();
                        break;
                    case HIGHSCORE:
                        JOptionPane.showMessageDialog(this, "Ceci est un message");
                        break;
                    case OPTIONS:
                        mPanelPause.setVisible(true);
                        break;

                }
                break;
            case KeyEvent.VK_UP:
                switch (this.getChoixMenu()) {
                    case OPTIONS:
                        this.setChoixMenu(ChoixMenu.COMMENCER);
                        break;
                    case HIGHSCORE:
                        this.setChoixMenu(ChoixMenu.OPTIONS);
                        break;
                }
                break;
            case KeyEvent.VK_DOWN:

                switch (this.getChoixMenu()) {
                    case COMMENCER:
                        this.setChoixMenu(ChoixMenu.OPTIONS);
                        break;
                    case OPTIONS:
                        this.setChoixMenu(ChoixMenu.HIGHSCORE);
                        break;
                }

                break;

        }
        
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void setChoixMenu(ChoixMenu pChoixMenu) {

        mChoixMenu = pChoixMenu;

        switch (pChoixMenu) {

            case COMMENCER:
                // On met la flèche sur commencer
                mFlecheX = 20;
                mFlecheY = 480;
                break;
            case OPTIONS:
                mFlecheX = 500;
                mFlecheY = 480;
                break;
            case HIGHSCORE:
                mFlecheX = 200;
                mFlecheY = 600;
                break;


        }

        repaint();


        // Updater le panneau

    }

    public ChoixMenu getChoixMenu() {
        return mChoixMenu;
    }
}
