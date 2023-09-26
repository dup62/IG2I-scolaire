package fr.remidesnyder.formule1;

/**
 * Created by Desnyder Rémi
 * Date: 26/09/2023
 */

public class Pilote extends Personne {

    private int prixWin;
    private int nbAbandons;

    public Pilote() {
        super();
        this.prixWin = 0;
        this.nbAbandons = 0;
    }

    public Pilote(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
        this.prixWin = 0;
        this.nbAbandons = 0;
    }

    public Pilote(String nom, String prenom, String adresse, int prixWin, int nbAbandons) {
        super(nom, prenom, adresse);
        this.setPrixWin(prixWin);
        this.setNbAbandons(nbAbandons);
    }

    public int getPrixWin() {
        return prixWin;
    }
    public int getNbAbandons() {
        return nbAbandons;
    }

    public void setPrixWin(int prixWin) {
        if (prixWin >= 0) this.prixWin = prixWin;
        else this.prixWin = 0;
    }
    public void setNbAbandons(int nbAbandons) {
        if (nbAbandons >= 0) this.nbAbandons = nbAbandons;
        else this.nbAbandons = 0;
    }

    public void gagnerUnPrix() {
        this.prixWin++;
    }
    public void abadonner() {
        this.nbAbandons++;
    }

    public void restistuerUnPrix() {
        if (this.prixWin > 0) this.prixWin--;
    }

    @Override
    public String toString() {
        return "Pilote{" +
                super.toString() + '\'' +
                "prixWin=" + prixWin +
                ", nbAbandons=" + nbAbandons +
                '}';
    }

    @Override
    public boolean estCompatible(Voiture voiture) {
        return voiture instanceof Formule1;
    }

    public static void main(String[] args) {
        Pilote p1 = new Pilote("Pierre", "Gasly", "...", 10, 0);
        System.out.println(p1.toString());

        p1.gagnerUnPrix();
        System.out.println(p1.getPrixWin());

        Camion cam1 = new Camion(1);
        Formule1 formule1 = new Formule1("Ferrari");

        System.out.println(p1.toString());

        p1.affecterVoiture(cam1);
        System.out.println(p1.toString());

        p1.affecterVoiture(formule1);
        System.out.println(p1.toString());
    }
}
