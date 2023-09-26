package fr.remidesnyder.formule1;

/**
 * Created by Desnyder Rémi
 * Date: 26/09/2023
 */

public class Camion extends Voiture {

    private int tonnage;

    public Camion() {
        super();
        this.setTonnage(0);
    }

    public Camion(int tonnage) {
        super();
        this.setTonnage(tonnage);
    }

    public Camion(String marque, int tonnage) {
        super(marque);
        this.setTonnage(tonnage);
    }

    public Camion(String marque, int puissance, String carburant, int tonnage) {
        super(marque, puissance, carburant);
        this.setTonnage(tonnage);
    }

    public int getTonnage() {
        return tonnage;
    }

    private void setTonnage(int tonnage) {
        if (tonnage >= 0) this.tonnage = tonnage;
    }

    @Override
    public String toString() {
        return "Camion{" +
                super.toString() + '\'' +
                "tonnage=" + tonnage +
                '}';
    }

    public static void main(String[] args) {
        Camion mack = new Camion("Mack", 100);
        System.out.println(mack.toString());
    }
}
