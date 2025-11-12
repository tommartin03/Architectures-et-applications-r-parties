package controllers;

import dtos.EmployeDto;
import entities.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import services.Facade;

@Controller
@RequestMapping("/")
public class Exercice1Controller {
    @Autowired
    Facade facade;

    @RequestMapping("")
    public String hello(Model model) {
        EmployeDto dto=facade.getEmployeDto(1);
        model.addAttribute("employe",dto);
        Employe e=facade.getEmployeAvecJoinFetch(1);
        model.addAttribute("employeJoinFetch",e);
        return "hello";
    }
}
