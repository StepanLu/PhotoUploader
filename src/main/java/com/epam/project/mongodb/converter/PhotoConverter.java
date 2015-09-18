package com.epam.project.mongodb.converter;

import com.epam.project.domain.Photo;
import com.mongodb.DBObject;

import java.util.Date;

/**
 * Created by StepLuch on 14.09.15.
 */
public class PhotoConverter {

    public static Photo toPhoto(DBObject doc) {
        Photo photo = new Photo();
        photo.setFileName((String) doc.get("fileName"));
        photo.setMd5((String) doc.get("md5"));
        photo.setUploadDate((Date) doc.get("uploadDate"));
        String id = (String)doc.get("photo_id");
        photo.setId(id);
        return photo;
    }
}
