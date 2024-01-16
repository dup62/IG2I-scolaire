package paquet;

import modele.EnumForme;
import modele.formes.Forme;
import modele.formes.Point;
import modele.formes.Rectangle;
import pot.Parfum;
import pot.PotDeGlace;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Desnyder Rémi
 * Date: 16/01/2024
 */

public class PaquetRectangulaire extends Paquet{

    private int largeur;
    private int longueur;

    public PaquetRectangulaire(int hauteur, int largeur, int longueur) {
        super(hauteur);
        this.largeur = Math.max(0, largeur);
        this.longueur = Math.max(0, longueur);
    }

    public double calculerVolumeEnL() {
        return largeur * longueur * super.getHauteur() / 1000.0;
    }

    public Forme getForme() {
        return new Rectangle(Color.BLUE, new Point(100, 100), new Point(100 + largeur, 100 + longueur));
    }

    public String getFormePaquet() {
        return "Rectangulaire";
    }

    @Override
    public String toString() {
        return "PaquetRectangulaire{" +
                super.toString() +
                ", largeur=" + largeur +
                ", longueur=" + longueur +
                '}';
    }
}
