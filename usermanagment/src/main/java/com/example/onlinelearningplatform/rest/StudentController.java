package com.example.onlinelearningplatform.rest;

import com.example.onlinelearningplatform.customExceptions.StudentNotFoundException;
import com.example.onlinelearningplatform.ejbs.StudentEJB;
import com.example.onlinelearningplatform.entities.Student;
import com.example.onlinelearningplatform.entities.User;

import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentController {
    @EJB
    private StudentEJB studentEJB;
    @GET
    @Path("/{studentId}")
    public Response getStudentById(@PathParam("studentId") long studentId) {
            User student = studentEJB.getStudentById(studentId);
            if(student==null)
                return Response.status(Response.Status.NOT_FOUND).entity("student not found").build();
            return Response.ok(student).build();

    }
}
