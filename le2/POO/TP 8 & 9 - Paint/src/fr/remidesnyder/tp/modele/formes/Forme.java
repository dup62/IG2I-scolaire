package fr.remidesnyder.tp.modele.formes;

import java.awt.*;

/**
 * Created by Desnyder Rémi
 * Date: 19/12/2023
 */

public abstract class Forme {

    private Color couleurForme;

    public Forme(Color couleurForme) {
        if( couleurForme != null)
            this.couleurForme = couleurForme;
        else
            this.couleurForme = Color.BLACK;
    }


    public void seDessiner(Graphics g) {
        if( g != null) g.setColor(couleurForme);
    }
}
