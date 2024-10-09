package vue;

import modele.Emprunt;
import modele.Livre;
import requete.RequeteBiblio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Desnyder Rémi
 * Date: 25/09/2024
 */

public class PanneauLivres extends JPanel {

    private JLabel etiquetteRecherche;
    private JTextField filtreRecherche;
    private JList<Livre> listeLivres;
    private JScrollPane scrollPane;

    private String recherche;

    private RequeteBiblio requeteBiblio;
    private FenetreBiblio fenetreBiblio;
    private MyListener ecouteur;

    private HashSet<Emprunt> emprunts;

    public PanneauLivres(RequeteBiblio requeteBiblio, FenetreBiblio fenetreBiblio) {
        super();

        this.ecouteur = new MyListener(this);

        this.requeteBiblio = requeteBiblio;
        this.fenetreBiblio = fenetreBiblio;

        this.initComponents();
        this.initLayout();

    }

    private void initComponents() {
        this.etiquetteRecherche = new JLabel("Rechercher : ");
        this.filtreRecherche = new JTextField(50);
        this.filtreRecherche.addKeyListener(this.ecouteur);

        getListeEmpunts();

        this.listeLivres = new JList<>();
        this.listeLivres.addMouseListener(new MyMouseListener(this, emprunts));

        MyRenderer rendu = new MyRenderer(this);
        this.listeLivres.setCellRenderer(rendu);

        updateListeLivres("");

        this.scrollPane = new JScrollPane(this.listeLivres);
    }

    private void getListeEmpunts() {
        try {
            this.emprunts = this.requeteBiblio.getEmpruntActuel();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des emprunts", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void updateListeLivres(String recherche) {
        try {
            List<Livre> livres = this.requeteBiblio.getLivreWhereTitleOrEditeurOrAuteur(recherche);

            DefaultListModel<Livre> model = new DefaultListModel<>();
            for (Livre livre : livres) {
                model.addElement(livre);
            }

            this.listeLivres.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des livres", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
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

    public static class MyListener implements KeyListener {

        private PanneauLivres panneauLivres;

        public MyListener(PanneauLivres panneauLivres) {
            this.panneauLivres = panneauLivres;
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            JTextField source = (JTextField) e.getSource();
            String recherche = source.getText();
            panneauLivres.updateListeLivres(recherche);
        }
    }

    public static class MyRenderer implements ListCellRenderer<Livre> {

        private PanneauLivres panneauLivres;

        public MyRenderer(PanneauLivres panneauLivres) {
            this.panneauLivres = panneauLivres;
        }

        @Override
        public Component getListCellRendererComponent(
                JList<? extends Livre> list,
                Livre value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {

            JLabel label = new JLabel();

            label.setText(value.getAffichageStandard());

            if (cellHasFocus) {
                label.setForeground(Color.BLACK);
                label.setOpaque(true);
                label.setBackground(Color.YELLOW);
            } else {
                label.setForeground(Color.GREEN);
            }

            for (Emprunt emprunt : this.panneauLivres.emprunts) {

                //System.out.println("1" + emprunt.getLivre());
                //System.out.println("2" + value);
                //System.out.println("3" + emprunt.getLivre().equals(value));

                if (emprunt.getLivre().equals(value)) {
                    label.setForeground(Color.RED);
                    break;
                }
            }

            return label;
        }

    }

    public static class MyMouseListener implements MouseListener {

        private PanneauLivres panneauLivres;
        private HashSet<Emprunt> emprunts;

        public MyMouseListener(PanneauLivres panneauLivres, HashSet<Emprunt> emprunts) {
            this.panneauLivres = panneauLivres;
            this.emprunts = emprunts;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                JList source = (JList) e.getSource();
                Livre livre = (Livre) source.getSelectedValue();

                if (livre == null) {
                    return;
                }

                if (!emprunts.isEmpty()) {
                    for (Emprunt emprunt : this.emprunts) {
                        if (emprunt.getLivre().equals(livre)) {
                            //JOptionPane.showMessageDialog(null, "Le livre est déjà emprunté", "Erreur", JOptionPane.ERROR_MESSAGE);
                            //return;

                            JPopupMenu popupMenu = new JPopupMenu();
                            JMenuItem menuItem = new JMenuItem("Rendre le livre");
                            menuItem.addActionListener(e1 -> {
                                try {
                                    this.panneauLivres.requeteBiblio.giveBackLivre(emprunt.getLivre());
                                    this.panneauLivres.getListeEmpunts();
                                    this.panneauLivres.updateListeLivres("");
                                } catch (SQLException e2) {
                                    JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour de la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
                                }
                            });
                            popupMenu.add(menuItem);
                            popupMenu.show(source, e.getX(), e.getY());
                            return;

                        }
                    }
                }


            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
