package com.epam.project.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by StepLuch on 01.09.15.
 */
@Path("user/{username}")
public class GreetingTest {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser(@PathParam("username") String username){
       return "Hello " + username;
    }
}
