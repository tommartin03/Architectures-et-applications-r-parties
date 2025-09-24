package services;

import org.springframework.stereotype.Service;


public class Service2{
    private int nbInvocations = 0;

    public void increment() {
        nbInvocations++;
    }

    public int getNbInvocations() {
        return nbInvocations;
    }


}
