package com.example.onlinelearningplatform.ejbs;

import com.example.onlinelearningplatform.DTOs.LoginDTO;
import com.example.onlinelearningplatform.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless
public class AuthenticationEJB {

    EntityManager em;

    public AuthenticationEJB() {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
        em=emf.createEntityManager();
    }
    public User login(LoginDTO loginDTO) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
        query.setParameter("email", loginDTO.getEmail());
        query.setParameter("password", loginDTO.getPassword());
        List<User> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            return null;
        }
    }



}
