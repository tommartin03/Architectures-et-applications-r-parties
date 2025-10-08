package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Salle {

    @Id
    private String nomSalle;
    private int capacite;

    @OneToMany(mappedBy = "dans")
    private List<Creneau> occupations;

    @ManyToMany
    private List<Formation> reserve;

    public List<Formation> getReserve() {
        return reserve;
    }

    public void setReserve(List<Formation> reserve) {
        this.reserve = reserve;
    }

    public Salle() {
    }

    public Salle(String nomSalle, int capacite) {
        this.nomSalle = nomSalle;
        this.capacite = capacite;
    }


    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public List<Creneau> getOccupations() {
        return occupations;
    }

    public void setOccupations(List<Creneau> occupations) {
        this.occupations = occupations;
    }
}
