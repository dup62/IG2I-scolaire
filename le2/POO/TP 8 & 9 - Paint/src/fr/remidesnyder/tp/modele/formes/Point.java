package fr.remidesnyder.tp.modele.formes;

/**
 * Created by Desnyder Rémi
 * Date: 19/12/2023
 */

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = Math.max(x, 0);
        this.y = Math.max(y, 0);
    }

    public Point(Point p) {
        if( p != null ) {
            this.x = p.x;
            this.y = p.y;
        } else {
            this.x = 0;
            this.y = 0;
        }
    }

    /**
     * Renvoie la distance entre le point p et l'instance.
     * Renvoie -1 si p vaut null.
     * @param p
     */
    public int getDistance(Point p) {
        if( p == null) return -1;

        return (int) Math.sqrt( Math.pow(this.x - p.x, 2) + Math.pow(this.y-p.y, 2)  );
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
