package fr.remidesnyder.livraisonBieres.planning;

import fr.remidesnyder.livraisonBieres.reseau.Client;
import fr.remidesnyder.livraisonBieres.reseau.Depot;
import fr.remidesnyder.livraisonBieres.reseau.Point;

import java.util.*;

/**
 * Created by Desnyder Rémi
 * Date: 21/11/2023
 */

public class Tournee {

    private Depot pointDeDepart;
    private int capaciteCamion;
    private int nbCaisseLivree;
    private double distanceParcourue;
    private LinkedList<Client> listeClients;

    public Tournee(Depot pointDeDepart, int capaciteCamion) {
        this.pointDeDepart = pointDeDepart;

        this.capaciteCamion = Math.max(capaciteCamion, 0);

        this.listeClients = new LinkedList<>();

        this.nbCaisseLivree = 0;
        this.distanceParcourue = 0;

    }

    public Depot getPointDeDepart() {
        return pointDeDepart;
    }

    public void setPointDeDepart(Depot pointDeDepart) {
        this.pointDeDepart = pointDeDepart;
    }

    public int getCapaciteCamion() {
        return capaciteCamion;
    }

    public void setCapaciteCamion(int capaciteCamion) {
        this.capaciteCamion = capaciteCamion;
    }

    public LinkedList<Client> getListeClients() {
        return listeClients;
    }

    public int getNbCaisseLivree() {
        return nbCaisseLivree;
    }

    public double getDistanceParcourue() {
        return distanceParcourue;
    }

    @Override
    public String toString() {
        return "Tournee{" +
                "pointDeDepart=" + pointDeDepart +
                ", capaciteCamion=" + capaciteCamion +
                ", nbCaisseLivree=" + nbCaisseLivree +
                ", distanceParcourue=" + distanceParcourue +
                ", listeClients=" + listeClients +
                '}';
    }

    private void majTournee(Client nouveauClient) {

        if (!this.listeClients.isEmpty()) {
            Client dernierClient = this.listeClients.getLast();
            this.distanceParcourue -= dernierClient.getDistance(this.pointDeDepart); // on retire la distance entre le depot et le dernier client
            this.distanceParcourue += dernierClient.getDistance(nouveauClient);    // on ajoute la distance entre le dernier client et le nouveau client.
            this.distanceParcourue += nouveauClient.getDistance(this.pointDeDepart);      // et enfin on ajoute la distance entre le point de départ et le nouveau client.
        } else {
            // Premier client valide => la distance parcourue est l'aller retour entre le client et le dépot.
            this.distanceParcourue = 2 * nouveauClient.getDistance(this.pointDeDepart);
        }

        this.nbCaisseLivree += nouveauClient.getNbCaisseDeBiereALivrer();
        this.listeClients.add(nouveauClient);
    }


    public boolean ajouterClient(Client client) {

        if (client != null && (this.nbCaisseLivree + client.getNbCaisseDeBiereALivrer() <= this.capaciteCamion)) {
            /*this.listeClients.add(client);
            this.nbCaisseLivree += client.getNbCaisseDeBiereALivrer();
            this.distanceParcourue += this.pointDeDepart.getDistance(client);*/
            majTournee(client);
            return true;
        }

        return false;

    }

    public boolean ajouterClient(List<Client> clients) {

        if (clients != null) {
            for (Client client : clients) {
                if (!this.ajouterClient(client)) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        Depot d1 = new Depot(0, 0);

        try {
            Client c1 = new Client(5, 5, 10);
            Client c2 = new Client(-5, 5, 10);
            Client c3 = new Client(-5, -5, 10);
            Client c4 = new Client(5, -5, 10);
            Set<Point> ensPoint = new HashSet<>();
            ensPoint.add(c1);
            ensPoint.add(c2);
            ensPoint.add(c3);
            ensPoint.add(c4);
            d1.ajouterRoutes(ensPoint);
            ensPoint.add(d1);
            c1.ajouterRoutes(ensPoint);
            c2.ajouterRoutes(ensPoint);
            c3.ajouterRoutes(ensPoint);
            c4.ajouterRoutes(ensPoint);
            List<Client> clients = new ArrayList<>();
            clients.add(c1);
            clients.add(c2);
            clients.add(c3);
            clients.add(c4);
            Tournee t = new Tournee(d1, 50);
            t.ajouterClient(clients);
            System.out.println(t);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
