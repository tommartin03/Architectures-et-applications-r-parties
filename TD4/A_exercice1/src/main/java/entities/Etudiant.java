package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Etudiant {
    @Id
    private String numEtu;
    private String prenom;
    private String nom;

    @ManyToMany(mappedBy = "appartient")
    private List<Groupe> groupe;

    @ManyToMany
    private List<Formation> inscritEn;

    public Etudiant() {
    }

    public Etudiant(String numEtu, String prenom, String nom) {
        this.numEtu = numEtu;
        this.prenom = prenom;
        this.nom = nom;
    }

    public String getNumEtu() {
        return numEtu;
    }

    public void setNumEtu(String numEtu) {
        this.numEtu = numEtu;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Groupe> getGroupe() {
        return groupe;
    }

    public void setGroupe(List<Groupe> groupe) {
        this.groupe = groupe;
    }

    public List<Formation> getInscritEn() {
        return inscritEn;
    }

    public void setInscritEn(List<Formation> inscritEn) {
        this.inscritEn = inscritEn;
    }
}
