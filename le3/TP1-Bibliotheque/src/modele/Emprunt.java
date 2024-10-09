package modele;

import java.util.Date;
import java.util.Objects;


/**
 * Created by Desnyder Rémi
 * Date: 11/09/2024
 */

public class Emprunt {

    private final int id;
    private Date dateEmprunt;
    private Date dateRetour;
    private Usager usager;
    private Livre livre;

    public Emprunt(int id, Date dateEmprunt, Date dateRetour, Usager usager, Livre livre) {
        this.id = id;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.usager = usager;
        this.livre = livre;
    }

    public Livre getLivre() {
        return livre;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetour=" + dateRetour +
                ", usager=" + usager +
                ", livre=" + livre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprunt emprunt = (Emprunt) o;
        return id == emprunt.id && dateEmprunt == emprunt.dateEmprunt && dateRetour == emprunt.dateRetour && Objects.equals(usager, emprunt.usager) && Objects.equals(livre, emprunt.livre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateEmprunt, dateRetour, usager, livre);
    }
}

