package fr.remidesnyder.livraisonBieres.planning;

import fr.remidesnyder.livraisonBieres.exceptions.ExceptionQuantite;
import fr.remidesnyder.livraisonBieres.reseau.Client;
import fr.remidesnyder.livraisonBieres.reseau.Depot;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Desnyder Rémi
 * Date: 21/11/2023
 */

public class Planning {

    private Set<Tournee> ensembleTournee;
    private double distanceTotaleParcourue;
    private int nombreTotalDeCaissesLivrees;
    private int capaciteDesCamions;

    public Planning(int capaciteDesCamions) {

        this.capaciteDesCamions = Math.max(capaciteDesCamions, 0);

        this.nombreTotalDeCaissesLivrees = 0;
        this.distanceTotaleParcourue = 0;

        this.ensembleTournee = new HashSet<Tournee>();
    }

    public Set<Tournee> getEnsembleTournee() {
        return ensembleTournee;
    }

    public double getDistanceTotaleParcourue() {
        return distanceTotaleParcourue;
    }

    public int getNombreTotalDeCaissesLivrees() {
        return nombreTotalDeCaissesLivrees;
    }

    public int getCapaciteDesCamions() {
        return capaciteDesCamions;
    }

    @Override
    public String toString() {
        return "Planning{" +
                "ensembleTournee=" + ensembleTournee +
                ", distanceTotaleParcourue=" + distanceTotaleParcourue +
                ", nombreTotalDeCaissesLivrees=" + nombreTotalDeCaissesLivrees +
                ", capaciteDesCamions=" + capaciteDesCamions +
                '}';
    }

    private boolean ajouterTournee(Tournee t) {

        if (t == null) return false;

        //if (t.getNbCaisseLivree() > this.capaciteDesCamions) return false;

        if (this.ensembleTournee.contains(t)) return false;

        this.ensembleTournee.add(t);
        this.distanceTotaleParcourue += t.getDistanceParcourue();
        this.nombreTotalDeCaissesLivrees += t.getNbCaisseLivree();

        return true;
    }

    public void planificationBasique(Depot depot, Set<Client> clients) {

        if (depot == null || clients == null) return;

        if (clients.isEmpty()) return;

        Tournee tourneeCourante = new Tournee(depot, this.capaciteDesCamions);

        for (Client client : clients) {

            if (client == null) continue;

            if (tourneeCourante.getNbCaisseLivree() + client.getNbCaisseDeBiereALivrer() > this.capaciteDesCamions) {
                this.ajouterTournee(tourneeCourante);
                tourneeCourante = new Tournee(depot, this.capaciteDesCamions);
            }

            if (!tourneeCourante.ajouterClient(client)) {
                this.ajouterTournee(tourneeCourante);
                tourneeCourante = new Tournee(depot, this.capaciteDesCamions);
            }

            tourneeCourante.ajouterClient(client);
        }

        this.ajouterTournee(tourneeCourante);

    }

    public static void main(String[] args) {

        Depot depot = new Depot(0, 0);

        try {
            Client c1 = new Client(1, 2, 3);
            Client c2 = new Client(3, 4, 5);
            Client c3 = new Client(-5, 6, 7);
            Client c4 = new Client(5, 6, 7);
            Client c5 = new Client(5, 6, 7);
            Client c6 = new Client(5, 6, 7);
            Client c7 = new Client(5, 6, 7);
            Client c8 = new Client(5, 6, 7);
            Client c9 = new Client(5, 6, 7);
            Client c10 = new Client(5, 6, 7);
            Client c11 = new Client(5, 6, 7);
            Client c12 = new Client(5, 6, 7);
            Client c13 = new Client(5, 6, 7);
            Client c14 = new Client(5, 6, 7);
            Client c15 = new Client(5, 6, 7);
            Client c16 = new Client(5, 6, 7);
            Client c17 = new Client(5, 6, 7);
            Client c18 = new Client(5, 6, 7);

            Set<Client> clients = new HashSet<>();
            clients.add(c1);
            clients.add(c2);
            clients.add(c3);
            clients.add(c4);
            clients.add(c5);
            clients.add(c6);
            clients.add(c7);
            clients.add(c8);
            clients.add(c9);
            clients.add(c10);
            clients.add(c11);
            clients.add(c12);
            clients.add(c13);
            clients.add(c14);
            clients.add(c15);
            clients.add(c16);
            clients.add(c17);
            clients.add(c18);

            Planning p = new Planning(10);
            p.planificationBasique(depot, clients);
            System.out.println(p);


        } catch (ExceptionQuantite e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}