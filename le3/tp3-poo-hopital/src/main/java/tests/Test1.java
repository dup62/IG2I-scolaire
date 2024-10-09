package tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import modele.Service;

/**
 * Created by Desnyder Rémi
 * Date: 09/10/2024
 */

public class Test1 {

    public static void main(String[] args) {

        final EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("HopitalPU");

        final EntityManager em = emf.createEntityManager();

        try {
            final EntityTransaction et = em.getTransaction();

            try {

                et.begin();
                Service serv1 = new Service("Cardiologie", "Bat A, 1er étage");
                Service serv2 = new Service("Pneumologie", "Bat B, 1er étage");
                Service serv3 = new Service("Urgence", "Bat C, 1er étage");

                em.persist(serv1);
                em.persist(serv2);
                serv1.setLocation("Bat D, 2ème étage");
                serv2.setName("Cardiologie");
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
