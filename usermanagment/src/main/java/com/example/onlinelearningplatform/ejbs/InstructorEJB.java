package com.example.onlinelearningplatform.ejbs;

import javax.ejb.Stateless;
import javax.persistence.*;

import com.example.onlinelearningplatform.entities.Student;
import com.example.onlinelearningplatform.entities.User;
import com.example.onlinelearningplatform.entities.UserRole;

@Stateless
public class InstructorEJB {
    private EntityManager em;
    public InstructorEJB() {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
        em=emf.createEntityManager();
    }

    public User getInstructorById(long id) {
        User user = (User) em.createQuery("SELECT u FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        if (user.getRole() == UserRole.INSTRUCTOR) {
            return user;
        } else {
            return null;
        }

    }
    public long getNumberOfInstructors() {
        Query query = em.createQuery("SELECT COUNT(u) FROM Instructor u");
        return (long) query.getSingleResult();
    }
}