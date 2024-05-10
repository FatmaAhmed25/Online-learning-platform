package com.example.onlinelearningplatform.ejbs;

import com.example.onlinelearningplatform.entities.Instructor;
import com.example.onlinelearningplatform.entities.Student;
import com.example.onlinelearningplatform.entities.User;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateful
public class AdminEJB {

    EntityManager em;

    public AdminEJB() {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
        em=emf.createEntityManager();
    }

    @Transactional
    public void editUserAccount(User editedUser) {
        em.merge(editedUser);
    }

    public List<User> getAllUserAccounts() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public List<Instructor> getAllInstructorAccounts() {
        return em.createQuery("SELECT i FROM Instructor i", Instructor.class).getResultList();
    }

    public List<Student> getAllStudentAccounts() {
        return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    @Transactional
    public void deleteUserAccount(Long userId) {
        User user = em.find(User.class, userId);
        if (user != null) {
            em.remove(user);
        }
    }
}
