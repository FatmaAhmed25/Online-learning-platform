package com.example.onlinelearningplatform.rest;


import com.example.onlinelearningplatform.ejbs.AdminEJB;
import com.example.onlinelearningplatform.entities.Instructor;
import com.example.onlinelearningplatform.entities.Student;
import com.example.onlinelearningplatform.entities.User;

import javax.ejb.EJB;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminController {

    @EJB
    AdminEJB adminEJB;
    @GET
    @Path("/validate")
    public Response getAdminById(@QueryParam("id") long adminId) {
        User admin= adminEJB.getAdminById(adminId);
        if(admin==null)
            return Response.status(Response.Status.NOT_FOUND).entity("admin not found").build();
        return Response.ok(admin).build();

    }
    @PUT
    @Path("/students")
    public Response editStudent(Student editedStudent) {
        adminEJB.editStudentAccount(editedStudent);
        return Response.ok("Student account updated successfully").build();
    }

    @PUT
    @Path("/instructors")
    @Consumes("application/json")
    @Produces("text/plain")
    public Response editInstructor(Instructor editedInstructor) {
        adminEJB.editInstructorAccount(editedInstructor);
        return Response.ok("Instructor account updated successfully").build();
    }


    @GET
    @Path("/users")
    public Response getAllUsers() {
        List<User> users = adminEJB.getAllUserAccounts();
        return Response.ok(users).build();
    }

    @GET
    @Path("/instructors")
    public Response getAllInstructors() {
        List<Instructor> instructors = adminEJB.getAllInstructorAccounts();
        return Response.ok(instructors).build();
    }

    @GET
    @Path("/students")
    public Response getAllStudents() {
        List<Student> students = adminEJB.getAllStudentAccounts();
        return Response.ok(students).build();
    }

    @DELETE
    @Path("/user/{userId}")
    public Response deleteUser(@PathParam("userId") Long userId) {
        adminEJB.deleteUserAccount(userId);
        return Response.ok().build();
    }

    @PUT
    @Path("/edituser")
    public Response editUser(User editedUser) {
        try {
            adminEJB.editUserAccount(editedUser);
            return Response.ok().build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
