package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Formation {

    @Id
    private String idForm;
    private String intituleForm;

    @ManyToMany(mappedBy = "reserve")
    private List<Salle> ReserveeA;

    @ManyToMany(mappedBy = "etudiantDe")
    private List<Groupe> groupes;

    @ManyToMany(mappedBy = "inscritEn")
    private List<Etudiant> inscrits;

    public Formation() {
    }

    public Formation(String idForm, String intituleForm) {
        this.idForm = idForm;
        this.intituleForm = intituleForm;
    }

    public String getIdForm() {
        return idForm;
    }

    public void setIdForm(String idForm) {
        this.idForm = idForm;
    }

    public String getIntituleForm() {
        return intituleForm;
    }

    public void setIntituleForm(String intituleForm) {
        this.intituleForm = intituleForm;
    }

    public List<Salle> getReserveeA() {
        return ReserveeA;
    }

    public void setReserveeA(List<Salle> reserveeA) {
        ReserveeA = reserveeA;
    }

    public List<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<Groupe> groupes) {
        this.groupes = groupes;
    }

    public List<Etudiant> getInscrits() {
        return inscrits;
    }

    public void setInscrits(List<Etudiant> inscrits) {
        this.inscrits = inscrits;
    }
}
