/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.graphique;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tp2.execution.ExecutionMenu;

/**
 *
 * @author Vincent
 */
public class FrameMenu extends JFrame {

   
    private static FrameMenu instance = new FrameMenu();

    public static FrameMenu getInstance() {
        return instance;
    }
    /**
     * Singleton
     */
    private Dimension dFrame = new Dimension(1000, 700);
    private JPanel highscore = new JPanel();
    private JPanel background = new JPanel();
    /**
     * Menu sélectionné
     */
    private ChoixMenu mChoixMenu = ChoixMenu.COMMENCER;

    // private Image background;
    // private Image fleche;
    private FrameMenu() {

        this.add(background);

        // Gestion des entrées du clavier
        addKeyListener(new ExecutionMenu());

        setTitle("SpyHunter");
        setSize(dFrame);
        setPreferredSize(dFrame);
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setChoixMenu(ChoixMenu pChoixMenu) {

        mChoixMenu = pChoixMenu;

        // Updater le panneau

    }

    public ChoixMenu getChoixMenu() {
        return mChoixMenu;
    }
}
