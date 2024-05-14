package com.example.onlinelearningplatform.ejbs;


import com.example.onlinelearningplatform.entities.Admin;
import com.example.onlinelearningplatform.entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Singleton
@Startup
public class AdminInitializer {
    EntityManager em;

    public AdminInitializer() {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
        em=emf.createEntityManager();
    }
    @PostConstruct
    public void init() {

        TypedQuery<Long> query = em.createQuery("SELECT COUNT(a.id) FROM Admin a", Long.class);
        Long adminCount = query.getSingleResult();

        // Check if no admin users exist
        if (adminCount == 0) {
            // Create two admin users
            createAdmin("Admin1", "admin1@example.com", "password1");
            createAdmin("Admin2", "admin2@example.com", "password2");
        }
    }

    private void createAdmin(String name, String email, String password) {
        Admin admin = new Admin();
        admin.setName(name);
        admin.setEmail(email);
        admin.setPassword(password);
        em.persist(admin);
    }
}

