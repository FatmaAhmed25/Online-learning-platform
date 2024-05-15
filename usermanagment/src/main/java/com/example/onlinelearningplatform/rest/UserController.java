package com.example.onlinelearningplatform.rest;


import com.example.onlinelearningplatform.ejbs.UserEJB;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {


    @EJB
    private UserEJB userEJB;
    @GET
    @Path("/count")
    public Response getNumberOfUsers() {
        long noOfUsers=userEJB.getNumberOfUsers();
        return Response.ok(noOfUsers).build();

    }
}

