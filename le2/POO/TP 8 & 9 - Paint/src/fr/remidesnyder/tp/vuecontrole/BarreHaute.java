package fr.remidesnyder.tp.vuecontrole;

import fr.remidesnyder.tp.modele.EnumCouleur;
import fr.remidesnyder.tp.modele.EnumForme;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Desnyder Rémi
 * Date: 19/12/2023
 */

public class BarreHaute extends JPanel implements ActionListener {

    private Fenetre fenetre;
    private JComboBox couleurs;
    private JComboBox formes;
    private JCheckBox checkBoxPlein;
    private JButton boutonEffacer;
    private JButton buttonRetourEnArriere;
    private JButton buttonGomme;
    private JSlider sliderGommeWidth;

    public BarreHaute(Fenetre fenetre) {
        couleurs = new JComboBox( EnumCouleur.values() );
        formes = new JComboBox ( EnumForme.values());
        checkBoxPlein = new JCheckBox("Plein");
        boutonEffacer = new JButton("Effacer");
        buttonRetourEnArriere = new JButton("Retour en arrière");
        buttonGomme = new JButton("Activer Gomme");
        sliderGommeWidth = new JSlider(1, 100, 10);
        this.fenetre = fenetre;

        couleurs.addActionListener(this);
        formes.addActionListener(this);

        boutonEffacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.getZoneGraphique().effacer();
            }
        });

        buttonRetourEnArriere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.getZoneGraphique().retourEnArriere();
            }
        });

        buttonGomme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonGomme.getText().equals("Activer Gomme")) {
                    fenetre.activerGomme();
                } else {
                    fenetre.desactiverGomme();
                }
                buttonGomme.setText(buttonGomme.getText().equals("Activer Gomme") ? "Désactiver Gomme" : "Activer Gomme");
            }
        });

        sliderGommeWidth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fenetre.getZoneGraphique().setGommeWidth(sliderGommeWidth.getValue());
            }
        });

        this.add(couleurs);
        this.add(formes);
        this.add(checkBoxPlein);
        this.add(boutonEffacer);
        this.add(buttonRetourEnArriere);
        this.add(buttonGomme);
        this.add(sliderGommeWidth);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox x = (JComboBox)e.getSource();
        System.out.println("Item choisi : " + x.getSelectedItem());
    }

    public EnumCouleur getCouleurSelectionnee() {
        return (EnumCouleur)this.couleurs.getSelectedItem();
    }

    public EnumForme getFormeSelectionnee() {
        return (EnumForme)this.formes.getSelectedItem();
    }

    public boolean isPlein() {
        return this.checkBoxPlein.isSelected();
    }
}
