package com.epam.project.mongodb.dao;

import com.epam.project.domain.User;
import com.epam.project.mongodb.converter.UserConverter;
import com.mongodb.*;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Created by StepLuch on 03.09.15.
 */

@Component
public class MongoDBUserDAO {

    private MongoClient mongo;
    private DBCollection col;

    public MongoDBUserDAO(MongoClient mongo) {
        this.mongo = mongo;
        DB db = mongo.getDB("PhotoUploderDB");
        col = db.getCollection("users");
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

    public JSONArray readAllPerson(String userId) throws JSONException {
        JSONArray data = new JSONArray();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            JSONObject p = UserConverter.toJSONUser(doc);
            data.put(p);
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

    public User readPersonByLogin(String login) {
        DBObject query = BasicDBObjectBuilder.start().append("login", login).get();
        DBObject data = col.findOne(query);
        return UserConverter.toUser(data);
    }

    public User readPersonById(String id) {
        DBObject query = BasicDBObjectBuilder.start().append("_id", new ObjectId(id)).get();
        DBObject data = col.findOne(query);
        return UserConverter.toUser(data);
    }
}
