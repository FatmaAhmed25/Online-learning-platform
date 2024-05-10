package com.example.onlinelearningplatform.rest;

import com.example.onlinelearningplatform.DTOs.LoginDTO;
import com.example.onlinelearningplatform.ejbs.AuthenticationEJB;
import com.example.onlinelearningplatform.ejbs.RegistrationEJB;
import com.example.onlinelearningplatform.entities.User;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationController {


    @EJB
    AuthenticationEJB authenticationEJB;

    @POST
    public Response login(LoginDTO loginDTO) {
        try {
            User user = authenticationEJB.login(loginDTO);
            if (user != null) {
                return Response.status(Response.Status.OK).entity(user).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid email or password").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error during login").build();
        }


    }
}
