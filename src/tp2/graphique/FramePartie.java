package tp2.graphique;

import tp2.graphique.panels.PanelPause;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import tp2.execution.ExecutionPartie;
import tp2.execution.ExecutionPause;


/**
 * Vue pour la partie.
 * 
 * @author Vincent
 */
public class FramePartie extends JFrame {

    private Carte mCarte;
    private PanelPause mPanelPause;
    private Dimension mDimension = new Dimension(1005, 775);
    private Stats mStats;

    public FramePartie(Dimension dPnlBoard) {
        mPanelPause = new PanelPause();
        mCarte = new Carte(dPnlBoard);
        mStats = new Stats();
        this.add(mCarte);
        this.add(mPanelPause);
        this.add(mStats);
        mStats.setLocation(0,dPnlBoard.height);        
        
        setTitle("SpyHunter");
        setSize(mDimension);
        setPreferredSize(mDimension);
        setResizable(false);
        setVisible(true);
        setPause(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Carte getCarte() {
        return mCarte;
    }

    public void setPause(boolean isPause) {
        for (KeyListener blabla : this.getKeyListeners()) {
            removeKeyListener(blabla);
        }
        if (isPause) {
            mCarte.setVisible(false);
            mPanelPause.setVisible(true);
            mPanelPause.invalidate();
            mPanelPause.repaint();

            addKeyListener(new KeyListener() {

                @Override
                public void keyPressed(KeyEvent e) {
                    ExecutionPause.getInstance().keyPressed(e.getKeyCode());
                }

                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        } else {
            mPanelPause.setVisible(false);
            mCarte.setVisible(true);
            addKeyListener(new KeyListener() {

                @Override
                public void keyPressed(KeyEvent e) {
                    ExecutionPartie.getInstance().keyPressed(e.getKeyCode());
                }

                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    ExecutionPartie.getInstance().keyReleased(e.getKeyCode());
                }
            });
        }
        invalidate();
        repaint();
    }
}
