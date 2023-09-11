package fr.remidesnyder.formule1;

/**
 * Created by Desnyder Rémi
 * Date: 11/09/2023
 */

public class Moteur {
    // Attributs
    /**
     * Le carburant doit être ESSENCE ou DIESEL
     */
    private String carburant;
    /**
     * La puissance doit être supérieure ou égale à 600 ch
     */
    private int puissance;

    // Constantes
    private static final String ESSENCE = "ESSENCE";
    private static final String DIESEL = "DIESEL";
    private static final int PUISSANCE_MIN = 600;

    /*
     final : non modifiable
     static : variable globale
    */

    /**
     * Constructeur par défault (sans paramétres)
     */
    public Moteur() {
        // this désign l'instance courante de la classe sur laquelle on travaille
        this.carburant = ESSENCE;
        this.puissance = PUISSANCE_MIN;
    }

    /**
     * construceur par données
     * @param carburant doit être ESSENCE ou DIESEL
     * @param puissance en chevaux, supérieur a 600
     */
    public Moteur(String carburant, int puissance) {
        // Appel au constructeur par défault
        this(); // Ne peut être écrit que dans la premiere ligne d'un constructeur
        // Modification des attributs
        /*if (!Objects.equals(carburant, ESSENCE) && !Objects.equals(carburant, DIESEL)) carburant = ESSENCE;
        if (puissance < PUISSANCE_MIN) puissance = PUISSANCE_MIN;
        this.carburant = carburant;
        this.puissance = puissance;*/
        if(carburant.equals(DIESEL)) this.carburant = carburant;
        if(puissance > PUISSANCE_MIN) this.puissance = puissance;
    }

    public Moteur(Moteur moteur) {
        this();
        if (moteur != null) {
            this.puissance = moteur.puissance;
            this.carburant = moteur.carburant;
        }

    }

    // Méthode

    public String getCarburant() {
        return carburant;
    }

    public int getPuissance() {
        return puissance;
    }

    /**
     * Modifie la puissance du moteur
     * Pas de modification si la puissance est inferieur à 600 ch
     * @param puissance
     */
    public void setPuissance(int puissance) {
        if (puissance >= PUISSANCE_MIN) this.puissance = puissance;
    }

    @Override
    public String toString() {
        return "Moteur{" +
                "carburant='" + carburant + '\'' +
                ", puissance=" + puissance + " ch" +
                '}';
    }

    // Methode principale pour tester la classe
}
