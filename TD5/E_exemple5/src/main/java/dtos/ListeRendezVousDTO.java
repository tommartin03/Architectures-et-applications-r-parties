package dtos;

import entities.Seance;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ListeRendezVousDTO {

    private List<String> rendezVous;

    public ListeRendezVousDTO(List<Seance> seances) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        this.rendezVous = seances.stream()
                .map(s -> String.format(
                        "Groupe %s – %s (%s) de %s à %s",
                        s.getGroupe().getLibelleGroupe(),
                        s.getMatiere().getIntituleMatiere(),
                        s.getSalle().getNomSalle(),
                        s.getDebut().format(fmt),
                        s.getFin().format(fmt)
                ))
                .collect(Collectors.toList());
    }

    public List<String> getRendezVous() {
        return rendezVous;
    }


}
