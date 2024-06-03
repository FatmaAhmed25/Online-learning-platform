package com.example.onlinelearningplatform.ejbs;

import com.example.onlinelearningplatform.entities.*;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class AdminEJB {

    EntityManager em;
    public User getAdminById(long id) {
        User user = (User) em.createQuery("SELECT u FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        if (user.getRole() == UserRole.ADMIN) {
            return user;
        } else {
            return null;
        }


    }

    public AdminEJB() {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
        em=emf.createEntityManager();
    }

    public void editUserAccount(User editedUser) {
        User existingUser = em.find(User.class, editedUser.getId());

        if (existingUser == null) {
            throw new EntityNotFoundException("User not found with id " + editedUser.getId());
        }

        // Update common fields only if they are not null in editedUser
        if (editedUser.getName() != null) {
            existingUser.setName(editedUser.getName());
        }
        if (editedUser.getEmail() != null) {
            existingUser.setEmail(editedUser.getEmail());
        }
        if (editedUser.getPassword() != null) {
            existingUser.setPassword(editedUser.getPassword());
        }

        if (existingUser instanceof Instructor && editedUser instanceof Instructor) {
            Instructor existingInstructor = (Instructor) existingUser;
            Instructor editedInstructor = (Instructor) editedUser;

            if (editedInstructor.getAffiliation() != null) {
                existingInstructor.setAffiliation(editedInstructor.getAffiliation());
            }
            if (editedInstructor.getBio() != null) {
                existingInstructor.setBio(editedInstructor.getBio());
            }
            if (editedInstructor.getYearsOfExperience() != null) {
                existingInstructor.setYearsOfExperience(editedInstructor.getYearsOfExperience());
            }
        } else if (existingUser instanceof Student && editedUser instanceof Student) {
            Student existingStudent = (Student) existingUser;
            Student editedStudent = (Student) editedUser;

            if (editedStudent.getAffiliation() != null) {
                existingStudent.setAffiliation(editedStudent.getAffiliation());
            }
            if (editedStudent.getBio() != null) {
                existingStudent.setBio(editedStudent.getBio());
            }
        }

        em.merge(existingUser);
    }
    public void editStudentAccount(Student editedStudent) {
        // Retrieve the existing student from the database
        Student existingStudent = em.find(Student.class, editedStudent.getId());

        // Check if the student exists
        if (existingStudent == null) {
            throw new EntityNotFoundException("Student not found with id " + editedStudent.getId());
        }

        // Update common fields only if they are not null in editedStudent
        if (editedStudent.getName() != null) {
            existingStudent.setName(editedStudent.getName());
        }
        if (editedStudent.getEmail() != null) {
            existingStudent.setEmail(editedStudent.getEmail());
        }
        if (editedStudent.getPassword() != null) {
            existingStudent.setPassword(editedStudent.getPassword());
        }

        // Update student-specific fields
        if (editedStudent.getAffiliation() != null) {
            existingStudent.setAffiliation(editedStudent.getAffiliation());
        }
        if (editedStudent.getBio() != null) {
            existingStudent.setBio(editedStudent.getBio());
        }

        // Merge the changes into the database
        em.merge(existingStudent);
    }

    public void editInstructorAccount(Instructor editedInstructor) {
        // Retrieve the existing instructor from the database
        Instructor existingInstructor = em.find(Instructor.class, editedInstructor.getId());

        // Check if the instructor exists
        if (existingInstructor == null) {
            throw new EntityNotFoundException("Instructor not found with id " + editedInstructor.getId());
        }

        // Update common fields only if they are not null in editedInstructor
        if (editedInstructor.getName() != null) {
            existingInstructor.setName(editedInstructor.getName());
        }
        if (editedInstructor.getEmail() != null) {
            existingInstructor.setEmail(editedInstructor.getEmail());
        }
        if (editedInstructor.getPassword() != null) {
            existingInstructor.setPassword(editedInstructor.getPassword());
        }

        // Update instructor-specific fields
        if (editedInstructor.getAffiliation() != null) {
            existingInstructor.setAffiliation(editedInstructor.getAffiliation());
        }
        if (editedInstructor.getBio() != null) {
            existingInstructor.setBio(editedInstructor.getBio());
        }
        if (editedInstructor.getYearsOfExperience() != null) {
            existingInstructor.setYearsOfExperience(editedInstructor.getYearsOfExperience());
        }

        // Merge the changes into the database
        em.merge(existingInstructor);
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
