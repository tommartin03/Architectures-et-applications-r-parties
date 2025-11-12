package dtos;

import entities.Adresse;
import entities.Employe;
import entities.Machine;
import entities.Projet;
import entities.Service;
import entities.Telephone;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeDto {

    private int id;
    private String prenom;
    private String nom;
    private String adresse;
    private String machine;
    private String service;
    private List<String> telephones;
    private List<String> projetsEnCours;
    private List<String> projetsTermines; // bonus pour DTO


    public EmployeDto() {
    }

    /**
     * Constructeur principal : crée un DTO à partir d'une entité Employe.
     * On transforme les associations en simples chaînes de caractères.
     */
    public EmployeDto(Employe e) {
        this.id = e.getIdEmp();
        this.prenom = e.getPrenom();
        this.nom = e.getNom();

        Adresse a = e.getAdresse();
        this.adresse = (a != null)
                ?  " " + a.getRue() + ", " + a.getCodepostal() + " " + a.getVille()
                : "Aucune adresse";

        Machine m = e.getMachine();
        this.machine = (m != null)
                ? m.getIdMachine() + " " + m.getAdresseMac() + " " +m.getAffecteeA() + " (" + m.getModele() + ")"
                : "Aucune machine";

        Service s = e.getService();
        this.service = (s != null)
                ? s.getNomService()
                : "Aucun service";

        this.telephones = e.getTelephones() != null
                ? e.getTelephones().stream()
                .map(Telephone::getNumero)
                .collect(Collectors.toCollection(ArrayList::new))
                : new ArrayList<>();

        this.projetsEnCours = e.getProjetsEnCours() != null
                ? e.getProjetsEnCours().stream()
                .map(p -> p.getCodeProjet() + " - " + p.getNomProjet())
                .collect(Collectors.toCollection(ArrayList::new))
                : new ArrayList<>();

        this.projetsTermines = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMachine() {
        return machine;
    }

    public String getService() {
        return service;
    }

    public List<String> getTelephones() {
        return telephones;
    }

    public List<String> getProjetsEnCours() {
        return projetsEnCours;
    }

    public List<String> getProjetsTermines() {
        return projetsTermines;
    }

    public void setProjetsTermines(List<String> projetsTermines) {
        this.projetsTermines = projetsTermines;
    }

}
