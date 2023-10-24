package fr.remidesnyder.masquesconfettis.atelier;

/**
 * Created by Desnyder Rémi
 * Date: 10/10/2023
 */

import fr.remidesnyder.masquesconfettis.ordonnancement.Ordonnancement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * static -> commun à toutes les instances de la classe
 * final -> ne peut pas être modifié après initialisation
 * constante -> Static + Final (Par convention, on écrit en majuscule)
 */

public class Tache implements Comparable<Tache> {

    /**
     * @param id Identifiant de la tâche
     */
    private int id;
    /**
     * @param tempsProduction Temps de production de la tâche
     */
    private int tempsProduction;
    /**
     * @param dateDebut Date de début de la tâche
     */
    private int dateDebut;
    /**
     * @param dateLimite Date limite de la tâche
     */
    private int dateLimite;
    /**
     * @param penaliteUnitaire Pénalité unitaire de la tâche
     */
    private double penaliteUnitaire;
    /**
     * @param dernierId Dernier identifiant de tâche
     */
    private static int dernierId = 0;

    /**
     * @param TEMPS_PRODUCTION_DEFAUT Temps de production par défaut
     */
    private static final int TEMPS_PRODUCTION_DEFAUT = 50;
    /**
     * @param DATE_LIMITE_DEFAUT Date debut par défaut
     */
    private static final int DATE_DEBUT_DEFAUT = -1;

    public Tache() {
        this.id = dernierId;
        dernierId++;
        this.tempsProduction = TEMPS_PRODUCTION_DEFAUT;
        this.dateDebut = DATE_DEBUT_DEFAUT;
        this.dateLimite = Integer.MAX_VALUE;
        this.penaliteUnitaire = 0;
    }

    public Tache(int tempsProduction) {
        this();
        if (tempsProduction >= TEMPS_PRODUCTION_DEFAUT) this.tempsProduction = tempsProduction;
    }

    public Tache(int tempsProduction, int dateLimite, double penaliteUnitaire) {
        this(tempsProduction);
        //if (dateLimite >= 2 * this.tempsProduction) this.dateLimite = dateLimite;
        //else this.dateLimite = 2 * this.tempsProduction; // Pas demandé par le sujet mais donne une certaine cohérence
        this.dateLimite = Math.max(dateLimite, 2 * this.tempsProduction);
        if( penaliteUnitaire >= 0) this.penaliteUnitaire = penaliteUnitaire;
    }

    // Constructeur par copie
    public Tache(Tache tache) {
        if (tache == null) return;
        this.id = tache.id;
        this.tempsProduction = tache.tempsProduction;
        this.dateDebut = tache.dateDebut;
        this.dateLimite = tache.dateLimite;
        this.penaliteUnitaire = tache.penaliteUnitaire;
    }

    // Getters et Setters

    public int getTempsProduction() {
        return tempsProduction;
    }

    public int getDateLimite() {
        return dateLimite;
    }

    public double getPenaliteUnitaire() {
        return penaliteUnitaire;
    }

    public int getId() {
        return id;
    }

    public int getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(int dateDebut) {
        if(dateDebut >= 0) this.dateDebut = dateDebut;
    }

    @Override
    public String toString() {
        String newLine = System.getProperty("line.separator");
        return "Tache{" +
                "id=" + id +
                ", tempsProduction=" + tempsProduction +
                ", dateDebut=" + dateDebut +
                ", dateLimite=" + dateLimite +
                ", penaliteUnitaire=" + penaliteUnitaire +
                '}' +
                newLine;
    }

    @Override
    public int compareTo(Tache o)
    {
        if ( o.tempsProduction == this.tempsProduction)   // SI 2 instances ont la même durée d'exécution ALORS on compare leur ID
            return this.id - o.id;

        return this.tempsProduction - o.tempsProduction;  // Si this > obj alors on renvoie un positif
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tempsProduction);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tache other = (Tache) obj;
        if (this.id != other.id) {
            return false;
        }
        return this.tempsProduction == other.tempsProduction;
    }

    /**
     * Cette méthode permet de calculer la date de fin d'exécution de la tâche
     * @return La date de fin d'exécution de la tâche
     */
    public int dateFinExecution() {
        return (this.dateDebut + this.tempsProduction);
    }

    /**
     * Cette méthode permet de calculer la pénalité de la tâche
     * @return La pénalité de la tâche
     */
    public double coutPenalite() {
        return (this.penaliteUnitaire * Math.max(0, this.dateFinExecution() - this.dateLimite));
    }

    /**
     * Cette méthode permet de savoir si la tâche peut être affectée à une machine
     * @return Vrai si la tâche peut être affectée à une machine, faux sinon
     */
    public boolean peutEtreAffectee() {
        return this.dateDebut == DATE_DEBUT_DEFAUT;
    }

    public static void main(String[] args) {
        Tache t1 = new Tache(150, 300, 2.5);
        Tache t2 = new Tache(140, 400, 1.5);
        Tache t3 = new Tache(50, 200, 2.5);
        Tache t4 = new Tache(85, 200, 1.0);
        Tache t5 = new Tache(75, 160, 0.5);
        Tache t6 = new Tache(80, 500, 1.5);

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);
        System.out.println(t5);
        System.out.println(t6);

        // TP4  -> Test Copie en profondeur

        List<Tache> taches = new ArrayList<>();
        taches.add(new Tache(100));
        taches.add(new Tache(150));
        List<Tache> copieTaches = Ordonnancement.copierTaches(taches);
        copieTaches.add(new Tache(400));
        System.out.println("Nombre de taches initiales : " + taches.size());
        System.out.println("Nombre de taches finales : " + copieTaches.size());
    }

}
