package com.example.onlinelearningplatform.ejbs;

import com.example.onlinelearningplatform.entities.TestEntity;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;


@Stateless
public class Test {


    EntityManager em;

    public Test() {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
        em=emf.createEntityManager();
    }

    public String testConnection() {
        try {
            List<TestEntity> testEntities = em.createQuery("SELECT t FROM TestEntity t", TestEntity.class)
                    .getResultList();

            if (!testEntities.isEmpty()) {
                return "Connection is working! Fetched " + testEntities.size() + " test entities.";
            } else {
                return "Connection is working! No test entities found.";
            }
        } catch (Exception e) {
            return "Error connecting to the database: " + e.getMessage();
        }
    }
    @Transactional // Ensure this method runs in a transaction
    public void insertTestData() {
        TestEntity testEntity = new TestEntity();
        testEntity.setName("Test Data");
        em.persist(testEntity);
    }


}
