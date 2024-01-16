package vuecontrole;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Desnyder Rémi
 * Date: 07/12/2023
 */

public class Fenetre extends JFrame {

    private BarreBasse barreBasse;
    private BarreHaute barreHaute;
    private ZoneGraphique zoneGraphique;
    private EcouteurFenetre ecouteurFenetre;

    public Fenetre() {
        super("TP JAVA - Paint - Desnyder Rémi");

        this.barreBasse = new BarreBasse();
        this.barreHaute = new BarreHaute(this);
        this.zoneGraphique = new ZoneGraphique(barreBasse, barreHaute);
        this.ecouteurFenetre = new EcouteurFenetre();

        this.addWindowListener(ecouteurFenetre);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.add(this.barreBasse, BorderLayout.SOUTH);
        this.add(this.zoneGraphique, BorderLayout.CENTER);
        this.add(barreHaute,BorderLayout.NORTH);
        this.setVisible(true);

    }

    public ZoneGraphique getZoneGraphique() {
        return zoneGraphique;
    }

    public void activerGomme() {
        this.zoneGraphique.activerGomme();
    }

    public void desactiverGomme() {
        this.zoneGraphique.desactiverGomme();
    }

    public static void main(String[] args) {

        Fenetre fenetre = new Fenetre();
        //fenetre.setContentPane(new BarreBasse());
    }

}
