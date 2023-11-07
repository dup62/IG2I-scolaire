package fr.remidesnyder.livraisonBieres.reseau;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

/**
 * Created by Desnyder Rémi
 * Date: 07/11/2023
 */

public class Point {

    private int idClient;
    private double abscisse;
    private double ordonnee;
    //private List<Route> mesDestinations;
    private Map<Point, Route> mesDestinations;
    private static int dernierIdClient = 0;

    public Point(double abscisse, double ordonnee) {
        this.idClient = dernierIdClient;
        dernierIdClient++;
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
        //this.mesDestinations = new ArrayList<>();
        this.mesDestinations = new HashMap<Point, Route>();
    }

    public double getAbscisse() {
        return abscisse;
    }

    public void setAbscisse(double abscisse) {
        this.abscisse = abscisse;
    }

    public double getOrdonnee() {
        return ordonnee;
    }

    public void setOrdonnee(double ordonnee) {
        this.ordonnee = ordonnee;
    }

    public int getIdClient() {
        return idClient;
    }

    /*@Override
    public String toString() {

        String toReturn = "Point{" +
                "idClient=" + idClient +
                ", abscisse=" + abscisse +
                ", ordonnee=" + ordonnee +
                ", mesDestinations=[";

        if (mesDestinations.isEmpty()) {
            toReturn += "]}";
        } else {
            toReturn += "[";
            for (Point p : mesDestinations.keySet()) {
                toReturn += "\n{idClient = " + p.getIdClient() +
                        "\nOrdonne = " + p.getOrdonnee() +
                        "\nAbscisse = " + p.getAbscisse() +
                        "\nNbroute =" + p.getNbRoutes() + "}, \n     ";
            }
            toReturn = toReturn.substring(0, toReturn.length() - 2) + "]}";
        }

        return toReturn;

    }*/
    @Override
    public String toString() {

        String sReturn = new String("Point {" + "id=" + idClient + ", abscisse=" + abscisse + ", ordonnee=" + ordonnee + "}");

        if(this.mesDestinations.isEmpty())
        {
            sReturn += "}";
            return sReturn;
        }

        sReturn += ",\n";

        for(Map.Entry<Point,Route> entry : this.mesDestinations.entrySet())
        {
            Point pDest = entry.getKey();
            Route rAsso = entry.getValue();

            sReturn += "Point destination{" + "id=" + pDest.idClient + ", abscisse=" + pDest.abscisse + ", ordonnee=" + pDest.ordonnee + "}, ";
            sReturn += "Distance de la route associée : " + rAsso.getDistance();
            sReturn += "\n";

        }
        sReturn += "}";


        return sReturn;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return idClient == point.idClient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient);
        //return 0;
    }

    public void ajouterRoutes(Set<Point> mesDestinations) {
        for (Point p : mesDestinations) {
            Route r = new Route(this, p);
            this.mesDestinations.put(p, r);
        }
    }

    public double getDistance(Point p) {
        //for(Route r : mesDestinations) {
        //    if(r.getDestination().equals(p)) {
        //        return r.getDistance();
        //    }
        //}
        //return -1;
        //return Math.sqrt(Math.pow(p.getAbscisse() - this.getAbscisse(), 2) + Math.pow(p.getOrdonnee() - this.getOrdonnee(), 2))
        if (this.mesDestinations.containsKey(p)) {
            return this.mesDestinations.get(p).getDistance();
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

    public int getNbRoutes() {
        return this.mesDestinations.size();
    }

    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(-5, 6);
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());

        Set<Point> mesDestinations = new HashSet<>();
        mesDestinations.add(p1);
        mesDestinations.add(p2);
        mesDestinations.add(p3);

        p1.ajouterRoutes(mesDestinations);

        System.out.println(p1.toString());
    }

}
