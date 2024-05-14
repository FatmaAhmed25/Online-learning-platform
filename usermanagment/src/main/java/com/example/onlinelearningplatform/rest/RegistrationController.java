package com.example.onlinelearningplatform.rest;

import com.example.onlinelearningplatform.DTOs.RegisterInstructorDTO;
import com.example.onlinelearningplatform.DTOs.RegisterStudentDTO;
import com.example.onlinelearningplatform.ejbs.RegistrationEJB;
import com.example.onlinelearningplatform.entities.Instructor;
import com.example.onlinelearningplatform.entities.Student;
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
    public Response registerStudent(RegisterStudentDTO student) {
        try {

            registrationEJB.registerStudent(student);
            return Response.status(Response.Status.CREATED).entity(student).build();
        }catch (javax.ejb.EJBException ejbEx) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Email already exists").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error registering student").build();
        }
    }
    @POST
    @Path("/instructor")
    public Response registerInstructor(RegisterInstructorDTO instructor) {
        try {

                registrationEJB.registerInstructor(instructor);
                return Response.status(Response.Status.CREATED).entity(instructor).build();
        }catch (javax.ejb.EJBException ejbEx) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Email already exists").build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error registering instructor").build();
        }
    }

    }




