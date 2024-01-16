package modele.formes;

import java.awt.Color;
import java.awt.Graphics;

public class Gomme extends Forme {

    private Point centre;

    int rayon;

    /**
     * Constructeur par données de la classe Gomme
     * Si rayon est strictement inférieur à 0, l'attribut associé vaudra 1
     * @param couleurForme
     * @param centre
     * @param rayon
     */
    public Gomme(Color couleurForme, Point centre, int rayon) {
        super(couleurForme);
        this.centre = new Point(centre);

        if( rayon > 0)
            this.rayon = rayon;
        else
            this.rayon = 1;
    }


    @Override
    public void seDessiner(Graphics g)
    {
        super.seDessiner(g);

        g.fillOval(this.centre.getX() - rayon , this.centre.getY() - rayon, 2*rayon, 2*rayon);
    }

}