package controllers;

import dtos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import services.Compteur;
import services.Facade;

@Controller
@SessionAttributes({"courant", "humeur", "compteur"})
@RequestMapping("/")
public class Exercice4Controller {

    @Autowired
    private Compteur compteur;

    @Autowired
    private Facade facade;

    @RequestMapping("")
    public String toLogin(Model model) {
        model.addAttribute("humeurs", facade.getHumeurs());
        compteur.increment();
        model.addAttribute("compteur", compteur.getNbInvocations());
        return "login";
    }

    // on passe un objet user en paramètre : mapping automatique des champs du formulaire
    // on joue aussi avec les messages d'erreurs (BindingResult) ...
    @RequestMapping("login")
    public String checkLP(User user, Model model){
        compteur.increment();
        if (facade.checkLP(user.getLogin(),user.getPassword())) {
            // on place courant dans le modèle, mais il s'agit d'un attribut de session, il se retrouve ainsi conservé en session
            model.addAttribute("courant",user.getLogin());
            model.addAttribute("username",user.getLogin());
            model.addAttribute("humeur",user.getHumeur());
            model.addAttribute("compteur",compteur.getNbInvocations());
            return "welcome";
        } else {
            // on ajoute un simple message d'erreur au modèle...
            model.addAttribute("error","Les informations saisies ne correspondent pas à un utilisateur connu.");
            return "login";
        }
    }

    @RequestMapping("simplecheck")
    public String simpleCheck(@SessionAttribute("courant") String courant,Model model){
        System.out.println(courant);
        model.addAttribute("username",courant);
        compteur.increment();
        return "welcome";
    }

    @RequestMapping("logout")
    public String logout(SessionStatus status, Model model) {
        status.setComplete();
        compteur.increment();
        model.addAttribute("humeurs", facade.getHumeurs());
        model.addAttribute("compteur",compteur.getNbInvocations());
        return "login";
    }
}
