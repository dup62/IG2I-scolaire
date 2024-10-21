package modele;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NamedQueries({
    @NamedQuery(name = "Medecin.findMedecinByChef",
        query = "SELECT m FROM Medecin m WHERE m.estGerePar.nom = :nom AND m.estGerePar.prenom = :prenom"),
    @NamedQuery(name = "Medecin.getAllMedecin",
        query = "SELECT m FROM Medecin m"),
})
@Entity
public class Medecin extends Personne{

    @Column(name = "MEDSALAIRE", nullable = false, columnDefinition = "NUMERIC(10,2)")
    private double salaire;

    @ManyToOne
    @JoinColumn(name = "SERVNO")
    private Service service; // 0..1

    @OneToMany(mappedBy = "dirigePar")
    private List<Service> serviceDirige = new ArrayList<>(); // 0..*

    @OneToMany(mappedBy = "estGerePar")
    private List<Medecin> gere = new ArrayList<>(); // 0..*

    @ManyToOne
    @JoinColumn(name = "GERANT_ID")
    private Medecin estGerePar; // 0..1

    public Medecin() {
        super();
    }

    public Medecin(String nom, String prenom, double salaire) {
        super(nom, prenom);
        this.salaire = salaire;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServiceDirige() {
        return serviceDirige;
    }

    public void setServiceDirige(List<Service> serviceDirige) {
        this.serviceDirige = serviceDirige;
    }

    public List<Medecin> getGere() {
        return gere;
    }

    public void setGere(List<Medecin> gere) {
        this.gere = gere;
    }

    public Medecin getEstGerePar() {
        return estGerePar;
    }

    public void setEstGerePar(Medecin estGerePar) {
        this.estGerePar = estGerePar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medecin medecin = (Medecin) o;
        return Double.compare(salaire, medecin.salaire) == 0 && Objects.equals(this.getNom(), medecin.getNom()) && Objects.equals(this.getPrenom(), medecin.getPrenom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getNom(), this.getPrenom(), salaire);
    }

    @Override
    public String toString() {
        return "Medecin{" +
                super.toString() +
                ", salaire=" + salaire +
                '}';
    }

    public boolean addServiceDirige(Service s) {
        if (s == null) {
            return false;
        }

        if (s.getDirigePar() != null) {
            s.getDirigePar().getServiceDirige().remove(s);
        }

        s.setDirigePar(this);
        return serviceDirige.add(s);
    }

    public boolean setChef (Medecin m) {

        if (m == null) {
            return false;
        }

        if (m.equals(this)) {
            return false;
        }

        if (estGerePar != null) {
            estGerePar.getGere().remove(this);
        }

        estGerePar = m;

        return m.getGere().add(this);

    }
}