/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.execution;

import java.awt.event.KeyEvent;
import tp2.partie.Partie;
import tp2.partie.son.Son;
import tp2.partie.objets.voiture.Joueur;
import tp2.partie.objets.armes.Tir;

/**
 *
 * @author Vincent
 */
public class ExecutionPartie {

    private static ExecutionPartie instance = new ExecutionPartie();

    private ExecutionPartie() {
    }

    public void keyPressed(int key) {
        Joueur heros = Partie.getInstance().getHeros();

        switch (key) {
            case KeyEvent.VK_ESCAPE://escape

                break;
            case KeyEvent.VK_ENTER: // enter
            case KeyEvent.VK_SPACE: // space
                Partie.getInstance().tirJoueur();
                break;
            case KeyEvent.VK_INSERT :
            case KeyEvent.VK_S :
            case KeyEvent.VK_SHIFT :
                Partie.getInstance().huileJoueur();  
                break;
            case KeyEvent.VK_LEFT:
                heros.setVx(-heros.getDeplacementlateral());
                break;
            case KeyEvent.VK_RIGHT:
                heros.setVx(heros.getDeplacementlateral());
                break;
            case KeyEvent.VK_UP:
                heros.setAcceleration(heros.getAccelerationMax());
                break;
            case KeyEvent.VK_DOWN:
                heros.setAcceleration(-heros.getAccelerationMax());
                break;
            case KeyEvent.VK_P:
                Partie.getInstance().setPause(true);
                break;
                // Controle de la musique
            case KeyEvent.VK_B:
                Son s = Partie.getInstance().getS();
                s.setBit8(!s.getBit8());
                break;
            case KeyEvent.VK_M:
                Partie.getInstance().getS().muteAll();
                break;
            case KeyEvent.VK_N:
                Partie.getInstance().getS().changerToune();
                break;

            case 45: // =
                break;
            case 65: // a 
                break;
            case 68: // d 
                break;
            case 87: // w 
                break;

        }
    }

    public void keyReleased(int key) {
        Joueur heros = Partie.getInstance().getHeros();
        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                heros.setVx(0);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_UP:
                heros.setAcceleration(0);
                break;
        }
    }


    public static ExecutionPartie getInstance() {
        return instance;
    }
}
