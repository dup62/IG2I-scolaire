package fr.remidesnyder.masquesconfettis.atelier;

/**
 * Created by Desnyder Rémi
 * Date: 10/10/2023
 */

/**
 * static -> commun à toutes les instances de la classe
 * final -> ne peut pas être modifié après initialisation
 * constante -> Static + Final (Par convention, on écrit en majuscule)
 */

public class Tache {

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
        if (dateLimite >= 2 * this.tempsProduction) this.dateLimite = dateLimite;
        if( penaliteUnitaire >= 0) this.penaliteUnitaire = penaliteUnitaire;
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
        return "Tache{" +
                "id=" + id +
                ", tempsProduction=" + tempsProduction +
                ", dateDebut=" + dateDebut +
                ", dateLimite=" + dateLimite +
                ", penaliteUnitaire=" + penaliteUnitaire +
                '}';
    }

    public static void main(String[] args) {
        Tache t1 = new Tache();
        Tache t2 = new Tache(100);
        Tache t3 = new Tache(100, 200, 0.5);

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);

        t1.setDateDebut(10);
        t2.setDateDebut(20);
        t3.setDateDebut(30);

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
    }

}
