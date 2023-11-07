package fr.remidesnyder.livraisonBieres.reseau;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Desnyder Rémi
 * Date: 07/11/2023
 */

public class Depot extends Point {
    public Depot(double abscisse, double ordonnee) {
        super(abscisse, ordonnee);
    }

    @Override
    public String toString() {
        return "Depot{" +
                //"idClient=" + this.getIdClient() +
                //", abscisse=" + this.getAbscisse() +
                //", ordonnee=" + this.getOrdonnee() +
                super.toString() +
                ", nbroute=" + this.getNbRoutes() +
                "}";
    }

    public static void main(String[] args) {
        Depot d = new Depot(0,0);

        Client c1 = new Client(5,5,10);
        Client c2 = new Client(-5,5,10);
        Client c3 = new Client(-5,-5,10);
        Client c4 = new Client(5,-5,10);

        Set<Point> mesClients = new HashSet<>();
        mesClients.add(c1);
        mesClients.add(c2);
        mesClients.add(c3);
        mesClients.add(c4);

        d.ajouterRoutes(mesClients);
        System.out.println(d);
    }
}
