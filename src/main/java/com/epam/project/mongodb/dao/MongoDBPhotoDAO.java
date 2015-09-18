package com.epam.project.mongodb.dao;

import com.epam.project.domain.Photo;
import com.epam.project.mongodb.converter.PhotoConverter;
import com.mongodb.*;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StepLuch on 14.09.15.
 */

@Component
public class MongoDBPhotoDAO {

    private DBCollection col;
    private MongoClient mongoClient;

    public MongoDBPhotoDAO(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        DB db = mongoClient.getDB("PhotoUploderDB");
        col = db.getCollection("users");
    }

    public List<Photo> readAllPhotos(String userId) {
        List<Photo> data = new ArrayList<Photo>();
        DBObject query = BasicDBObjectBuilder.start().append("_id", new ObjectId(userId)).get();
        DBCursor cursor = col.find(query,new BasicDBObject("photos",1));
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            for(BasicDBObject obj: (ArrayList<BasicDBObject>)doc.get("photos")){
                Photo p = PhotoConverter.toPhoto(obj);
                data.add(p);
            }
        }
        return data;
    }

}
