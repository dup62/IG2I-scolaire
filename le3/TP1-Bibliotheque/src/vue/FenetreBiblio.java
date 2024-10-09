package vue;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Desnyder Rémi
 * Date: 25/09/2024
 */

public class FenetreBiblio extends JFrame {

    private PanneauLivres panneauLivres;
    private PanneauEmprunt panneauEmprunt;

    public static final Color COULEUR_FOND = new Color(38, 84, 124);
    //public static final Color COULEUR_FOND = new Color(134, 211, 124);

    public FenetreBiblio() {

        super();

        initFenetre();
        initComponents();
        initLayout();

        this.setVisible(true);
    }

    private void initFenetre() {

        this.setTitle("Bibliothèque");
        this.setBackground(COULEUR_FOND);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(1000, 500);

    }

    private void initComponents() {
        this.panneauLivres = new PanneauLivres();
        this.panneauEmprunt = new PanneauEmprunt();
    }

    private void initLayout() {

        GridLayout layout = new GridLayout(2, 1);
        this.setLayout(layout);

        this.add(this.panneauLivres);
        this.add(this.panneauEmprunt);
    }

    public static void main(String[] args) {
        FenetreBiblio fenetre  = new FenetreBiblio();
    }
}
