package com.example.onlinelearningplatform.rest;

import com.example.onlinelearningplatform.ejbs.RegistrationEJB;
import com.example.onlinelearningplatform.entities.User;
import com.example.onlinelearningplatform.entities.UserRole;


import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistrationController {


    @EJB
    RegistrationEJB registrationEJB;

    @POST
    @Path("/student")
    public Response registerStudent(User user) {
        try {

            registrationEJB.registerStudent(user.getName(), user.getEmail(), user.getPassword(), user.getAffiliation(), user.getBio());
            user.setRole(UserRole.STUDENT);
            return Response.status(Response.Status.CREATED).entity(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error registering user").build();
        }
    }
    @POST
    @Path("/instructor")
    public Response registerInstructor(User user) {
        try {

                registrationEJB.registerInstructor(user.getName(), user.getEmail(), user.getPassword(), user.getAffiliation(), user.getBio(), user.getYearsOfExperience());
                user.setRole(UserRole.INSTRUCTOR);
                return Response.status(Response.Status.CREATED).entity(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error registering user").build();
        }
    }

    }




