package fr.remidesnyder.tp.modele.formes;

import java.awt.*;

/**
 * Created by Desnyder Rémi
 * Date: 19/12/2023
 */

public class Droite extends Forme {

    private Point pA;
    private Point pB;

    public Droite(Color c, Point p1, Point p2) {
        super(c);
        pA = new Point(p1);
        pB = new Point(p2);
    }


    @Override
    public void seDessiner(Graphics g) {
        super.seDessiner(g);
        g.drawLine(pA.getX(), pA.getY(), pB.getX(), pB.getY());
    }
}

