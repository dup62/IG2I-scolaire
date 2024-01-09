package fr.remidesnyder.tp.modele.formes;

import java.awt.*;

/**
 * Created by Desnyder Rémi
 * Date: 09/01/2024
 */

public class TriangleEquilateral extends Forme {

    private Point pA;
    private Point pB;
    private boolean plein;

    public TriangleEquilateral(Color c, Point p1, Point p2) {
        super(c);
        pA = new Point(p1);
        pB = new Point(p2);
        this.plein = false;
    }

    public TriangleEquilateral(Color c, Point p1, Point p2, boolean plein) {
        this(c, p1, p2);
        this.plein = plein;
    }

    @Override
    public void seDessiner(Graphics g) {
        super.seDessiner(g);
        if (plein) {
            g.fillPolygon(new int[]{pA.getX(), pB.getX(), pA.getX()}, new int[]{pA.getY(), pB.getY(), pB.getY()}, 3);
        } else {
            g.drawPolygon(new int[]{pA.getX(), pB.getX(), pA.getX()}, new int[]{pA.getY(), pB.getY(), pB.getY()}, 3);
        }
    }
}
