package modele;

import java.awt.*;

public enum EnumCouleur {

    ROUGE(Color.RED),
    BLEU(Color.BLUE),
    VERT(Color.GREEN),
    JAUNE(Color.YELLOW),
    MAGENTA(Color.MAGENTA),
    NOIR(Color.BLACK),
    LIGHT_BLUE(Color.CYAN),
    ORANGE(Color.ORANGE);
    private Color couleur;

    private EnumCouleur(Color c) {
        if( c != null ) this.couleur = c;
    }

    public Color getCouleur() {
        return couleur;
    }
}
