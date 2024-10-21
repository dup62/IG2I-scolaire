package modele;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Created by Desnyder Rémi Date: 21/10/2024
 */

@Entity
@Table(
    name = "Personne",
    uniqueConstraints={
        @UniqueConstraint(
            name = "UNIQUE_PERSONNE",
            columnNames = {"PERSONNE_NOM", "PERSONNE_PRENOM"}
        )
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Personne {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PERSONNE_ID")
  private int id;

  @Column(name = "PERSONNE_NOM", nullable = false, length = 50)
  private String nom;

  @Column(name = "PERSONNE_PRENOM", nullable = false, length = 50)
  private String prenom;

  public Personne() {
  }

  public Personne(String nom, String prenom) {
    this.nom = nom;
    this.prenom = prenom;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  @Override
  public String toString() {
    return "Personne{" +
        "id=" + id +
        ", nom='" + nom + '\'' +
        ", prenom='" + prenom + '\'' +
        '}';
  }
}
