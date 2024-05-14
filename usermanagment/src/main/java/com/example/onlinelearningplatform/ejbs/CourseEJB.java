//package com.example.onlinelearningplatform.ejbs;
//
//
//import com.example.onlinelearningplatform.DTOs.CourseDTO;
//
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//@Stateless
//public class CourseEJB {
//
////    EntityManager em;
////
////    public CourseEJB() {
////        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
////        em=emf.createEntityManager();
////    }
//    public CourseDTO addCourse(CourseDTO courseDTO, Long instructorId) {
//        System.out.println(courseDTO.toString());
//        Client client = ClientBuilder.newClient();
//        String url = "http://localhost:8081/courses/create?instructorId=" + instructorId; // URL of the Spring Boot endpoint to create a course
//        Response response = client.target(url)
//                .request(MediaType.APPLICATION_JSON)
//                .post(Entity.entity(courseDTO, MediaType.APPLICATION_JSON));
//        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
//            return response.readEntity(CourseDTO.class);
//        } else {
//            // Handle error response
//            return null;
//        }
//    }
//}
