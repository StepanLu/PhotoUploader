package com.epam.project.rest;


import com.epam.project.domain.User;
import com.epam.project.mongodb.dao.MongoDBUserDAO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/user")
public class UserResource {

    @Autowired
    private MongoDBUserDAO mongoDBUserDAO;

    @GET
    @Path("/{user_id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getDefaultUserInJSON(@PathParam("user_id") String userId) throws JSONException {
        User user = null;
        user = mongoDBUserDAO.readPersonById(userId);
        JSONObject jo = new JSONObject();
        jo.put("firstName", user.getFirstName());
        jo.put("lastName", user.getLastName());
        jo.put("userEmail", user.getEmail());
        jo.put("userRole", user.getRole());
        jo.put("userLogin", user.getLogin());
        return jo.toString();
    }

    @GET
    @Path("/userList")
    @Produces({MediaType.APPLICATION_JSON})
    public String getUserList(@QueryParam("user_id") String userId) throws JSONException {
        JSONArray userList = mongoDBUserDAO.readAllPerson(userId);
        return userList.toString();
    }
}
