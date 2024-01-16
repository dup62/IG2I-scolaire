package pot;

import java.util.Objects;

/**
 * Created by Desnyder Rémi
 * Date: 16/01/2024
 */

public class PotDeGlace {

    private String idPot;
    private Parfum parfum;

    /**
     * En cm
     */
    private int diametre;
    /**
     * En cm
     */
    private int hauteur;
    /**
     * En kg/L
     */
    private static final double MASSE_VOLUMIQUE = 1.2;

    // private -> lecture / ecriture seulement a l'interieur de la classe
    // Encapsulation -> Garanti la cohérence, logique d'utilisation

    public PotDeGlace(String idPot, Parfum parfum, int diametre, int hauteur) {
        this.idPot = idPot;

        if (parfum == null) {
            this.parfum = Parfum.CHOCOLAT;
        } else {
            this.parfum = parfum;
        }

        this.diametre = Math.max(0, diametre);
        this.hauteur = Math.max(0, hauteur);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PotDeGlace that = (PotDeGlace) o;
        return Objects.equals(idPot, that.idPot);
    }

    // SI equals = true alors le hascode est le même

    @Override
    public int hashCode() {
        return Objects.hash(idPot);
    }

    @Override
    public String toString() {
        return "PotDeGlace{" +
                "idPot='" + idPot + '\'' +
                ", parfum=" + parfum +
                ", diametre=" + diametre +
                ", hauteur=" + hauteur +
                '}';
    }

    public String getIdPot() {
        return idPot;
    }

    public Parfum getParfum() {
        return parfum;
    }

    public int getDiametre() {
        return diametre;
    }

    public int getHauteur() {
        return hauteur;
    }

    public static double getMasseVolumique() {
        return MASSE_VOLUMIQUE;
    }

    public double calculerVolumeEnL() {
        return this.hauteur * Math.PI * Math.pow(this.diametre, 2) / (4 * 1000);
    }

    // Methode static => Pas besoin de créer une instance (Comme Math.pow)

    public double calculerPoidsEnG() {
        return calculerVolumeEnL() * MASSE_VOLUMIQUE * 1000;
    }

    public static void main(String[] args) {
        /*-----------------------------------------------------------
        -- 	           Test de la question 2.                    --
        --        A placer dans la méthode principale            --
        --             de la classe PotDeGlace.                  --
        -----------------------------------------------------------*/

        System.out.println("Tests question 2 : classe PotDeGlace\n");
        PotDeGlace g1 = new PotDeGlace("glace1", Parfum.VANILLE, 5, 6);
        PotDeGlace g2 = new PotDeGlace("glace2", Parfum.CHOCOLAT, 6, 7);
        PotDeGlace g3 = new PotDeGlace("glace1", Parfum.FRAISE, 5, 6);
        PotDeGlace g4 = new PotDeGlace("glace4", Parfum.FRAISE, 5, 6);
        System.out.println(g1); // diamtre 5, hauteur 6
        System.out.println(g2); // diamtre 6, hauteur 7
        System.out.println(g3); // diamtre 5, hauteur 6
        System.out.println(g4); // diamtre 5, hauteur 6
        System.out.println("\ng1 equals g1 ? " + g1.equals(g1)); // true
        System.out.println("g1 equals g2 ? " + g1.equals(g2)); // false
        System.out.println("g2 equals g1 ? " + g2.equals(g1)); // false
        System.out.println("g1 equals g3 ? " + g1.equals(g3)); // true
        System.out.println("g3 equals g2 ? " + g3.equals(g2)); // false
        System.out.println("g3 equals g4 ? " + g3.equals(g4)); // false
        System.out.println("g4 equals g3 ? " + g4.equals(g3)); // false

        /*-----------------------------------------------------------
        -- 	           Test de la question 3.                    --
        --        A placer dans la méthode principale            --
        --             de la classe PotDeGlace                   --
        --          APRES les tests de la question 2.            --
        -----------------------------------------------------------*/

        System.out.println("\n\nTests question 3 : volume et poids des pots de glace\n");
        System.out.println("volume de g1 : " + g1.calculerVolumeEnL() + " L"); // 0.117 L
        System.out.println("volume de g2 : " + g2.calculerVolumeEnL() + " L"); // 0.197 L
        System.out.println("poids de g1 : " + g1.calculerPoidsEnG() + " g"); // 141.37 g
        System.out.println("poids de g2 : " + g2.calculerPoidsEnG() + " g"); // 237.50 g
    }
}
