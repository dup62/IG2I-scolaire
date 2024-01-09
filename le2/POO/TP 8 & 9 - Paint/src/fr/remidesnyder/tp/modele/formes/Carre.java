package fr.remidesnyder.tp.modele.formes;

import java.awt.*;

/**
 * Created by Desnyder Rémi
 * Date: 19/12/2023
 */

public class Carre extends Forme {

    private Point pA;
    private Point pB;
    private boolean plein;

    public Carre(Color c, Point p1, Point p2) {
        super(c);
        pA = new Point(p1);
        pB = new Point(p2);
        this.plein = false;
    }

    public Carre(Color c, Point p1, Point p2, boolean plein) {
        this(c, p1, p2);
        this.plein = plein;
    }

    @Override
    public void seDessiner(Graphics g) {
        super.seDessiner(g);

        int x = pA.getX();
        int y = pA.getY();
        int width = pB.getX() - pA.getX();
        int height = pB.getY() - pA.getY();

        if (plein) {
            g.fillRect(x, y, width, height);
        } else {
            g.drawRect(x, y, width, height);
        }
    }
}

