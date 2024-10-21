package tests;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Map;
import modele.Medecin;
import modele.Service;
import requete.RequeteHopital;

/**
 * Created by Desnyder Rémi Date: 21/10/2024
 */

public class Test5 {

  public static void main(String[] args) {

    RequeteHopital requete = new RequeteHopital();

    System.out.println("\nListe des services :");
    List<Service> services = requete.getServices();
    for (Service service : services) {
      System.out.println(service);
    }

    System.out.println("\nListe des médecins chefs :");
    List<Medecin> medecins = requete.getMedecinsChef();
    for (Medecin medecin : medecins) {
      System.out.println(medecin);
    }

    System.out.println("\nListe des médecins avec un salaire supérieur à 2800 :");
    List<Medecin> medecins2 = requete.getMedecinsWithSalaryGreaterThan(2800);
    for (Medecin medecin : medecins2) {
      System.out.println(medecin);
    }

    System.out.println("\nSalaires moyens par service :");
    Map<String, Double> salaires = requete.getSalairesMoyenParService();
    for (Map.Entry<String, Double> entry : salaires.entrySet()) {
      System.out.println(entry.getKey() + " : " + entry.getValue());
    }

    System.out.println("\nListe des médecins d'un service (Cardiologie) :");
    List<Medecin> medecins3 = requete.getMedecinsByService("Cardiologie");
    for (Medecin medecin : medecins3) {
      System.out.println(medecin);
    }

    System.out.println("\nListe des médecins dont le chef est Coralie Gator :");
    List<Medecin> medecins4 = requete.getMedecinByChef("Gator", "Coralie");
    for (Medecin medecin : medecins4) {
      System.out.println(medecin);
    }

    System.out.println("\nAugmenter de 5% le salaire des personnes dont le service est Cardiologie :");
    requete.increaseMedecinSalaryByService("Cardiologie", 50);

    System.out.println("\nListe des médecins :");
    List<Medecin> medecins5 = requete.getMedecins();
    for (Medecin medecin : medecins5) {
      System.out.println(medecin);
    }

    System.out.println("\nMettre le salaire de tous les médecins à 2000 EUR :");
    requete.setMedecinSalary(2000);

    System.out.println("\nListe des médecins :");
    List<Medecin> medecins6 = requete.getMedecins();
    for (Medecin medecin : medecins6) {
      System.out.println(medecin);
    }


  }

}
