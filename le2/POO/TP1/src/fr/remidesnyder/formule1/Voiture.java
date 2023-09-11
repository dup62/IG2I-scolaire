package fr.remidesnyder.formule1;

/**
 * Created by Desnyder Rémi
 * Date: 11/09/2023
 */

public class Voiture {

    private int numeroImmatriculation;
    private String marque;
    private Moteur moteur;

    private static int derniereImmatriculation = 0;

    private int conducteur;

    public Voiture() {
        this.numeroImmatriculation = derniereImmatriculation;
        derniereImmatriculation++;
        this.marque = "???";
        this.moteur = new Moteur();
        this.conducteur = -1;
    }

    public Voiture(String marque) {
        this();
        if (marque != null) this.marque = marque;
    }

    public Voiture(String marque, int puissance, String carburant) {
        this(marque);
        this.moteur = new Moteur(carburant, puissance);
    }

    public Voiture(String marque, Moteur moteur) {
        this(marque);
        this.moteur = new Moteur(moteur);
    }

    public int getNumeroImmatriculation() {
        return numeroImmatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public Moteur getCopieMoteur() {
        //return moteur; // On retourne la référence de l'adresse mémoire
        return new Moteur(this.moteur);
    }

    public void setMoteur(Moteur moteur) {
        if (moteur != null) this.moteur = new Moteur(moteur);
    }

    public void setMoteur(String carburant, int puissance) {
        this.moteur = new Moteur(carburant, puissance);
    }

    public void setPuissanceMoteur(int puissance) {
        this.moteur.setPuissance(puissance);
    }

    public boolean estDisponible() {
        return this.conducteur == -1;
    }

    public void affecterPersonne(int idPersonne) {
        this.conducteur = idPersonne;
    }

    public void rendreDisponible() {
        this.conducteur = -1;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "numeroImmatriculation='" + numeroImmatriculation + '\'' +
                ", marque='" + marque + '\'' +
                ", moteur=" + moteur +
                ", estDisponible=" + estDisponible() +
                ", Personne=" + conducteur +
                '}';
    }
}
