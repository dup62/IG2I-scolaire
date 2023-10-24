package fr.remidesnyder.masquesconfettis.ordonnancement;

import fr.remidesnyder.masquesconfettis.atelier.Atelier;
import fr.remidesnyder.masquesconfettis.atelier.Tache;

import java.util.List;

/**
 * Created by Desnyder Rémi
 * Date: 24/10/2023
 */

public class OrdonnancementTempsDecroissant implements Ordonnancement{

    // qui trie les tâches par temps
    // d’exécution croissant avant de faire l’affectation aux machines selon le
    // modus operandi de Monsieur Sorcier ;
    @Override
    public Atelier ordonnancer(int nombreMachines, List<Tache> taches) {

        Atelier atelier = new Atelier(Math.max(nombreMachines, 1));
        List<Tache> tachesCopiees = Ordonnancement.copierTaches(taches);

        // Ordre décroissant
        tachesCopiees.sort((tache1, tache2) -> tache2.getTempsProduction() - tache1.getTempsProduction());

        atelier.ordonnancerTaches(tachesCopiees);

        return atelier;
    }
}
