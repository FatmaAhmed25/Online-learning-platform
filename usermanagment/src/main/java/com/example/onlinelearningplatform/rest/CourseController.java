//package com.example.onlinelearningplatform.rest;
//
//import com.example.onlinelearningplatform.DTOs.CourseDTO;
//import com.example.onlinelearningplatform.ejbs.CourseEJB;
//
//import javax.ejb.EJB;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//@Path("/courses")
//public class CourseController {
//
//    @EJB
//    private CourseEJB courseEJB;
//
//    @POST
//    @Path("/create")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createCourse(CourseDTO courseDTO, @QueryParam("instructorId") Long instructorId) {
//        System.out.println(courseDTO.toString());
//        System.out.println(instructorId);
//        CourseDTO createdCourse = courseEJB.addCourse(courseDTO, instructorId);
//        if (createdCourse != null) {
//            return Response.status(Response.Status.CREATED).entity(createdCourse).build();
//        } else {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//}
