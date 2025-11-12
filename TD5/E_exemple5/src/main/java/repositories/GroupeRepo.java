package repositories;

import entities.Groupe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupeRepo extends CrudRepository<Groupe,Integer> {
    List<Groupe> findByTailleGroupe(int tailleGroupe);

    List<Groupe> findByTailleGroupeGreaterThan(int tailleGroupe);
}
