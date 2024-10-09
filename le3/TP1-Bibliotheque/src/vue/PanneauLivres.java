package vue;

import modele.Livre;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Desnyder Rémi
 * Date: 25/09/2024
 */

public class PanneauLivres extends JPanel {

    private JLabel etiquetteRecherche;
    private JTextField filtreRecherche;
    private JList<Livre> listeLivres;
    private JScrollPane scrollPane;

    public PanneauLivres() {
        super();

        this.initComponents();
        this.initLayout();

    }

    private void initComponents() {
        this.etiquetteRecherche = new JLabel("Rechercher : ");
        this.filtreRecherche = new JTextField(50);
        this.listeLivres = new JList<>();
        this.scrollPane = new JScrollPane(this.listeLivres);
    }

    private void initLayout() {

        /* Panneau du haut */
        JPanel panneauTop = new JPanel();
        JPanel panneauTop1 = new JPanel();
        JPanel panneauTop2 = new JPanel();
        JPanel panneauTop3 = new JPanel();

        panneauTop2.add(this.etiquetteRecherche);
        panneauTop2.add(this.filtreRecherche);

        panneauTop.setBackground(FenetreBiblio.COULEUR_FOND);
        panneauTop1.setBackground(FenetreBiblio.COULEUR_FOND);
        panneauTop2.setBackground(FenetreBiblio.COULEUR_FOND);
        panneauTop3.setBackground(FenetreBiblio.COULEUR_FOND);

        GridLayout layoutTop = new GridLayout(3, 1);
        panneauTop.setLayout(layoutTop);

        panneauTop.add(panneauTop1);
        panneauTop.add(panneauTop2);
        panneauTop.add(panneauTop3);

        /* Panneau Général */
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.add(this.scrollPane, BorderLayout.CENTER);
        this.add(panneauTop, BorderLayout.NORTH);
        this.add(Box.createHorizontalStrut(50), BorderLayout.EAST);
        this.add(Box.createHorizontalStrut(50), BorderLayout.WEST);

        this.setBackground(FenetreBiblio.COULEUR_FOND);

    }

}
