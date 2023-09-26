package fr.remidesnyder.formule1;

/**
 * Created by Desnyder Rémi
 * Date: 11/09/2023
 */

public abstract class Personne {

    private int numeroIdentification;
    private String nom;
    private String prenom;
    private String adresse;
    private static int dernierePersonne = 0;
    private Voiture voiture;

    public Personne() {
        this.numeroIdentification = dernierePersonne;
        dernierePersonne++;
        this.nom = "???";
        this.prenom = "???";
        this.adresse = "???";
        this.voiture = null;
    }

    public Personne(String nom, String prenom) {
        this();
        if (nom != null) this.nom = nom;
        if (prenom != null) this.prenom = prenom;
    }

    public Personne(String nom, String prenom, String adresse) {
        this(nom, prenom);
        if (adresse != null) this.adresse = adresse;
    }

    public int getNumeroIdentification() {
        return numeroIdentification;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public boolean estPieton() {
        return (this.voiture == null);
    }

    private void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public boolean affecterVoiture(Voiture voiture) {
        if (voiture == null || !this.estPieton() || !voiture.estDisponible() || !this.estCompatible(voiture)) return false;

        this.setVoiture(voiture);

        if (!voiture.affecterConducteur(this)) {
            this.affecterVoiture(null);
            return false;
        }

        return true;
    }

    public boolean restituerVoiture() {
        if (this.voiture == null || this.voiture.getNumeroImmatriculation() == -1 || estPieton()) return false;
        this.voiture.rendreDisponible();
        this.voiture = null;
        return true;
    }

    public int getVoitureImmatriculation() {
        if (this.voiture == null) return -1;
        return this.voiture.getNumeroImmatriculation();
    }

    public abstract boolean estCompatible(Voiture v);

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + numeroIdentification +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", Voiture=" + voiture +
                '}';
    }
}