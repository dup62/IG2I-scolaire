package tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import modele.Adresse;
import modele.Malade;
import modele.Medecin;
import modele.Service;

/**
 * Created by Desnyder Rémi Date: 21/10/2024
 */

public class Test4 {

  public static void main(String[] args) {

    final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("HopitalPU");

    final EntityManager em = emf.createEntityManager();

    try {
      final EntityTransaction et = em.getTransaction();

      try {

        Service serv1 = new Service("Cardiologie", "Bat A, 1er étage");
        Service serv2 = new Service("Pneumologie", "Bat B, 1er étage");
        Service serv3 = new Service("Urgence", "Bat C, 1er étage");

        Medecin med1 = new Medecin("Trancen", "Jean", 2135.23);
        Medecin med2 = new Medecin("Gator", "Coralie", 3156.00);
        Medecin med3 = new Medecin("Gator", "Magalie", 2545.37);
        Medecin med4 = new Medecin("Hitmieu", "Helmer", 1873.30);
        Medecin med5 = new Medecin("Cambronne", "Maude", 3765.20);
        Medecin med6 = new Medecin("Haybon", "Sylvain", 2980.00);
        Medecin med7 = new Medecin("Trancen", "Jean", 21352.23);

        serv1.addMedecin(med1);
        serv1.addMedecin(med2);
        serv1.addMedecin(med3);
        serv2.addMedecin(med4);
        serv3.addMedecin(med5);
        serv3.addMedecin(med6);
        //serv1.addMedecin(med7);

        med4.addServiceDirige(serv2);
        med5.addServiceDirige(serv1);
        med5.addServiceDirige(serv3);

        med1.setChef(med2);
        med3.setChef(med2);

        Adresse adr1 = new Adresse("Rue des lilas", "Paris", "75000");

        Malade mal1 = new Malade("Proviste", "Alain");
        Malade mal2 = new Malade("Trahuil", "Phil");
        Malade mal3 = new Malade("Ancieux", "Cecile");
        Malade mal4 = new Malade("Conda", "Anna", adr1);


        et.begin();

        em.persist(serv1);
        em.persist(serv2);
        em.persist(serv3);


        em.persist(mal1);
        em.persist(mal2);
        em.persist(mal3);
        em.persist(mal4);


        et.commit();

      } catch (Exception ex) {
        System.out.println("exception: " + ex);
        System.out.println("rollback");
        et.rollback();
      }

    } finally {
      if(em != null && em.isOpen()){
        em.close();
      }
      if(emf != null && emf.isOpen()){
        emf.close();
      }
    }

  }

}
