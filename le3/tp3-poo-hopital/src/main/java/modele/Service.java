package modele;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERVNO")
    private int id;

    @Column(name = "SERVNAME", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "SERVLOC", nullable = false, length = 50)
    private String location;

    @ManyToOne
    @JoinColumn(name = "MEDNO")
    private Medecin dirigePar; // 0..1

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medecin> medecins = new ArrayList<>(); // 0..*

    public Service() {
    }

    public Service(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Medecin getDirigePar() {
        return dirigePar;
    }

    public void setDirigePar(Medecin dirigePar) {
        this.dirigePar = dirigePar;
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(List<Medecin> medecins) {
        this.medecins = medecins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(name, service.name) && Objects.equals(location, service.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public boolean addMedecin(Medecin m) {
        if (m == null) {
            return false;
        }

        if (m.getService() != null) {
            m.getService().getMedecins().remove(m);
        }

        m.setService(this);
        return medecins.add(m);
    }
}