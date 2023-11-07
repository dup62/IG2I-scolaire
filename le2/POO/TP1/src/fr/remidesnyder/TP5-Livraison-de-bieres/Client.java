package fr.remidesnyder.livraisonBieres.reseau;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Desnyder Rémi
 * Date: 07/11/2023
 */

public class Client extends Point{

    private int nbCaisseDeBiereALivrer;

    public Client(double abscisse, double ordonnee, int nbCaisseDeBiereALivrer) {
        super(abscisse, ordonnee);

        if (nbCaisseDeBiereALivrer > 0) {
            this.nbCaisseDeBiereALivrer = nbCaisseDeBiereALivrer;
        } else {
            this.nbCaisseDeBiereALivrer = 0;
        }
    }

    public int getNbCaisseDeBiereALivrer() {
        return nbCaisseDeBiereALivrer;
    }

    @Override
    public String toString() {
        return "Client{" +
                super.toString() +
                //"idClient=" + this.getIdClient() +
                //", abscisse=" + this.getAbscisse() +
                //", ordonnee=" + this.getOrdonnee() +
                ", nbCaisseDeBiereALivrer=" + nbCaisseDeBiereALivrer +
                '}';
    }

    public static void main(String[] args) {
        /*Client c1 = new Client(1, 2, 3);
        Client c2 = new Client(3, 4, 5);
        Client c3 = new Client(-5, 6, 7);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);*/

        Client c1 = new Client(5,5,10);
        Client c2 = new Client(-5,5,10);
        Client c3 = new Client(-5,-5,10);
        Client c4 = new Client(5,-5,10);

        Set<Point> mesClients = new HashSet<>();
        mesClients.add(c2);
        mesClients.add(c3);
        mesClients.add(c4);
        c1.ajouterRoutes(mesClients);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
    }
}
