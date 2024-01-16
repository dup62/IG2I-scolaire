package paquet;

import modele.formes.Cercle;
import modele.formes.Forme;
import modele.formes.Point;
import pot.Parfum;
import pot.PotDeGlace;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Desnyder Rémi
 * Date: 16/01/2024
 */

public class PaquetCirculaire extends Paquet {

    /**
     * Diamètre du paquet en cm
     */
    private int diametre;

    public PaquetCirculaire(int hauteur, int diametre) {
        super(hauteur);
        this.diametre = Math.max(0, diametre);
    }

    public double calculerVolumeEnL() {
        return Math.PI * Math.pow(diametre, 2) * super.getHauteur() / (4 * 1000);
    }

    public Forme getForme() {
        return new Cercle(Color.BLACK, new Point(10, 10), new Point(10 + diametre, 10 + diametre));
    }

    public String getFormePaquet() {
        return "Circulaire";
    }

    @Override
    public String toString() {
        return "PaquetCirculaire{" +
                super.toString() +
                "diametre=" + diametre +
                '}';
    }
}
