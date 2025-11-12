package controllers;

import dtos.AddSeanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.Facade;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class Exemple5Controller {
    @Autowired
    Facade facade;

    @GetMapping("")
    public String accueil(Model model){
        model.addAttribute("salles",facade.getAllSalles());
        model.addAttribute("groupes",facade.getAllGroupes());
        model.addAttribute("matieres",facade.getAllMatieres());


        return "welcome";
    }

    @PostMapping("addseance")
    public String addSeance(AddSeanceDTO seanceDTO,Model model){
        facade.addSeance(seanceDTO);
        return accueil(model);
    }

    @GetMapping("/seances/date")
    public String seancesParDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime date,
                                 Model model) {
        model.addAttribute("seances", facade.getSeancesByDate(date).getRendezVous());
        return "welcome";
    }

    @GetMapping("/seances/groupe")
    public String seancesParGroupe(@RequestParam("idGroupe") int idGroupe, Model model) {
        model.addAttribute("seances", facade.getSeancesByGroupe(idGroupe).getRendezVous());
        return "welcome";
    }

    @PostMapping("/seance/delete")
    public String deleteSeance(@RequestParam("id") int id, Model model) {
        facade.supprimerSeance(id);
        return accueil(model);
    }

}
