package com.epam.project.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

/**
 * Created by StepLuch on 01.09.15.
 */
@Path("/")
public class LoginAction {
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response sayHtmlHello() throws URISyntaxException {
        java.net.URI location = new java.net.URI("/index.jsp");
        return null;
    }
}

