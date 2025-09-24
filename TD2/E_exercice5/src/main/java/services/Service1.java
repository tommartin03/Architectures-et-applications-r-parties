package services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Service1{
    private Map<String,String> users;
    private List<String> humeurs=List.of("heureux","triste","excité","bavard");


    // On évite de toucher au constructeur... du coup on fait l'initialisation de la map dans une méthode annotée PostConstruct
    @PostConstruct
    public void fillMap(){
        users=new HashMap<>();
        users.put("alice","alice");
        users.put("bob","bob");
    }


    public boolean checkLP(String login, String password) {
        String pwd=users.get(login);
        return ((pwd!=null) && (pwd.equals(password)));
    }

    public List<String> getHumeurs() {
        return humeurs;
    }



}
