package services;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class Facade {
    private final Service1 service1;
    private final Service2 service2;

    public Facade(Service1 service1, Service2 service2) {
        this.service1 = service1;
        this.service2 = service2;
    }

    @PostConstruct
    public void fillMap(){
        service1.fillMap();
    }


    public boolean checkLP(String login, String password) {
        return service1.checkLP(login, password);

    }

    public List<String> getHumeurs() {
        return service1.getHumeurs();
    }

    public void increment() {
        service2.increment();
    }

    public int getNbInvocations() {
        return service2.getNbInvocations();
    }

}
