package fr.remidesnyder.livraisonBieres.reseau;

import java.util.Objects;

/**
 * Created by Desnyder Rémi
 * Date: 07/11/2023
 */

public class Route {

    private Point origine;
    private Point destination;
    private double distance;

    public Route(Point origine, Point destination) {
        this.origine = origine;
        this.destination = destination;

        if (origine.equals(destination)) {
            this.distance = 0;
        } else if(origine == null || destination == null) {
            this.distance = -1;
        } else {
            this.distance = Math.sqrt(Math.pow(destination.getAbscisse() - origine.getAbscisse(), 2) + Math.pow(destination.getOrdonnee() - origine.getOrdonnee(), 2));
        }

    }

    public Point getOrigine() {
        return origine;
    }

    public Point getDestination() {
        return destination;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Route{" +
                "origine=" + origine +
                ", destination=" + destination +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Double.compare(distance, route.distance) == 0 && Objects.equals(origine, route.origine) && Objects.equals(destination, route.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origine, destination, distance);
    }

    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(-5, 6);
        Route r1 = new Route(p1, p2);
        Route r2 = new Route(p2, p3);
        Route r3 = new Route(p3, p1);
        System.out.println(r1.toString());
        System.out.println(r2.toString());
        System.out.println(r3.toString());
    }

}
