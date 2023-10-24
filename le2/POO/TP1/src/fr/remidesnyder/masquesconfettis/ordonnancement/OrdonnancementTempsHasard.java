package fr.remidesnyder.masquesconfettis.ordonnancement;

import fr.remidesnyder.masquesconfettis.atelier.Atelier;
import fr.remidesnyder.masquesconfettis.atelier.Tache;

import java.util.Collections;
import java.util.List;

/**
 * Created by Desnyder Rémi
 * Date: 24/10/2023
 */

public class OrdonnancementTempsHasard implements Ordonnancement {

    // qui trie les tâches par temps
    // d’exécution au hasard avant de faire l’affectation aux machines selon le
    @Override
    public Atelier ordonnancer(int nombreMachines, List<Tache> taches) {

        Atelier atelier = new Atelier(Math.max(nombreMachines, 1));
        List<Tache> tachesCopiees = Ordonnancement.copierTaches(taches);

        // Ordre au hasard (random)
        Collections.shuffle(tachesCopiees);

        atelier.ordonnancerTaches(tachesCopiees);

        return atelier;
    }

}
