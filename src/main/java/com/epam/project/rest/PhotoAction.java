package com.epam.project.rest;

import com.epam.project.domain.Photo;
import com.epam.project.mongodb.dao.MongoDBPhotoDAO;
import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Component
@Path("/photo")
public class PhotoAction {

    @Autowired
    private MongoDBPhotoDAO mongoDBPhotoDAO;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadFile(
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader,
            @FormDataParam("user_id") String userId
    ) {
        String fileName = contentDispositionHeader.getFileName();
        String writeResult = saveFile(fileInputStream, fileName, userId);
        String output = "File saved to server location: " + fileName + " user_id: " + userId + " responce: " + writeResult;
        return "OK" + output;
    }


    @GET
    @Path("/imageList")
    @Produces({MediaType.APPLICATION_JSON})
    public String getFolderImages(@QueryParam("user_id") String user_id) throws JSONException {
        List<Photo> photoList = mongoDBPhotoDAO.readAllPhotos(user_id);
        JSONArray array = new JSONArray();
        for(Photo photo: photoList){
            JSONObject obj = new JSONObject();
            obj.put("fileName", photo.getFileName());
            obj.put("photo_id", photo.getId());
            obj.put("md5", photo.getMd5());
            array.put(obj);
        }
        //return Response.status(Response.Status.OK).entity(new Gson().toJson(newfiles)).type("text/json").build();
        return array.toString();
    }


    @GET
    @Path("/{photoId : [0-9a-z]+}")
    @Produces("image/png")
    public Response getImage(@PathParam("photoId") String photoId) throws IOException {
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("PhotoUploderDB");
        GridFS gfsPhoto = new GridFS(db, "users");
        GridFSDBFile imageForOutput = gfsPhoto.findOne(new ObjectId(photoId));

        BufferedImage image = ImageIO.read(imageForOutput.getInputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageData = baos.toByteArray();

        return Response.ok(imageData).build();
    }


    private String saveFile(InputStream uploadedInputStream,
                            String fileName, String userId) {
        int read = 0;
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("PhotoUploderDB");
        DBCollection collection = db.getCollection("users");
        BasicDBObject info = new BasicDBObject();
        info.put("name", fileName);
        info.put("userId", userId);
        info.put("fileName", fileName);

        GridFS gridfs = new GridFS(db, "users");
        GridFSInputFile gfsFile = gridfs.createFile(uploadedInputStream);
        gfsFile.setFilename(fileName);
        gfsFile.setMetaData(info);
        gfsFile.save();
        info.put("photo_id", gfsFile.getId().toString());
        WriteResult responce = collection.update(new BasicDBObject("_id", new ObjectId(userId)), new BasicDBObject("$push", new BasicDBObject("photos", info)));
        return responce.toString() + "; " + info.toString();
    }

}


