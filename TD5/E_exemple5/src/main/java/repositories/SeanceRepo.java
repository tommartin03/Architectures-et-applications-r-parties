package repositories;

import entities.Seance;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SeanceRepo extends CrudRepository<Seance,Integer> {

    List<Seance> findByDebutBetween(LocalDateTime start, LocalDateTime end);

    List<Seance> findByGroupe_IdGroupe(int idGroupe);
}
