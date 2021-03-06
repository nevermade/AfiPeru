package com.afiperu.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Document implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer docId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("upload_date")
    @Expose
    private String uploadDate;
    @SerializedName("size")
    @Expose
    private String filesize;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("users")
    @Expose
    private ArrayList<DocumentUser> users;

    public Document() {
    }

    public String getName() {
        return name;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<DocumentUser> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<DocumentUser> users) {
        this.users = users;
    }
}
