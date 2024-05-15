package com.example.onlinelearningplatform.ejbs;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Stateless
public class UserEJB {

    private EntityManager em;
    public UserEJB() {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
        em=emf.createEntityManager();
    }

    public long getNumberOfUsers() {
        Query query = em.createQuery("SELECT COUNT(u) FROM User u");
        return (long) query.getSingleResult();
    }



}
