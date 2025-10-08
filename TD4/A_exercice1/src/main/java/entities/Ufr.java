package entities;

import jakarta.persistence.*;
import java.util.ArrayList;


import java.util.List;

@Entity
public class Ufr {
    @Id @GeneratedValue
    private int idUFR;
    private String singleUFR;

    @OneToMany(mappedBy = "gerePar")
    private List<Batiment> batiments;

    public Ufr() {
    }

    public Ufr(String singleUFR) {
        this.singleUFR = singleUFR;
    }

    public int getIdUFR() {
        return idUFR;
    }

    public String getSingleUFR() {
        return singleUFR;
    }

    public void setSingleUFR(String singleUFR) {
        this.singleUFR = singleUFR;
    }

    public List<Batiment> getBatiments() {
        return batiments;
    }

    public void setIdUFR(int idUFR) {
        this.idUFR = idUFR;
    }

    public void setBatiments(List<Batiment> batiments) {
        this.batiments = batiments;
    }
}
