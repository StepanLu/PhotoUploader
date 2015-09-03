package com.epam.project.rest;

import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by StepLuch on 01.09.15.
 */
@Path("/login")
public class LoginAction {
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public String login(@QueryParam("username") String userName, @QueryParam("password") String pass) throws JSONException {
        //java.net.URI location = new java.net.URI("/index.jsp");
        JSONObject jo = new JSONObject();
        jo.put("logined",true);
        jo.put("user_id",1);
        jo.put("username",userName);
        jo.put("password",pass);
        return jo.toString();
    }
}

