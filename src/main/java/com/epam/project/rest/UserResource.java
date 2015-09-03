package com.epam.project.rest;


import com.epam.project.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user/{user_id}")
public class UserResource {

    @Autowired
    private UserService userService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getDefaultUserInJSON(@PathParam("user_id") String userId) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("userId", userId);
        return obj.toString();
    }
}
