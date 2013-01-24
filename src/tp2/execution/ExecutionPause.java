/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.execution;

import java.awt.event.KeyEvent;
import tp2.partie.Partie;
import tp2.partie.objets.voiture.Joueur;

/**
 *
 * @author Usager
 */
public class ExecutionPause
{

    private static ExecutionPause instance = new ExecutionPause();

    private ExecutionPause()
    {
    }

    public void keyPressed(int key)
    {
        switch (key)
        {
            case KeyEvent.VK_P:
                Partie.getInstance().setPause(false);
                break;
        }
    }

    public static ExecutionPause getInstance()
    {
        return instance;
    }
}
