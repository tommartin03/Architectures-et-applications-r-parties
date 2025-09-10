package facades;

import java.util.*;

/**
 * on va un peu bricoler pour avoir un singleton...
 */
public class Facade {
    public static Facade instance=null;
    private List<String> mots = new ArrayList<>();

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

    public void setMots(String mot) {
        this.mots.add(mot);
    }

    public String getMot() {
        Random rand = new Random();
        return this.mots.get(rand.nextInt(this.mots.size()));
    }

    public List<String> getMots() {
        return mots;
    }


}
