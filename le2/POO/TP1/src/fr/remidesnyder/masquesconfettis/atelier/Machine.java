package fr.remidesnyder.masquesconfettis.atelier;

/**
 * Created by Desnyder Rémi
 * Date: 10/10/2023
 */

/**
 * Classe abstraite -> Une classe qu'on ne peut pas instancier
 *                  = On ne peut pas créer de nouveaux objets en appelant new ....
 *
 * Methode abstraite -> definie (sa signature) dans une classe abstraite
 *                   = On ne donne que la signature de la méthode
 *                   = DOIT être implémentée (donner un corps) dans toutes les classes (non abstraites)
 *                      qui héritent de la classe abstraite
 *
 * Interface -> "Comme" une classe abstraite qui contient seulement :
 *                         - des constantes
 *                         - des méthodes abstraites
 */

import java.util.LinkedList;
import java.util.List;

public class Machine {

    private final int identifiantMachine;
    /**
     * @param dateDisponibilite Date de disponibilité de la machine
     */
    private int dateDisponibilite;
    /**
     * @param penaliteTotale Pénalité totale de la machine
     */
    private double penaliteTotale;
    /**
     * @param listeTaches Liste des tâches de la machine
     */
    private List<Tache> listeTaches;

    private static int dernierId = 0;

    public Machine() {
        this.identifiantMachine = dernierId;
        dernierId++;
        this.listeTaches = new LinkedList<>();
        this.dateDisponibilite = 0;
        this.penaliteTotale = 0.0;
    }

    public int getDateDisponibilite() {
        return dateDisponibilite;
    }

    public double getPenaliteTotale() {
        return penaliteTotale;
    }

    @Override
    public String toString() {
        String newLine = System.getProperty("line.separator");
        return newLine + "Machine{" +
                "identifiantMachine=" + identifiantMachine +
                ", dateDisponibilite=" + dateDisponibilite +
                ", penaliteTotale=" + penaliteTotale +
                ", listeTaches=" + listeTaches +
                "}";
    }

    public boolean addTache(Tache tache) {

        if (tache != null && tache.peutEtreAffectee()) {
            tache.setDateDebut(this.dateDisponibilite);
            this.dateDisponibilite += tache.getTempsProduction();
            this.penaliteTotale += tache.coutPenalite();
            return this.listeTaches.add(tache);
        }
        return false;
    }

    public static void main(String[] args) {
        Tache t1 = new Tache(150, 300, 2.5);
        Tache t2 = new Tache(140, 400, 1.5);
        Tache t3 = new Tache(50, 200, 2.5);
        Tache t4 = new Tache(85, 200, 1.0);
        Tache t5 = new Tache(75, 160, 0.5);
        Tache t6 = new Tache(80, 500, 1.5);

        Machine m1 = new Machine();
        m1.addTache(t1);
        m1.addTache(t2);
        m1.addTache(t3);
        m1.addTache(t4);
        m1.addTache(t5);
        m1.addTache(t6);

        System.out.println(m1.toString());
    }
}
