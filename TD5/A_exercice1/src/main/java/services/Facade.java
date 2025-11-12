package services;

import dtos.EmployeDto;
import entities.Employe;
import entities.Projet;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Facade {
    @PersistenceContext
    EntityManager em;

    @Transactional(readOnly = true)
    public EmployeDto getEmployeDto(int idEmploye) {
        Employe e=em.find(Employe.class,idEmploye);
        EmployeDto dto=new EmployeDto(e);
        List<String> projetsTermines = getProjetsTerminesByEmploye(idEmploye);
        return dto;
    }

    private List<String> getProjetsTerminesByEmploye(int idEmp) {
        TypedQuery<Projet> query = em.createQuery("""
            SELECT p FROM Projet p
            JOIN p.participants e
            WHERE e.idEmp = :idEmp
            AND p.statut = 'TERMINE'
        """, Projet.class);

        query.setParameter("idEmp", idEmp);

        return query.getResultList().stream()
                .map(p -> p.getCodeProjet() + " - " + p.getNomProjet())
                .collect(Collectors.toList());
    }


    public Employe getEmployeAvecJoinFetch(int idEmploye) {
        return em.createQuery("""
                SELECT e FROM Employe e
                LEFT JOIN FETCH e.telephones
                LEFT JOIN FETCH e.machine
                LEFT JOIN FETCH e.service
                LEFT JOIN FETCH e.adresse
                LEFT JOIN FETCH e.projetsEnCours
                WHERE e.idEmp = :id
                """, Employe.class)
                .setParameter("id", idEmploye)
                .getSingleResult();
    }




}
