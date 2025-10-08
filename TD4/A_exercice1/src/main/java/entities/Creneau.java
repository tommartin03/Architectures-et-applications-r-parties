package entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Creneau {

    @Id @GeneratedValue
    private int Creneau;
    private LocalDateTime debut;
    private LocalDateTime fin;
    @ManyToOne
    private Salle dans;

    @ManyToOne
    private Groupe concerne;

    public Creneau() {
    }

    public Creneau(LocalDateTime debut, LocalDateTime fin) {
        this.debut = debut;
        this.fin = fin;
    }

    public Groupe getConcerne() {
        return concerne;
    }

    public void setConcerne(Groupe concerne) {
        this.concerne = concerne;
    }

    public int getCreneau() {
        return Creneau;
    }

    public void setCreneau(int creneau) {
        Creneau = creneau;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public Salle getDans() {
        return dans;
    }

    public void setDans(Salle dans) {
        this.dans = dans;
    }
}
