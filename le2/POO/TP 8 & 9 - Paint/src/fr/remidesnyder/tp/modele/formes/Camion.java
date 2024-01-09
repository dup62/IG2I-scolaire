package fr.remidesnyder.tp.modele.formes;

import java.awt.*;

/**
 * Created by Desnyder Rémi
 * Date: 09/01/2024
 */

public class Camion extends Forme {

    private Point pInit;
    private Point pFin;

    private Rectangle corps;
    private Rectangle fenetre;
    private Rectangle avant;
    private Cercle roueAvant;
    private Cercle roueArriere;

    public Camion(Color couleurForme, Point pInit, Point pFin) {
        super(couleurForme);

        this.pInit = new Point(pInit);
        this.pFin = new Point(pFin);

        this.corps = new Rectangle(couleurForme, pInit, pFin);

        /**
         *
         *             l l l l l l l l l l l l l
         * l l l l l l l                       l
         * l           l                       l
         * l   l l l   l                       l
         * l   l   l   l                       l
         * l   l l l   l                       l
         * l           l                       l
         * l l l l l l l l l l l l l l l l l l l
         *      l   l                l   l
         *      l l l                l l l
         */

        this.fenetre = new Rectangle(couleurForme, new Point(pInit.getX() + 10, pInit.getY() + 10), new Point(pInit.getX() + 30, pInit.getY() + 30));
        this.avant = new Rectangle(couleurForme, new Point(pInit.getX() + 30, pInit.getY() + 10), new Point(pInit.getX() + 50, pInit.getY() + 30));
        this.roueAvant = new Cercle(couleurForme, new Point(pInit.getX() + 10, pInit.getY() + 50), new Point(pInit.getX() + 30, pInit.getY() + 70), false);
        this.roueArriere = new Cercle(couleurForme, new Point(pInit.getX() + 50, pInit.getY() + 50), new Point(pInit.getX() + 70, pInit.getY() + 70), false);
        this.corps = new Rectangle(couleurForme, new Point(pInit.getX() + 10, pInit.getY() + 70), new Point(pInit.getX() + 70, pInit.getY() + 90));

    }


    @Override
    public void seDessiner(Graphics g) {
        super.seDessiner(g);

        this.corps.seDessiner(g);
        this.fenetre.seDessiner(g);
        this.avant.seDessiner(g);
        this.roueAvant.seDessiner(g);
        this.roueArriere.seDessiner(g);
    }

}
