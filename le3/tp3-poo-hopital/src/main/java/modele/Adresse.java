package modele;

import jakarta.persistence.Column;

/**
 * Created by Desnyder Rémi Date: 21/10/2024
 */

public class Adresse {

  public int number;
  @Column(nullable = false)
  public String street;

  @Column(nullable = false)
  private String city;

  @Column(name="ZIP_CODE")
  private String zipcode;

  public Adresse() {
  }

  public Adresse(String street, String city, String zipcode) {
    this.street = street;
    this.city = city;
    this.zipcode = zipcode;
  }
}
