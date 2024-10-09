package modele;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Desnyder Rémi
 * Date: 11/09/2024
 */

public class Usager {

    private final int idUsager;
    private String nom;
    private String prenom;
    private String adresse;
    private Date dateNaissance;

    public Usager(int idUsager, String nom, String prenom, String adresse, Date dateNaissance) {
        this.idUsager = idUsager;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
    }

    public int getIdUsager() {
        return idUsager;
    }

    public String getAffichageStandard() {
        return this.prenom + " " + this.nom + " : " + this.adresse;
    }

    @Override
    public String toString() {
        return "Usager{" +
                "idUsager=" + idUsager +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usager usager = (Usager) o;
        return Objects.equals(nom, usager.nom) && Objects.equals(prenom, usager.prenom) && Objects.equals(adresse, usager.adresse) && Objects.equals(dateNaissance, usager.dateNaissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, adresse, dateNaissance);
    }
}
