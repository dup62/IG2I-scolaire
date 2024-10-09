package vue;

import modele.Livre;
import requete.RequeteBiblio;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by Desnyder Rémi
 * Date: 25/09/2024
 */

public class FenetreBiblio extends JFrame {

    private PanneauLivres panneauLivres;
    private PanneauEmprunt panneauEmprunt;

    public static final Color COULEUR_FOND = new Color(38, 84, 124);
    //public static final Color COULEUR_FOND = new Color(134, 211, 124);

    private RequeteBiblio requeteBiblio;

    private boolean seenEmprunt = false;
    private Livre livreEmprunt = null;

    public FenetreBiblio() {

        super();

        initConnexionBdd();

        initFenetre();
        initComponents();
        initLayout();


        this.setVisible(true);
    }

    private void initConnexionBdd() {
        try {
            this.requeteBiblio = new RequeteBiblio();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            System.exit(1);
        }
    }

    private void initFenetre() {

        this.setTitle("Bibliothèque");
        this.setBackground(COULEUR_FOND);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(1000, 500);

    }

    private void initComponents() {
        this.panneauLivres = new PanneauLivres(this.requeteBiblio, this);
        this.panneauEmprunt = new PanneauEmprunt(this.requeteBiblio, this);
    }

    private void initLayout() {

        GridLayout layout = new GridLayout(2, 1);
        this.setLayout(layout);

        this.add(this.panneauLivres);
        this.add(this.panneauEmprunt);
    }

    public static void main(String[] args) {
        FenetreBiblio fenetre = new FenetreBiblio();
    }

    public boolean isSeenEmprunt() {
        return seenEmprunt;
    }

    public void setSeenEmprunt(boolean seenEmprunt) {
        this.seenEmprunt = seenEmprunt;
    }

    public Livre getLivreEmprunt() {
        return livreEmprunt;
    }

    public void setLivreEmprunt(Livre livreEmprunt) {
        this.livreEmprunt = livreEmprunt;
    }
}
