package fr.diginamic;

import jakarta.persistence.*;

import java.util.List;

public class ConnexionJpa {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("maConfig1");
        EntityManager em = entityManagerFactory.createEntityManager();

        // Appel du/des TP(s) à exécuter
        //tp4FindEmpruntsByClient(em, 1);

        testDesyncManyToMany(em);

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

    /**
     * TP4-1 - Annotations relationnelles
     */
    private static void tp4FindClientById(EntityManager em, int id) {
        Client client = em.find(Client.class, id);
        if (client != null) {
            System.out.println(client.getNom() + " " + client.getPrenom());
            for (Emprunt e : client.getEmprunts()) {
                System.out.println("  - Emprunt #" + e.getId() + " du " + e.getDateDebut());
            }
        }
    }

    /**
     * TP4-2 - Annotations relationnelles
     */
    private static void tp4FindEmpruntById(EntityManager em, int id) {
        Emprunt emprunt = em.find(Emprunt.class, id);
        if (emprunt != null) {
            System.out.println("Emprunt #" + emprunt.getId() + " " + emprunt.getDateDebut());
            for (Livre l : emprunt.getLivres()) {
                System.out.println("  - Livre #" + l.getId() + " " + l.getTitre() + ", " + l.getAuteur());
            }
        }
    }

    private static void tp4FindEmpruntsByClient(EntityManager em, int id) {
        Client client = em.find(Client.class, id);
        if (client != null) {
            System.out.println(client.getNom() + " " + client.getPrenom());
            for (Emprunt e : client.getEmprunts()) {
                System.out.println("  -- Emprunt #" + e.getId() + " du " + e.getDateDebut());
                for (Livre l : e.getLivres()) {
                    System.out.println("    - Livre #" + l.getId() + " " + l.getTitre() + ", " + l.getAuteur());
                }
            }
        }
    }
    // méthode pour tester les limites du double @JoinTable (= pas de mappedBy)
    // résultat : suppression OK côté base, mais incohérence côté mémoire java = desynchronisation !
    // mais a priori même problème avec le mappedBy --> solution: méthodes utilitaires
    private static void testDesyncManyToMany(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Emprunt emprunt2 = em.find(Emprunt.class, 2);
        Livre livre3 = em.find(Livre.class, 3);

        // On force le chargement des deux collections AVANT modification
        System.out.println("Avant - livre3 contient emprunt2 ? " + livre3.getEmprunts().contains(emprunt2));

        // On supprime la relation UNIQUEMENT via le côté Emprunt
        emprunt2.getLivres().remove(livre3);

        // On NE touche PAS livre3.getEmprunts()
        System.out.println("Après modif Emprunt - livre3 contient encore emprunt2 en mémoire ? "
                + livre3.getEmprunts().contains(emprunt2));

        tx.commit();
    }
}


