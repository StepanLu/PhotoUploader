package com.epam.project.mongodb.converter;

import com.epam.project.domain.User;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by StepLuch on 03.09.15.
 */
public class UserConverter {
    public static DBObject toDBObject(User user){
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("firstName", user.getFirstName())
                .append("lastName", user.getLastName())
                .append("email", user.getEmail())
                .append("password",user.getPassword())
                .append("role",user.getRole())
                .append("login", user.getLogin());
        if (user.getId() != null)
            builder = builder.append("_id", new ObjectId(user.getId()));
        return builder.get();
    }

    public static User toUser(DBObject doc) {
        User user = new User();
        user.setFirstName((String) doc.get("firstName"));
        user.setLastName((String) doc.get("lastName"));
        user.setRole((String) doc.get("role"));
        user.setEmail((String) doc.get("email"));
        user.setLogin((String) doc.get("login"));
        user.setPassword((String) doc.get("password"));
        ObjectId id = (ObjectId) doc.get("_id");
        user.setId(id.toString());
        return user;
    }

    public static JSONObject toJSONUser(DBObject doc) throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("firstName", (String) doc.get("firstName"));
        jo.put("lastName", (String) doc.get("lastName"));
        jo.put("role", (String) doc.get("role"));
        jo.put("email", (String) doc.get("email"));
        jo.put("login", (String) doc.get("login"));
        ObjectId id = (ObjectId) doc.get("_id");
        jo.put("_id", id.toString());
        return jo;
    }
}
