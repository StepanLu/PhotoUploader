package com.epam.project.mongodb.converter;

import com.epam.project.domain.User;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

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
        ObjectId id = (ObjectId) doc.get("_id");
        user.setId(id.toString());
        return user;
    }
}
