package tp2.partie;

import java.io.Serializable;

/**
 * Interface pour afficher les meilleurs scores.
 *
 * @author user1
 */
public class Score implements Serializable {

    private int score;
    private String nomjoueur;

    public Score(int score, String nomjoueur) {
        this.score = score;
        this.nomjoueur = nomjoueur;
    }

    public String getNomjoueur() {
        return nomjoueur;
    }

    public int getScore() {
        return score;
    }
}
