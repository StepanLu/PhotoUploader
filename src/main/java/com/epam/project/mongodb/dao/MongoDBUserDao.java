package com.epam.project.mongodb.dao;

import com.epam.project.domain.User;
import com.epam.project.mongodb.converter.UserConverter;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StepLuch on 03.09.15.
 */
public class MongoDBUserDAO {

    private DBCollection col;

    public MongoDBUserDAO(MongoClient mongo) {
        this.col = mongo.getDB("PhotoUploaderDB").getCollection("Users");
    }

    public User createPerson(User p) {
        DBObject doc = UserConverter.toDBObject(p);
        this.col.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        p.setId(id.toString());
        return p;
    }

    public void updatePerson(User p) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(p.getId())).get();
        this.col.update(query, UserConverter.toDBObject(p));
    }

    public List<User> readAllPerson() {
        List<User> data = new ArrayList<User>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            User p = UserConverter.toUser(doc);
            data.add(p);
        }
        return data;
    }

    public void deletePerson(User p) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(p.getId())).get();
        this.col.remove(query);
    }

    public User readPerson(User p) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(p.getId())).get();
        DBObject data = this.col.findOne(query);
        return UserConverter.toUser(data);
    }
}
