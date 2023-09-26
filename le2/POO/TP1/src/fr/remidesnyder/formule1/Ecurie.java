package fr.remidesnyder.formule1;

import java.util.Arrays;

/**
 * Created by Desnyder Rémi
 * Date: 26/09/2023
 */

public class Ecurie {

    private String nom;
    private int nbPersonnes;
    private int nbVoitures;

    private Personne[] equipe;
    private Voiture[] vehicules;

    private final static int MAX_NB_PERSONNES = 5;
    private final static int MAX_NB_VOITURES = 5;

    public Ecurie(String nom) {
        this.nbVoitures = 0;
        this.nbPersonnes = 0;
        this.setNom(nom);
        this.equipe = new Personne[MAX_NB_PERSONNES];
        this.vehicules = new Voiture[MAX_NB_VOITURES];
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public int getNbVoitures() {
        return nbVoitures;
    }

    public void setNom(String nom) {
        if (nom != null) this.nom = nom;
    }

    @Override
    public String toString() {
        return "Ecurie{" +
                "nom='" + nom + '\'' +
                ", nbPersonnes=" + nbPersonnes +
                ", nbVoitures=" + nbVoitures +
                ", equipe=" + Arrays.toString(equipe) +
                ", véhicules=" + Arrays.toString(vehicules) +
                '}';
    }

    public boolean estPresent(Personne personne) {
        if (personne != null) {

            for (int i = 0; i < nbPersonnes; i++) {
                if (this.equipe[i].getNumeroIdentification() == personne.getNumeroIdentification()) return true;
            }
            return false;

        }
        return false;
    }

    public boolean estPresent(Voiture voiture) {
        if (voiture != null) {

            for (int i = 0; i < nbVoitures; i++) {
                if (this.vehicules[i].getNumeroImmatriculation() == voiture.getNumeroImmatriculation()) return true;
            }
            return false;

        }
        return false;
    }

    public boolean embaucher(Personne personne) {
        if ( !this.estPresent(personne) && this.nbPersonnes < MAX_NB_PERSONNES) {
            // Si la personne n'est pas déjà dans l'équipe et que l'équipe n'a pas atteint le nombre max de personnes
            this.equipe[this.nbPersonnes] = personne;
            this.nbPersonnes++;
            return true;
        }
        return false;
    }

    public boolean acheter(Voiture voiture) {
        if ( !this.estPresent(voiture) && this.nbVoitures < MAX_NB_VOITURES) {
            this.vehicules[this.nbVoitures] = voiture;
            this.nbVoitures++;
            return true;
        }
        return false;
    }

    private Personne localiserPersonne(int id) {
        for (int i = 0; i< nbPersonnes; i++) {
            if( this.equipe[i].getNumeroIdentification() == id) return this.equipe[i];
        }
        return null;
    }

    private Voiture localiserVoiture(int immat) {
        for (int i = 0; i < nbVoitures; i++) {
            if( this.vehicules[i].getNumeroImmatriculation() == immat) return this.vehicules[i];
        }
        return null;
    }

    public boolean affecter(int id, int immat) {
        Personne personne = localiserPersonne(id);
        if (personne != null) {
            personne.affecterVoiture(localiserVoiture(immat));
        }
        return false;
    }

    public boolean restituer(int id) {
        Personne personne = localiserPersonne(id);
        if (personne != null) {
            personne.restituerVoiture();
        }
        return false;
    }


    public static void main(String[] args) {
        Ecurie ecurie1 = new Ecurie("Ferrari");
        Ecurie ecurie2 = new Ecurie("Alpine");
        Ecurie ecurie3 = new Ecurie(null);

        System.out.println(ecurie1.toString());
        System.out.println(ecurie2.toString());
        System.out.println(ecurie3.toString());

        Pilote pilote1 = new Pilote("Gasly", "Pierre", "...", 10, 0);
        Pilote pilote2 = new Pilote("Leclerc", "Charles", "...", 10, 0);

        ecurie1.embaucher(pilote2);
        System.out.println(ecurie1.toString());
        // Test 1
        ecurie1.embaucher(pilote2);
        System.out.println(ecurie1.toString());

        ecurie1.embaucher(pilote1);

        Formule1 f1 = new Formule1("Ferrari");
        Formule1 f1b = new Formule1("Alpine");

        ecurie1.acheter(f1);
        System.out.println(ecurie1.toString());
        // Test 2
        ecurie1.acheter(f1);
        System.out.println(ecurie1.toString());

        ecurie1.acheter(f1b);

        ecurie1.affecter(pilote2.getNumeroIdentification(), f1.getNumeroImmatriculation());
        System.out.println(ecurie1.toString());

        // Test
        ecurie1.affecter(pilote1.getNumeroIdentification(), f1b.getNumeroImmatriculation());
        System.out.println(ecurie1.toString());

        Technicien t1 = new Technicien("Jean", "Roger", "...", "Ingenieur");
        Technicien t2 = new Technicien("Chabriet", "Pierre", "...", "Casseur de voiture");
        Technicien t3 = new Technicien("Levy", "Sylvain", "...", "Prendre des dos d'ane à 150");
        Technicien t4 = new Technicien("Chabroud", "Maxime", "...", "Amixem Fou");

        Formule1 f1test = new Formule1("NordVPN");

        Camion camion1 = new Camion("Mack", 100);
        Camion camion2 = new Camion("Mack2", 300);
        Camion camion3 = new Camion("Mack3", 2300);

        ecurie1.embaucher(t1);
        ecurie1.embaucher(t2);
        ecurie1.embaucher(t3);
        ecurie1.embaucher(t4);

        ecurie1.acheter(f1test);
        ecurie1.acheter(camion1);
        ecurie1.acheter(camion2);
        ecurie1.acheter(camion3);

        ecurie1.affecter(t1.getNumeroIdentification(), camion1.getNumeroImmatriculation());
        ecurie1.affecter(t2.getNumeroIdentification(), camion2.getNumeroImmatriculation());
        ecurie1.affecter(t3.getNumeroIdentification(), camion3.getNumeroImmatriculation());

        System.out.println(ecurie1.toString());
    }
}
