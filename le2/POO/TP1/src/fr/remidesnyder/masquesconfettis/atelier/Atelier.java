package fr.remidesnyder.masquesconfettis.atelier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desnyder Rémi
 * Date: 10/10/2023
 */

public class Atelier {

    /**
     * @param tempsTotalExecution Temps total d'exécution de l'atelier
     */
    private int tempsTotalExecution;
    /**
     * @param penaliteTotale Pénalité totale de l'atelier
     */
    private double penaliteTotale;

    /**
     * @param listeMachines Liste des machines de l'atelier
     */
    private List<Machine> listeMachines;

    public Atelier(int nbMachine) {
        this.tempsTotalExecution = 0;
        this.penaliteTotale = 0.0;

        nbMachine = Math.max(nbMachine, 1);
        this.listeMachines = new ArrayList<Machine>();

        for(int i = 0; i < nbMachine; i++) {
            this.listeMachines.add(new Machine());
        }
    }

    public int getTempsTotalExecution() {
        return tempsTotalExecution;
    }

    public double getPenaliteTotale() {
        return penaliteTotale;
    }

    @Override
    public String toString() {
        return "Atelier{" +
                "tempsTotalExecution=" + tempsTotalExecution +
                ", penaliteTotale=" + penaliteTotale +
                ", listeMachines=" + listeMachines +
                '}';
    }

    /**
     * Q11
     * Met à jour les critères d'ordonnancement
     */
    public void miseAJourCriteres() {
        this.tempsTotalExecution = 0;
        this.penaliteTotale = 0.0;

        for(Machine machine : this.listeMachines) {

            if (machine.getDateDisponibilite() > this.tempsTotalExecution)
                this.tempsTotalExecution = machine.getDateDisponibilite();

            this.penaliteTotale += machine.getPenaliteTotale();
        }

    }

    /**
     * Q12
     */
    /**
     * Retourne la machine à la position posMachine
     * @param posMachine Position de la machine
     * @return La machine à la position posMachine
     */
    private Machine getMachine(int posMachine){
        if (posMachine >= 0 && posMachine < this.listeMachines.size())
            return this.listeMachines.get(posMachine);
        else
            return null;
    }

    /**
     * Ajoute une tâche à la machine à la position posMachine
     * @param t Tâche à ajouter
     * @param posMachine Position de la machine
     * @return true si la tâche a été ajoutée, false sinon
     */
    private boolean addTache(Tache t, int posMachine) {
        Machine machine = this.getMachine(posMachine);
        if (machine != null) {
            return machine.addTache(t);
        }
        return false;
    }

    /**
     * Retourne la première machine libre
     * @return La première machine libre
     */
    private int getPremiereMachineLibre() {
        int posMachine = 0;
        int tempsDispoPremiereMachine = Integer.MAX_VALUE;
        for (int i = 0; i < this.listeMachines.size(); i++) {
            if (this.listeMachines.get(i).getDateDisponibilite() < tempsDispoPremiereMachine) {
                tempsDispoPremiereMachine = this.listeMachines.get(i).getDateDisponibilite();
                posMachine = i;
            }
        }
        return posMachine;
    }

    /**
     * Ordonnance les tâches passées en paramètre
     * @param taches
     */
    public void ordonnancerTaches(List<Tache> taches) {
        this.addTache(taches.get(0), 0);
        this.miseAJourCriteres();
        for (int i = 1; i < taches.size(); i++) {
            int posMachine = this.getPremiereMachineLibre();
            this.addTache(taches.get(i), posMachine);
            this.miseAJourCriteres();
        }
    }

    public static void main(String[] args) {
        Tache t1 = new Tache(150, 300, 2.5);
        Tache t2 = new Tache(140, 400, 1.5);
        Tache t3 = new Tache(50, 200, 2.5);
        Tache t4 = new Tache(85, 200, 1.0);
        Tache t5 = new Tache(75, 160, 0.5);
        Tache t6 = new Tache(80, 500, 1.5);

        Atelier atelier = new Atelier(3);

        List<Tache> taches = new ArrayList<Tache>();
        taches.add(t1);
        taches.add(t2);
        taches.add(t3);
        taches.add(t4);
        taches.add(t5);
        taches.add(t6);

        atelier.ordonnancerTaches(taches);

        System.out.println(atelier);
    }
}
