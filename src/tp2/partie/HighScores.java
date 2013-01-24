/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.partie;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Vincent
 */
public class HighScores {

    private ArrayList<Score> highscore; // top 5

    public HighScores() {
        loadHighScores();
    }

    private void loadHighScores() {
        try {
            FileInputStream fichier = new FileInputStream("HighScores.dat");
            ObjectInputStream ois = new ObjectInputStream(fichier);

            highscore = (ArrayList<Score>) ois.readObject();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void ecrireHighScore() {
        try {
            FileOutputStream fos = new FileOutputStream("HighScores.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(highscore);
            oos.flush();
            oos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void addScore(int points) {
        boolean isHighScore = false;
        int i = -1;
        for (Score hs : highscore) {
            if (hs.getScore() < points) {
                isHighScore = true;
                i = highscore.indexOf(hs);
                break;
            }
        }
        if (isHighScore) {
            boolean ok = true;
            String rep = "";
            do {
                rep = JOptionPane.showInputDialog(null, "Vous avez obtenu un des 5 meilleurs scores du jeu! Veuillez entrer votre nom.", "Félicitation! ", JOptionPane.WARNING_MESSAGE);
            // Verifer nom? contraintes?
            
            } while (!ok);
            highscore.add(i, new Score(points, rep));
            highscore.remove(6);
        }

    }
}
