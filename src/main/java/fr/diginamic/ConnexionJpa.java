package fr.diginamic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ConnexionJpa {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("maConfig1");
        EntityManager em = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin(); // DEMARRAGE TRANSACTION

        Region reg1 = em.find(Region.class, 1);
        if (reg1 != null){
            System.out.println(reg1.getNom());
        }

        transaction.commit(); // SAUVEGARDER DANS LA BDD
    }
}
