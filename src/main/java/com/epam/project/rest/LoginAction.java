package com.epam.project.rest;

import com.epam.project.domain.User;
import com.epam.project.mongodb.dao.MongoDBUserDAO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by StepLuch on 01.09.15.
 */
@Service
@Path("/login")
public class LoginAction {

    @Autowired
    private MongoDBUserDAO mongoDBUserDAO;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public String login(@QueryParam("username") String userName, @QueryParam("password") String pass) throws JSONException {
        User user = null;
        user = mongoDBUserDAO.readPersonByLogin(userName);
        JSONObject jo = new JSONObject();
        if(user!=null && user.getPassword().equals(pass)){
            jo.put("logined",true);
            jo.put("user_id",user.getId());
            jo.put("username",user.getLogin());
        }else{
            jo.put("error","UNAUTHORIZED");
        }
        return jo.toString();
    }
}

