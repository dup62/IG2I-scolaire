package modele;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

/**
 * Created by Desnyder Rémi Date: 21/10/2024
 */

@Entity
public class Malade extends Personne {

  @Embedded
  private Adresse address;

  public Malade() {
    super();
  }

  public Malade(String nom, String prenom) {
    super(nom, prenom);
  }

  public Malade(String nom, String prenom, Adresse address) {
    super(nom, prenom);
    this.address = address;
  }
}
