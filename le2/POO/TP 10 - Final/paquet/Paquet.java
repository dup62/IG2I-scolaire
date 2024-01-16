package paquet;

import modele.formes.Forme;
import pot.Parfum;
import pot.PotDeGlace;
import vuecontrole.Fenetre;

import java.util.*;

/**
 * Created by Desnyder Rémi
 * Date: 16/01/2024
 */

public abstract class Paquet {

    private int idPaquet;
    private static int nbPaquets = 0;
    private int hauteur;

    private Set<PotDeGlace> pots;

    public Paquet(int hauteur) {
        this.hauteur = Math.max(0, hauteur);
        this.idPaquet = ++nbPaquets;
        this.pots = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paquet paquet = (Paquet) o;
        return idPaquet == paquet.idPaquet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPaquet);
        // Ici idPaquet est unique donc le hashcode est unique donc on peut utiliser idPaquet
    }

    public int getHauteur() {
        return hauteur;
    }

    public abstract double calculerVolumeEnL();

    //public abstract boolean ajouterPotDeGlace(PotDeGlace pot);
    public boolean ajouterPotDeGlace(PotDeGlace pot) {

        if (pot.getHauteur() > this.getHauteur()) {
            //return "false -> hauteur";
            return false;
        }

        if (pots.contains(pot)) {
            //return "false -> idPot";
            return false;
        }

        if (calculerVolumeEnL() < pot.calculerVolumeEnL()) {
            //return "false -> volume (1)";
            return false;
        }

        if (calculerVolumeEnL() < getVolumeUtilise() + pot.calculerVolumeEnL()) {
            //return "false -> volume (2)";
            return false;
        }

        pots.add(pot);

        //return "true";
        return true;
    }

    public double getVolumeUtilise() {
        double volumeUtilise = 0;

        for (PotDeGlace pot : pots) {
            volumeUtilise += pot.calculerVolumeEnL();
        }

        return volumeUtilise;
    }

    public abstract  String getFormePaquet();

    public String genererEtiquette() {
        String etiquette = "Paquet " + this.getFormePaquet()  + "\n" +
                "Ce paquet contient " + getPots().size() + " pots de glace :\n";

        Map<Parfum, Integer> parfums = new HashMap<>();

        for (PotDeGlace pot : getPots()) {
            if (parfums.containsKey(pot.getParfum())) {
                parfums.put(pot.getParfum(), parfums.get(pot.getParfum()) + 1);
            } else {
                parfums.put(pot.getParfum(), 1);
            }
        }

        for (Parfum parfum : parfums.keySet()) {
            etiquette += "- " + parfums.get(parfum) + " pots parfum " + parfum + "\n";
        }

        etiquette += "Volume totale de creme glacée : " + getVolumeUtilise() + " L\n";
        etiquette += "Poids total de creme glacée : " + getVolumeUtilise() * PotDeGlace.getMasseVolumique() + " g";

        return etiquette;
    }

    public abstract Forme getForme();

    public Set<PotDeGlace> getPots() {
        return pots;
    }

    public void dessin() {

        Fenetre fenetre = new Fenetre();

        PaquetRectangulaire paquetRectangulaire = new PaquetRectangulaire(100, 100, 100);
        paquetRectangulaire.getForme().seDessiner(fenetre.getGraphics());

        fenetre.getZoneGraphique().addForme(paquetRectangulaire.getForme());

        //PaquetCirculaire paquetCirculaire = new PaquetCirculaire(10, 10);
        //paquetCirculaire.getForme().seDessiner(fenetre.getGraphics());

    }

    @Override
    public String toString() {
        return "Paquet{" +
                "idPaquet=" + idPaquet +
                ", hauteur=" + hauteur +
                '}';
    }

    public static void main(String[] args) {
        /*-----------------------------------------------------------
        -- 	           Test de la question 4.                    --
        --        A placer dans la méthode principale            --
        --               de la classe Paquet.                    --
        -----------------------------------------------------------*/

        System.out.println("Tests question 4 : classes Paquet, "
                + "PaquetRectangulaire et PaquetCirculaire\n");
        Paquet p1 = new PaquetRectangulaire(6, 11, 11);
        Paquet p2 = new PaquetRectangulaire(7, 13, 19);
        Paquet p3 = new PaquetCirculaire(6, 15);
        Paquet p4 = new PaquetCirculaire(7, 20);
        Paquet p5 = new PaquetCirculaire(7, 20);
        System.out.println(p1); // hauteur 6, largeur 11, longueur 11
        System.out.println(p2); // hauteur 7, largeur 13, longueur 19
        System.out.println(p3); // hauteur 6, diametre 15
        System.out.println(p4); // hauteur 7, diametre 20
        System.out.println(p5); // hauteur 7, diametre 20
        System.out.println("\np1 equals p1 ? " + p1.equals(p1)); // true
        System.out.println("p1 equals p2 ? " + p1.equals(p2)); // false
        System.out.println("p1 equals p3 ? " + p1.equals(p3)); // false
        System.out.println("p4 equals p5 ? " + p4.equals(p5)); // false

        /*-----------------------------------------------------------
        -- 	           Test de la question 5.                    --
        --        A placer dans la méthode principale            --
        --               de la classe Paquet                     --
        --          APRES les tests de la question 4.            --
        -----------------------------------------------------------*/

        System.out.println("\n\nTests question 5 : volume des paquets\n");
        System.out.println("volume de p1 : " + p1.calculerVolumeEnL() + " L"); // 0.726 L
        System.out.println("volume de p2 : " + p2.calculerVolumeEnL() + " L"); // 1.729 L
        System.out.println("volume de p3 : " + p3.calculerVolumeEnL() + " L"); // 1.060 L
        System.out.println("volume de p4 : " + p4.calculerVolumeEnL() + " L"); // 2.199 L

        /*-----------------------------------------------------------
        -- 	           Test de la question 6.                    --
        --        A placer dans la méthode principale            --
        --               de la classe Paquet                     --
        --          APRES les tests de la question 5.            --
        -----------------------------------------------------------*/

        System.out.println("\n\nTests question 6 : ajout des pots de glace "
                + "danss les paquets\n");
        System.out.println("Premiers tests : \n");
        Paquet pTest1 = new PaquetRectangulaire(5, 6, 11);
        Paquet pTest2 = new PaquetCirculaire(7, 10);
        PotDeGlace pdg1 = new PotDeGlace("test1", Parfum.FRAISE, 5, 6);
        PotDeGlace pdg2 = new PotDeGlace("test2", Parfum.FRAISE, 5, 5);
        PotDeGlace pdg3 = new PotDeGlace("test3", Parfum.FRAISE, 5, 5);
        PotDeGlace pdg4 = new PotDeGlace("test1", Parfum.FRAISE, 5, 6);
        PotDeGlace pdg5 = new PotDeGlace("test5", Parfum.FRAISE, 5, 6);
        PotDeGlace pdg6 = new PotDeGlace("test6", Parfum.FRAISE, 9, 5);
        System.out.println("ajout de pdg1 dans ptest1 : "
                + pTest1.ajouterPotDeGlace(pdg1)); // false (hauteur)
        System.out.println("ajout de pdg2 dans ptest1 : "
                + pTest1.ajouterPotDeGlace(pdg2)); // true
        System.out.println("ajout de pdg1 dans ptest2 : "
                + pTest2.ajouterPotDeGlace(pdg1)); // true
        System.out.println("ajout de pdg3 dans ptest1 : "
                + pTest1.ajouterPotDeGlace(pdg3)); // true
        System.out.println("ajout de pdg4 dans ptest2 : "
                + pTest2.ajouterPotDeGlace(pdg4)); // false (meme identifiant que pdg1)
        System.out.println("ajout de pdg5 dans ptest2 : "
                + pTest2.ajouterPotDeGlace(pdg5)); // true
        System.out.println("ajout de pdg6 dans ptest1 : "
                + pTest1.ajouterPotDeGlace(pdg6)); // false (volume depasse)
        System.out.println("ajout de pdg6 dans ptest2 : "
                + pTest2.ajouterPotDeGlace(pdg6)); // false (volume depasse)
        System.out.println("\nLes tests qui suivent doivent tous renvoyer true :\n");
        PotDeGlace g1 = new PotDeGlace("glace1", Parfum.FRAISE, 5, 6);
        PotDeGlace g2 = new PotDeGlace("glace2", Parfum.FRAISE, 5, 6);
        PotDeGlace g3 = new PotDeGlace("glace3", Parfum.VANILLE, 5, 6);
        PotDeGlace g4 = new PotDeGlace("glace4", Parfum.VANILLE, 5, 6);
        PotDeGlace g5 = new PotDeGlace("glace5", Parfum.FRAISE, 6, 7);
        PotDeGlace g6 = new PotDeGlace("glace6", Parfum.FRAISE, 6, 7);
        PotDeGlace g7 = new PotDeGlace("glace7", Parfum.CHOCOLAT, 6, 7);
        PotDeGlace g8 = new PotDeGlace("glace8", Parfum.CHOCOLAT, 6, 7);
        PotDeGlace g9 = new PotDeGlace("glace9", Parfum.PISTACHE, 6, 7);
        PotDeGlace g10 = new PotDeGlace("glace10", Parfum.PISTACHE, 6, 7);
        PotDeGlace g11 = new PotDeGlace("glace11", Parfum.VANILLE, 5, 6);
        PotDeGlace g12 = new PotDeGlace("glace12", Parfum.FRAISE, 5, 6);
        PotDeGlace g13 = new PotDeGlace("glace13", Parfum.PISTACHE, 5, 6);
        PotDeGlace g14 = new PotDeGlace("glace14", Parfum.VANILLE, 5, 6);
        PotDeGlace g15 = new PotDeGlace("glace15", Parfum.FRAISE, 5, 6);
        PotDeGlace g16 = new PotDeGlace("glace16", Parfum.VANILLE, 6, 7);
        PotDeGlace g17 = new PotDeGlace("glace17", Parfum.FRAISE, 6, 7);
        PotDeGlace g18 = new PotDeGlace("glace18", Parfum.CHOCOLAT, 6, 7);
        PotDeGlace g19 = new PotDeGlace("glace19", Parfum.PISTACHE, 6, 7);
        PotDeGlace g20 = new PotDeGlace("glace20", Parfum.VANILLE, 6, 7);
        PotDeGlace g21 = new PotDeGlace("glace21", Parfum.CHOCOLAT, 6, 7);
        PotDeGlace g22 = new PotDeGlace("glace22", Parfum.VANILLE, 6, 7);
        System.out.println("ajout de g1 dans b1 : " + p1.ajouterPotDeGlace(g1)); // true
        System.out.println("ajout de g2 dans b1 : " + p1.ajouterPotDeGlace(g2)); // true
        System.out.println("ajout de g3 dans b1 : " + p1.ajouterPotDeGlace(g3)); // true
        System.out.println("ajout de g4 dans b1 : " + p1.ajouterPotDeGlace(g4)); // true
        System.out.println("ajout de g5 dans b2 : " + p2.ajouterPotDeGlace(g5)); // true
        System.out.println("ajout de g6 dans b2 : " + p2.ajouterPotDeGlace(g6)); // true
        System.out.println("ajout de g7 dans b2 : " + p2.ajouterPotDeGlace(g7)); // true
        System.out.println("ajout de g8 dans b2 : " + p2.ajouterPotDeGlace(g8)); // true
        System.out.println("ajout de g9 dans b2 : " + p2.ajouterPotDeGlace(g9)); // true
        System.out.println("ajout de g10 dans b2 : " + p2.ajouterPotDeGlace(g10)); // true
        System.out.println("ajout de g11 dans b3 : " + p3.ajouterPotDeGlace(g11)); // true
        System.out.println("ajout de g12 dans b3 : " + p3.ajouterPotDeGlace(g12)); // true
        System.out.println("ajout de g13 dans b3 : " + p3.ajouterPotDeGlace(g13)); // true
        System.out.println("ajout de g14 dans b3 : " + p3.ajouterPotDeGlace(g14)); // true
        System.out.println("ajout de g15 dans b3 : " + p3.ajouterPotDeGlace(g15)); // true
        System.out.println("ajout de g16 dans b4 : " + p4.ajouterPotDeGlace(g16)); // true
        System.out.println("ajout de g17 dans b4 : " + p4.ajouterPotDeGlace(g17)); // true
        System.out.println("ajout de g18 dans b4 : " + p4.ajouterPotDeGlace(g18)); // true
        System.out.println("ajout de g19 dans b4 : " + p4.ajouterPotDeGlace(g19)); // true
        System.out.println("ajout de g20 dans b4 : " + p4.ajouterPotDeGlace(g20)); // true
        System.out.println("ajout de g21 dans b4 : " + p4.ajouterPotDeGlace(g21)); // true
        System.out.println("ajout de g22 dans b4 : " + p4.ajouterPotDeGlace(g22)); // true

        /*-----------------------------------------------------------
        -- 	           Test de la question 7.                    --
        --        A placer dans la méthode principale            --
        --               de la classe Paquet                     --
        --          APRES les tests de la question 6.            --
        -----------------------------------------------------------*/

        System.out.println("\n\nTests question 7 : etiquettes des pots de glace\n");
        System.out.println(p1.genererEtiquette());
        System.out.println("\n" + p2.genererEtiquette());
        System.out.println("\n" + p3.genererEtiquette());
        System.out.println("\n" + p4.genererEtiquette());

        /*-----------------------------------------------------------
        -- 	           Test de la question 9.                    --
        --        A placer dans la méthode principale            --
        --               de la classe Paquet                     --
        --          APRES les tests de la question 6.            --
        -----------------------------------------------------------*/

        System.out.println("\n\nTests question 9 : formes des paquets\n");
        System.out.println("forme de p1 : " + p1.getForme());
        System.out.println("\nforme de p2 : " + p2.getForme());
        System.out.println("\nforme de p3 : " + p3.getForme());
        System.out.println("\nforme de p4 : " + p4.getForme());

        /*-----------------------------------------------------------
        -- 	           Test de la question 10.                   --
        --        A placer dans la méthode principale            --
        --               de la classe Paquet                     --
        --          APRES les tests de la question 9.            --
        -----------------------------------------------------------*/

        //p1.dessin();
        //p2.dessin();
        //p3.dessin();
        //p4.dessin();

    }
}
