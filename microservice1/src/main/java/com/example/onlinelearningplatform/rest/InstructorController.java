package com.example.onlinelearningplatform.rest;

import com.example.onlinelearningplatform.customExceptions.StudentNotFoundException;
import com.example.onlinelearningplatform.ejbs.InstructorEJB;
import com.example.onlinelearningplatform.ejbs.StudentEJB;
import com.example.onlinelearningplatform.entities.Instructor;
import com.example.onlinelearningplatform.entities.Student;
import com.example.onlinelearningplatform.entities.User;

import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/instructor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstructorController {
    @EJB
    private InstructorEJB instructorEJB;
    @GET
    @Path("/{instructorId}")
    public Response getStudentById(@PathParam("instructorId") long instructorId) {
        User instructor = instructorEJB.getInstructorById(instructorId);
        if(instructor==null)
            return Response.status(Response.Status.NOT_FOUND).entity("instructor not found").build();
        return Response.ok(instructor).build();

    }
}
