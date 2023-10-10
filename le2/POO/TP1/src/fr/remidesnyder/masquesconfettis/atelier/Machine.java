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

/**
 * public interface Machine {
 *     (public abstract) void methodeA();
 *     (public abstract) int methodeB(double val);
 * }
 */

/**
 * Une classe peut IMPLEMENTER plusieurs interfaces
 *                      -> DOIT donner une implémentation (un corps)
 *                              de toutes les méthodes de l'interface
 */

/**
 * public class Machine extend Class1 implements InterfaceA, InterfaceB {
 *     public void methodeA() {...}
 *     public int methodeB(double val) {...}
 * }
 */

public class Machine {
}
