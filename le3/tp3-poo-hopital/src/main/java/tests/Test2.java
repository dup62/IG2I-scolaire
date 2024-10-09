package tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import modele.Medecin;

/**
 * Created by Desnyder Rémi
 * Date: 09/10/2024
 */

public class Test2 {

    public static void main(String[] args) {

        final EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("HopitalPU");

        final EntityManager em = emf.createEntityManager();

        try {
            final EntityTransaction et = em.getTransaction();

            try {

                et.begin();
                Medecin med1 = new Medecin("Trancen", "Jean", 2135.23);
                Medecin med2 = new Medecin("Gator", "Coralie", 3156.00);
                Medecin med3 = new Medecin("Gator", "Magalie", 2545.3723);
                Medecin med4 = new Medecin("Trancen", "Jean", 2135.24);
                em.persist(med1);
                em.persist(med2);
                em.persist(med3);
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
