package services;

import entities.Etudiant;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class Facade {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void createEntity(String id,String prenom, String nom) {
        Etudiant et = new Etudiant(id,prenom, nom);
        em.persist(et);
    }

    public Collection<Etudiant> findAllEntity() {
        return em.createQuery("From Etudiant e").getResultList();
    }



}
