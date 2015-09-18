package com.epam.project.domain;

import java.util.Date;

/**
 * Created by StepLuch on 03.09.15.
 */
public class Photo {

    private String id;
    private String fileName;
    private Date uploadDate;
    private String md5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public String toString() {
        return "Photo {" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", md5='" + md5 + '\'' +
                '}';
    }
}
