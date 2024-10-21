package requete;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modele.Medecin;
import modele.Service;

/**
 * Created by Desnyder Rémi Date: 21/10/2024
 */

public class RequeteHopital {

  private EntityManagerFactory emf;

  public RequeteHopital() {
    this.emf = Persistence.createEntityManagerFactory("HopitalPU");
  }

  public List<Service> getServices() {

    EntityManager em = emf.createEntityManager();
    String strQuery = "SELECT s FROM Service s";
    Query query = em.createQuery(strQuery);
    List<Service> services = query.getResultList();
    em.close();
    return services;

  }

  public List<Medecin> getMedecins() {
    EntityManager em = emf.createEntityManager();
    Query query = em.createNamedQuery("Medecin.getAllMedecin");
    List<Medecin> medecins = query.getResultList();
    em.close();
    return medecins;
  }

  public List<Medecin> getMedecinsChef() {

    EntityManager em = emf.createEntityManager();
    //String strQuery = "SELECT m FROM Medecin m WHERE m.id IN (SELECT DISTINCT m2.estGerePar.id FROM Medecin m2 WHERE m2.estGerePar IS NOT NULL)";
    //String strQuery = "SELECT m FROM Medecin m WHERE m.estGerePar IS NOT NULL";
    String strQuery = "SELECT m.estGerePar FROM Medecin m";
    Query query = em.createQuery(strQuery);
    List<Medecin> medecins = query.getResultList();
    em.close();
    return medecins;

  }

  public List<Medecin> getMedecinsWithSalaryGreaterThan(int salary) {

    EntityManager em = emf.createEntityManager();
    String strQuery = "SELECT m FROM Medecin m WHERE m.salaire > :salary";
    Query query = em.createQuery(strQuery);
    query.setParameter("salary", salary);
    List<Medecin> medecins = query.getResultList();
    em.close();
    return medecins;

  }

  public Map<String, Double> getSalairesMoyenParService() {

    EntityManager em = emf.createEntityManager();
    String strQuery = "SELECT s.name, AVG(m.salaire) FROM Medecin m JOIN m.service s GROUP BY s.name";
    Query query = em.createQuery(strQuery);
    List<Object[]> results = query.getResultList();
    Map<String, Double> salaires = new HashMap<>();
    for (Object[] result : results) {
      salaires.put((String) result[0], (Double) result[1]);
    }
    em.close();
    return salaires;
  }

  public List<Medecin> getMedecinsByService(String service) {
    EntityManager em = emf.createEntityManager();
    String strQuery = "SELECT m FROM Medecin m JOIN m.service s WHERE s.name = :service";
    Query query = em.createQuery(strQuery);
    query.setParameter("service", service);
    List<Medecin> medecins = query.getResultList();
    em.close();
    return medecins;
  }

  public List<Medecin> getMedecinByChef(String nom, String prenom) {
    EntityManager em = emf.createEntityManager();
    Query query = em.createNamedQuery("Medecin.findMedecinByChef");
    query.setParameter("nom", nom);
    query.setParameter("prenom", prenom);
    List<Medecin> medecins = query.getResultList();
    em.close();
    return medecins;
  }

  public void increaseMedecinSalaryByService(String service, double percent) {
    EntityManager em = emf.createEntityManager();
    final EntityTransaction et  = em.getTransaction();
    try {
      et.begin();
      String strQuery = "SELECT m FROM Medecin m JOIN m.service s WHERE s.name = :service";
      Query query = em.createQuery(strQuery);
      query.setParameter("service", service);
      List<Medecin> medecins = query.getResultList();
      for (Medecin medecin : medecins) {
        medecin.setSalaire(medecin.getSalaire() * (1 + percent / 100));
        em.merge(medecin);
      }
      et.commit();
    } catch (Exception e) {
      et.rollback();
    } finally {
      em.close();
    }

  }

  public void setMedecinSalary(double salary) {

    EntityManager em = emf.createEntityManager();

    final String strQuery = "UPDATE Medecin m SET m.salaire = :salary";
    Query query = em.createQuery(strQuery);
    query.setParameter("salary", salary);
    final EntityTransaction et = em.getTransaction();

    try {
      et.begin();
      query.executeUpdate();
      et.commit();
    } catch (Exception e) {
      et.rollback();
    } finally {
      em.close();
    }

  }

}
