package entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Etudiant {
    @Id
    @Column(name = "NUMETU")
    private String numEtu;
    @Column(name = "PRENOM", nullable = false)
    private String prenom;
    @Column(name = "NOM", nullable = false)
    private String nom;

    @ManyToMany
    @JoinTable (name = "MEMBRES",
            joinColumns = {@JoinColumn(name = "NUMETU")},
            inverseJoinColumns = {@JoinColumn(name = "IDGROUPE")})
    private List<Groupe> groupe;

    @ManyToMany
    @JoinTable (name = "INSCRITS",
            joinColumns = {@JoinColumn(name = "NUMETU")},
            inverseJoinColumns = {@JoinColumn(name = "IDFORM")})
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
