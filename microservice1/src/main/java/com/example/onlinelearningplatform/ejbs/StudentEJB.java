package com.example.onlinelearningplatform.ejbs;

import javax.ejb.Stateless;
import javax.persistence.*;

import com.example.onlinelearningplatform.entities.Student;
import com.example.onlinelearningplatform.entities.User;
import com.example.onlinelearningplatform.entities.UserRole;

@Stateless
public class StudentEJB {
    private EntityManager em;
    public StudentEJB() {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
        em=emf.createEntityManager();
    }

    public User getStudentById(long id) {
            User user = (User) em.createQuery("SELECT u FROM User u WHERE u.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
            if (user.getRole() == UserRole.STUDENT) {
                return user;
            } else {
                return null;
            }


    }
}