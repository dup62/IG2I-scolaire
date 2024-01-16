package modele.formes;

import java.awt.*;

/**
 * Created by Desnyder Rémi
 * Date: 09/01/2024
 */

public class Cercle extends Forme {

    /**
     * Point situé en haut à gauche du cercle.
     */
    private Point pHautGauche;

    private int diametre;
    private boolean plein;

    /**
     * Constructeur par données de la classe Cercle.
     * Si un point vaut null, alors le point en question sera remplacé par un point de coordonnées (0,0).
     * Le diamètre est la distance entre pDebut et pFinal.
     *
     * @param pDebut       Point représentant l'appui sur la souris pour dessiner.
     * @param pFinal       Point représentant le relachement de la souris.
     * @param couleurForme La couleur du dessin.
     */
    public Cercle(Color couleurForme, Point pDebut, Point pFinal) {
        super(couleurForme);
        if (pDebut == null) {
            pDebut = new Point(0, 0);
        }
        if (pFinal == null) {
            pFinal = new Point(0, 0);
        }

        int diffX = pFinal.getX() - pDebut.getX();
        int diffY = pFinal.getY() - pDebut.getY();

        this.diametre = Math.min(Math.abs(diffX), Math.abs(diffY));


        int x = pDebut.getX();
        int y = pDebut.getY();

        if (diffX < 0)
            x = x - this.diametre;
        if (diffY < 0)
            y = y - this.diametre;

        this.pHautGauche = new Point(x, y);

        this.plein = false;
    }

    public Cercle(Color couleurForme, Point pDebut, Point pFinal, boolean plein) {
        this(couleurForme, pDebut, pFinal);
        this.plein = plein;
    }


    @Override
    public void seDessiner(Graphics g) {
        super.seDessiner(g);

        if (plein)
            g.fillOval(this.pHautGauche.getX(), this.pHautGauche.getY(), this.diametre, this.diametre);
        else
            g.drawOval(this.pHautGauche.getX(), this.pHautGauche.getY(), this.diametre, this.diametre);
    }


}