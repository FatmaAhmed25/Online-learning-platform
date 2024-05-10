package com.example.onlinelearningplatform.rest;

import com.example.onlinelearningplatform.ejbs.AdminEJB;
import com.example.onlinelearningplatform.entities.Instructor;
import com.example.onlinelearningplatform.entities.Student;
import com.example.onlinelearningplatform.entities.User;

import javax.ejb.EJB;
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
    @Path("/user")
    public Response editUser(User editedUser) {
        adminEJB.editUserAccount(editedUser);
        return Response.ok().build();
    }
}
