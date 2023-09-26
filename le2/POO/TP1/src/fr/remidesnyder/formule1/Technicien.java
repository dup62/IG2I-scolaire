package fr.remidesnyder.formule1;

/**
 * Created by Desnyder Rémi
 * Date: 26/09/2023
 */

public class Technicien extends Personne {

    private String specialite;

    public Technicien() {
        super();
        this.specialite = "???";
    }

    public Technicien(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
        this.specialite = "???";
    }

    public Technicien(String nom, String prenom, String adresse, String specialite) {
        super(nom, prenom, adresse);
        if (specialite != null)
            this.specialite = specialite;
        else
            this.specialite = "???";
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        if (specialite != null) this.specialite = specialite;
        else this.specialite = "???";
    }

    @Override
    public String toString() {
        return "Technicien{" +
                super.toString() + '\'' +
                "specialite='" + specialite + '\'' +
                '}';
    }

    @Override
    public boolean estCompatible(Voiture voiture) {
        return voiture instanceof Camion;
    }

    public static void main(String[] args) {
        Technicien t1 = new Technicien("Jean", "Roger", "...");
        System.out.println(t1.toString());
        t1.setSpecialite("Ingénieur");
        System.out.println(t1.toString());

        t1.setSpecialite(null);
        System.out.println(t1.toString());

        Camion cam1 = new Camion(1);
        Formule1 formule1 = new Formule1("Ferrari");

        System.out.println(t1.toString());

        t1.affecterVoiture(formule1);
        System.out.println(t1.toString());

        t1.affecterVoiture(cam1);
        System.out.println(t1.toString());
    }
}
