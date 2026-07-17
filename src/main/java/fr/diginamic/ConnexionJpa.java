package fr.diginamic;

import jakarta.persistence.*;

import java.util.List;

public class ConnexionJpa {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("maConfig1");
        EntityManager em = entityManagerFactory.createEntityManager();

        // Appel du/des TP(s) à exécuter
        tp3LivreTypedQuery(em);

        em.close();
        entityManagerFactory.close();
    }

    /**
     * Squelette utile pour un CRUD persistant (INSERT, UPDATE, DELETE...)
     */
    private static void crudTemplate(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin(); // DEMARRAGE TRANSACTION
        // INSERT, UPDATE, DELETE...
        transaction.commit(); // SAUVEGARDER DANS LA BDD
    }

    /**
     * TP2 - find simple sur Region
     */
    private static void tp2Region(EntityManager em) {
        Region reg1 = em.find(Region.class, 1);
        if (reg1 != null) {
            System.out.println(reg1.getNom());
        }
    }

    /**
     * TP3 - find simple sur Livre
     */
    private static void tp3LivreFind(EntityManager em) {
        Livre liv1 = em.find(Livre.class, 1);
        if (liv1 != null) {
            System.out.println(liv1.getId() + " - " + liv1.getTitre() + ", " + liv1.getAuteur());
        }
    }

    /**
     * TP3 - TypedQuery et JPQL, extraction de tous les livres
     */
    private static void tp3LivreTypedQuery(EntityManager em) {
        TypedQuery<Livre> query1 = em.createQuery("select l from Livre l", Livre.class);
        List<Livre> livres = query1.getResultList();

        for (Livre l : livres) {
            System.out.println(l.getId() + " - " + l.getTitre() + ", " + l.getAuteur());
        }
    }
}
