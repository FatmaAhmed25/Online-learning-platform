package com.example.onlinelearningplatform.ejbs;

import com.example.onlinelearningplatform.DTOs.RegisterInstructorDTO;
import com.example.onlinelearningplatform.DTOs.RegisterStudentDTO;
import com.example.onlinelearningplatform.entities.Instructor;
import com.example.onlinelearningplatform.entities.Student;
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

    public void registerStudent(RegisterStudentDTO studentDTO) {
        // Check if the email already exists
        if (isEmailExists(studentDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // If email doesn't exist, proceed with registration
        Student student = new Student(studentDTO.getName(),studentDTO.getEmail(),studentDTO.getPassword(),studentDTO.getAffiliation(),studentDTO.getBio(),UserRole.STUDENT);
        em.persist(student);
    }

    public void registerInstructor(RegisterInstructorDTO instructorDTO) {
        // Check if the email already exists
        if (isEmailExists(instructorDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // If email doesn't exist, proceed with registration
        Instructor instructor = new Instructor(instructorDTO.getName(),instructorDTO.getEmail(),instructorDTO.getPassword(),instructorDTO.getAffiliation(),instructorDTO.getBio(),UserRole.INSTRUCTOR, instructorDTO.getYearsOfExperience());
        em.persist(instructor);
    }
    private boolean isEmailExists(String email) {
        Long count = (Long) em.createQuery("SELECT COUNT(s) FROM User s WHERE s.email = :email")
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }

}
