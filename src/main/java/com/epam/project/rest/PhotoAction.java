package com.epam.project.rest;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.*;


@Path("/upload")
public class PhotoAction {
    private static final String SERVER_UPLOAD_LOCATION_FOLDER = "E:/";
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadFile(
                @FormDataParam("file") InputStream fileInputStream,
                @FormDataParam("file") FormDataContentDisposition contentDispositionHeader
        ) {

            String filePath = SERVER_UPLOAD_LOCATION_FOLDER	+ contentDispositionHeader.getFileName();

            // save the file to the server
           saveFile(fileInputStream, filePath);

            String output = "File saved to server location : " + filePath;

            return "OK" +  output;

        }

        // save uploaded file to a defined location on the server
        private void saveFile(InputStream uploadedInputStream,
                String serverLocation) {

            try {
                OutputStream outputStream;
                int read = 0;
                byte[] bytes = new byte[1024];
                outputStream = new FileOutputStream(new File(serverLocation));
                while ((read = uploadedInputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
}
