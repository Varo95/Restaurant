package com.restaurant.utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PersistenceUnit {

    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    private PersistenceUnit(){

    }

    public static void init(){
        try {
            emf = Persistence.createEntityManagerFactory("ApplicationH2");
        } catch (final PersistenceException e) {
            log.error(e);
        }
    }

    public static EntityManager createEM() {
        if(em != null) em = null;
        em = emf.createEntityManager();
        return em;
    }

    public static void closeEMF() {
        if (emf != null) {
            emf.close();
            emf = null;
        }
    }

}
