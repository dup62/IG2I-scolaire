package vue;

import modele.Emprunt;
import modele.Livre;
import modele.Usager;
import requete.RequeteBiblio;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Desnyder Rémi
 * Date: 25/09/2024
 */

public class PanneauEmprunt extends JPanel {

    private RequeteBiblio requeteBiblio;
    private FenetreBiblio fenetreBiblio;

    private List<Usager> usagers;

    private JLabel etiquetteNomLivre;
    private JComboBox<Usager> listeUsagers;
    private JButton boutonEmprunter;

    public PanneauEmprunt(RequeteBiblio requeteBiblio, FenetreBiblio fenetreBiblio) {

        super();

        this.requeteBiblio = requeteBiblio;
        this.fenetreBiblio = fenetreBiblio;

        getUsagers();

        this.initComponents();
        this.initLayout();

        this.setBackground(FenetreBiblio.COULEUR_FOND);
    }

    private void initComponents() {

        if (this.fenetreBiblio.isSeenEmprunt()) {

            this.etiquetteNomLivre = new JLabel("Emprunt du livre '':");
            this.listeUsagers = new JComboBox<>();
            this.listeUsagers.setModel(new DefaultComboBoxModel<>(this.usagers.toArray(new Usager[0])));
            this.listeUsagers.setRenderer(new MyRenderer());

            this.boutonEmprunter = new JButton("Valider l'emprunt");
        }
    }

    private void initLayout() {

        if (this.fenetreBiblio.isSeenEmprunt()) {

            GridLayout layout = new GridLayout(3, 3);
            this.setLayout(layout);

            // Vide etiquetteNomLivre Vide
            // Vide listeUsagers Vide
            // Vide boutonEmprunter Vide

            this.add(new JLabel());
            this.add(this.etiquetteNomLivre);
            this.add(new JLabel());

            this.add(new JLabel());
            this.add(this.listeUsagers);
            this.add(new JLabel());

            this.add(new JLabel());
            this.add(this.boutonEmprunter);
            this.add(new JLabel());

        }
    }

    private void getUsagers() {
        try {
            this.usagers = this.requeteBiblio.getUsagerOrderByName();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des usagers", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public static class MyRenderer implements ListCellRenderer<Usager> {

        @Override
        public Component getListCellRendererComponent(
                JList<? extends Usager> list,
                Usager value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {

            JLabel label = new JLabel();

            label.setText(value.getAffichageStandard());

            return label;
        }

    }

}
