package fr.remidesnyder.masquesconfettis.ordonnancement;

import fr.remidesnyder.masquesconfettis.atelier.Atelier;
import fr.remidesnyder.masquesconfettis.atelier.Tache;

import java.util.ArrayList;
import java.util.List;

public interface Ordonnancement {

    public Atelier ordonnancer(int nombreMachines, List<Tache> taches);

    /**
     * Copie en profondeur la liste de tâches passée en paramètre.
     * @param taches
     * @return une nouvelle liste de tâches
     */
    public static List<Tache> copierTaches(List<Tache> taches) {
        List<Tache> tachesCopiees = new ArrayList<>();
        for (Tache tache : taches) {
            tachesCopiees.add(new Tache(tache));
        }
        return tachesCopiees;
    }

}
