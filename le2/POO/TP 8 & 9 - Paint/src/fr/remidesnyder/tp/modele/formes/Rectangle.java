package fr.remidesnyder.tp.modele.formes;

import java.awt.*;

/**
 * Created by Desnyder Rémi
 * Date: 19/12/2023
 */

public class Rectangle extends Forme {

    private Point pA;
    private Point pB;
    private boolean plein;

    public Rectangle(Color c, Point p1, Point p2) {
        super(c);
        pA = new Point(p1);
        pB = new Point(p2);
        this.plein = false;
    }

    public Rectangle(Color c, Point p1, Point p2, boolean plein) {
        this(c, p1, p2);
        this.plein = plein;
    }

    @Override
    public void seDessiner(Graphics g) {
        super.seDessiner(g);
        if (plein)
            g.fillRect(pA.getX(), pA.getY(), pB.getX() - pA.getX(), pB.getY() - pA.getY());
        else
            g.drawRect(pA.getX(), pA.getY(), pB.getX() - pA.getX(), pB.getY() - pA.getY());
    }
}

