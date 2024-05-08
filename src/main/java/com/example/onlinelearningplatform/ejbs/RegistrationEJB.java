package com.example.onlinelearningplatform.ejbs;

import com.example.onlinelearningplatform.entities.User;
import com.example.onlinelearningplatform.entities.UserRole;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Stateless
public class RegistrationEJB
{

    EntityManager em;

    public RegistrationEJB() {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
        em=emf.createEntityManager();
    }

    public void registerStudent(String name, String email, String password, String affiliation, String bio)
    {
        User student = new User();
        student.setName(name);
        student.setEmail(email);
        student.setPassword(password);
        student.setAffiliation(affiliation);
        student.setBio(bio);
        student.setRole(UserRole.STUDENT);

        em.persist(student);
    }

    public void registerInstructor(String name, String email, String password, String affiliation, String bio, int yearsOfExperience) {
        User instructor = new User();
        instructor.setName(name);
        instructor.setEmail(email);
        instructor.setPassword(password);
        instructor.setAffiliation(affiliation);
        instructor.setBio(bio);
        instructor.setYearsOfExperience(yearsOfExperience);
        instructor.setRole(UserRole.INSTRUCTOR);

        em.persist(instructor);
    }

}
