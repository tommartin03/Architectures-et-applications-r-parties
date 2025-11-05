package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Groupe {
    @Id @GeneratedValue
    @Column(name = "IDGROUP")
    private int idGroup;
    @Column(name = "INTITULE", nullable = false)
    private String intitule;

}
