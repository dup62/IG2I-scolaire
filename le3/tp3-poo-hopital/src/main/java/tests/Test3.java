package tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import modele.Medecin;
import modele.Service;

/**
 * Created by Desnyder Rémi
 * Date: 09/10/2024
 */

public class Test3 {

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
                serv1.addMedecin(med7);

                med4.addServiceDirige(serv2);
                med5.addServiceDirige(serv1);
                med5.addServiceDirige(serv3);

                med1.setChef(med2);
                med3.setChef(med2);


                et.begin();
                em.persist(serv1);
                em.persist(serv2);
                em.persist(serv3);
                et.commit();

            } catch (Exception ex) {
                System.out.println("exception: " + ex);
                System.out.println("rollback");
                et.rollback();
            }

            try {

                et.begin();

                Service serv1 = em.find(Service.class, 1);
                Service serv2 = em.find(Service.class, 2);
                Service serv3 = em.find(Service.class, 3);

                Medecin med1 = em.find(Medecin.class, 1);
                Medecin med2 = em.find(Medecin.class, 2);
                Medecin med3 = em.find(Medecin.class, 3);

                System.out.println(med2.getGere());

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