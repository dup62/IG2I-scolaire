package fr.remidesnyder.formule1;

/**
 * Created by Desnyder Rémi
 * Date: 11/09/2023
 */

public abstract class Voiture {

    private int numeroImmatriculation;
    private String marque;
    private Moteur moteur;

    private static int derniereImmatriculation = 0;

    private Personne conducteur;

    public Voiture() {
        this.numeroImmatriculation = derniereImmatriculation;
        derniereImmatriculation++;
        this.marque = "???";
        this.moteur = new Moteur();
        this.conducteur = null;
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

    public int getPuissanceMoteur(){
        return this.moteur.getPuissance();
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
        return(this.conducteur == null);
    }

    public boolean affecterConducteur(Personne conducteur) {
        if(conducteur == null || !this.estDisponible() || !conducteur.estCompatible(this)) return false;

        if(conducteur.getVoitureImmatriculation() != this.numeroImmatriculation) return false;

        this.conducteur = conducteur;
        return true;
    }

    public void rendreDisponible() {
        this.conducteur = null;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "numeroImmatriculation='" + numeroImmatriculation + '\'' +
                ", marque='" + marque + '\'' +
                ", moteur=" + moteur +
                '}';
    }
}
