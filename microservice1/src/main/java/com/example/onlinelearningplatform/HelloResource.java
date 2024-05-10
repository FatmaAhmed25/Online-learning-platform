package com.example.onlinelearningplatform;

import com.example.onlinelearningplatform.ejbs.Test;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/hello-world")
public class HelloResource {
//    @GET
//    @Produces("text/plain")
//    public String hello() {
//        return "Hello, World!";
//    }
@EJB
Test testEJB;

    @GET
    public Response testConnection() {
        String result = testEJB.testConnection();
        return Response.ok(result).build();
    }
    @GET
    @Path("/insert-data")
    public Response insertData() {
        testEJB.insertTestData();
        return Response.ok("Test data inserted successfully").build();
    }
}