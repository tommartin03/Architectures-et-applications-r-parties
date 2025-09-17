package services;

import org.springframework.stereotype.Service;

@Service
public class Compteur {
    private int nbInvocations = 0;

    public synchronized void increment() {
        nbInvocations++;
    }

    public synchronized int getNbInvocations() {
        return nbInvocations;
    }
}
