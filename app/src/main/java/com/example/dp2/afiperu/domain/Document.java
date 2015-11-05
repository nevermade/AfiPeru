package com.example.dp2.afiperu.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Document implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
