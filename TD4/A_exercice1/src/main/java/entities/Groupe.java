package entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Groupe {
    @Id @GeneratedValue
    private int idGroupe;
    private String intitule;

    @OneToMany(mappedBy = "concerne")
    private List<Creneau> creneaux;

    @ManyToMany
    private List<Etudiant> appartient;

    @ManyToMany
    private List<Formation> etudiantDe;

    public Groupe() {
    }

    public List<Formation> getEtudiantDe() {
        return etudiantDe;
    }

    public void setEtudiantDe(List<Formation> etudiantDe) {
        this.etudiantDe = etudiantDe;
    }

    public Groupe(String intitule) {
        this.intitule = intitule;
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public List<Creneau> getCreneaux() {
        return creneaux;
    }

    public void setCreneaux(List<Creneau> creneaux) {
        this.creneaux = creneaux;
    }

    public List<Etudiant> getAppartient() {
        return appartient;
    }

    public void setAppartient(List<Etudiant> appartient) {
        this.appartient = appartient;
    }
}
