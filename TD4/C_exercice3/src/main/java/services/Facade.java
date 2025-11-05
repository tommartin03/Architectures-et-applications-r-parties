package services;

import entities.Categorie;
import entities.Gestionnaire;
import entities.Produit;
import entities.Vente;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Service
public class Facade {
    @PersistenceContext
    EntityManager em;

    /**
     * Renvoie le produit dont l'id est fourni. et null si non trouvé.
     * @param idProduit
     * @return
     */
    public Produit findById(int idProduit) {
        Query query = em.createNamedQuery("entities.Produit.findByID");
        query.setParameter("idProduit", idProduit);
        return  (Produit) query.getSingleResult();
    }

    /**
     * Renvoie le nom du produit correspondant à la plus grosse ligne de vente
     * @return
     */
    public String  nomProduitPlusGrosseLigne(){
        Query query = em.createQuery("SELECT p.nomProduit FROM Produit p JOIN Vente v WHERE v.id = (SELECT MAX(lv.quantite) FROM LigneVente lv)");
        return (String) query.getSingleResult();
    }

    /**
     * Renvoie le produit dont les ventes sont les plus grandes en nombre d'unité (en quantité)
     * VOUS NE POURREZ PAS LE FAIRE DIRECTEMENT EN JPQL OU SQL : PASSEZ PAR UNE VUE DANS LA BD ET UNE REQUETE NATIVE
     * OU PAR UN PEU DE TRAITEMENT EN JAVA...
     * @return
     */
    public Produit plusGrosseVenteQuantite() {
        Query query = em.createQuery("SELECT p FROM Produit p JOIN LigneVente lv ON p.idProduit = lv.produit.idProduit GROUP BY p.idProduit ORDER BY SUM(lv.quantite) DESC");
        query.setMaxResults(1);
        return (Produit) query.getSingleResult();
    }

    /**
     * Renvoie le produit dont les ventes génèrent le plus gros chiffre d'affaire (quantité * prix unitaire)
     * VOUS NE POURREZ PAS LE FAIRE DIRECTEMENT EN JPQL OU SQL : PASSEZ PAR UNE VUE DANS LA BD ET UNE REQUETE NATIVE
     * OU PAR UN PEU DE TRAITEMENT EN JAVA...
     * @return
     */
    public Produit plusGrosseVenteMontant() {
        Query query = em.createQuery("SELECT p FROM Produit p JOIN LigneVente lv ON p.idProduit = lv.produit.idProduit GROUP BY p.idProduit ORDER BY SUM(lv.quantite) DESC");
        query.setMaxResults(1);
        return (Produit) query.getSingleResult();
    }

    /**
     * Renvoie la liste des produits dont le stock est inférieur ou égal à stockMini
     * @param stockMini
     * @return
     */
    public List<Produit> stockSous(int stockMini) {
        Query query = em.createQuery("SELECT p FROM Produit p WHERE p.stock <= :stockMini");
        query.setParameter("stockMini", stockMini);
        return query.getResultList();
    }

    /**
     * Renvoie la liste des ventes comportant le produit dont l'id est en paramètre
     * @param idProduit
     * @return
     */
    public List<Vente> ventesDe(int idProduit) {
        Query query = em.createQuery("SELECT v FROM Vente v JOIN LigneVente lv ON v.idVente = lv.vente.idVente WHERE lv.produit.idProduit = :idProduit");
        query.setParameter("idProduit", idProduit);
        return query.getResultList();
    }

      /**
     * Renvoie la liste ** dates de commande *** des ventes comportant le produit dont l'id est en paramètre
     * @param idProduit
     * @return
     */
      public List<LocalDate> datesVentesDe(int idProduit) {
          String jpql = """
                SELECT DISTINCT v.dateCmd 
                FROM Vente v 
                JOIN v.lignesV lv 
                WHERE lv.produit.idProduit = :idProduit
                ORDER BY v.dateCmd
            """;

          Query query = em.createQuery(jpql);
          query.setParameter("idProduit", idProduit);
          return query.getResultList();
      }

    /**
     * Renvoie la liste de sproduits qui n'ont jamais été vendus (aucune vente)
     * @return
     */
    public List<Produit> produitsNonVendus() {
        String jpql = """
        SELECT p 
        FROM Produit p 
        LEFT JOIN p.ventes lv 
        WHERE lv IS NULL
        """;

        return em.createQuery(jpql, Produit.class).getResultList();
    }

    /**
     * Renvoie les gestionnaires qui ont déjà passé des demandes d'approvisionnement pour le produit en paramètre
     * @param idProduit
     * @return
     */
    public List<Gestionnaire> acheteurDe(int idProduit) {
        String jpql = """
        SELECT DISTINCT la.approvisionnement.gestionnaire 
        FROM LigneApprovisionnement la 
        WHERE la.produit.idProduit = :idProduit
        """;

        return em.createQuery(jpql, Gestionnaire.class)
                .setParameter("idProduit", idProduit)
                .getResultList();
    }

    /**
     * Renvoie le produit le moins cher de la catégorie indiquée.
     * @param idCategorie
     * @return
     */
    public Produit moinsCherDe(int idCategorie) {
        String jpql = """
        SELECT p 
        FROM Produit p 
        WHERE p.categorie.idCategorie = :idCategorie 
        ORDER BY p.prixVente ASC
        """;

        List<Produit> produits = em.createQuery(jpql, Produit.class)
                .setParameter("idCategorie", idCategorie)
                .setMaxResults(1)
                .getResultList();

        return produits.isEmpty() ? null : produits.get(0);
    }

    /**
     * Renvoie la catégorie du produit indiqué.
     * @param idProduit
     * @return
     */
    public Categorie categorieDe(int idProduit) {
        String jpql = """
        SELECT p.categorie 
        FROM Produit p 
        WHERE p.idProduit = :idProduit
        """;

        try {
            return em.createQuery(jpql, Categorie.class)
                    .setParameter("idProduit", idProduit)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
