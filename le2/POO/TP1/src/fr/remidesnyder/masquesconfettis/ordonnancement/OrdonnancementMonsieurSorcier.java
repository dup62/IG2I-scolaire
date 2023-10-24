package fr.remidesnyder.masquesconfettis.ordonnancement;

import fr.remidesnyder.masquesconfettis.atelier.Atelier;
import fr.remidesnyder.masquesconfettis.atelier.Tache;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desnyder Rémi
 * Date: 24/10/2023
 */

public class OrdonnancementMonsieurSorcier implements Ordonnancement {

    @Override
    public Atelier ordonnancer(int nombreMachines, List<Tache> taches) {

        Atelier atelier = new Atelier(Math.max(nombreMachines, 1));
        List<Tache> tachesCopiees = Ordonnancement.copierTaches(taches);

        atelier.ordonnancerTaches(tachesCopiees);

        return atelier;
    }

}

