package facades;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * on va un peu bricoler pour avoir un singleton...
 */
public class Facade {
    public static Facade instance=null;
    private String aDeviner=null;
    private StringBuilder devine=null;
    private int nbEssaisRestants;

    Map<String,Integer> compteur=new HashMap();


    /**
     *
     * @return l'unique instance
     */
    public static synchronized Facade getInstance(){
        if (instance==null) {
            instance=new Facade();
        }
        return instance;
    }

    /**
     * Constructeur privé, ne peut etre appelé que depuis la classe...
     */
    private Facade() {

    }

    public void ajout(String key) {
        int nbajouts=0;
        if (compteur.containsKey(key)) {
            nbajouts=compteur.get(key);
        }
        nbajouts++;
        compteur.put(key,nbajouts);
    }

    public Set<Map.Entry<String, Integer>> getCompteurAsPairs() {
        return compteur.entrySet();
    }

    public void setaDeviner(String aDeviner) {
        this.aDeviner=aDeviner;
        this.devine=new StringBuilder("_".repeat(aDeviner.length()));
        this.nbEssaisRestants=10;
    }

    public boolean test(char carac){
        boolean res=false;
        int last=aDeviner.indexOf(carac,0);
        while (last!=-1) {
            res = true;
            devine.setCharAt(last, carac);
            last=aDeviner.indexOf(carac,last+1);
        }
        if (res==false) {
            nbEssaisRestants--;
        }
        return aDeviner.equals(devine.toString());
    }

    // Getters and setters for aDeviner, devine, and nbEssaisRestants
    public String getADeviner() {
        return aDeviner;
    }
    public StringBuilder getDevine() {
        return devine;
    }
    public int getNbEssaisRestants() {
        return nbEssaisRestants;
    }

}
