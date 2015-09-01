package com.epam.project.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Stepan on 30.08.2015.
 */
@Path("/bye")
public class ByeWorld {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Bye Jersey";
    }
}
