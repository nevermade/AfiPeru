package com.example.dp2.afiperu.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Document {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("upload_date")
    @Expose
    private String uploadDate;
    @SerializedName("size")
    @Expose
    private Double filesize;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("users")
    @Expose
    private List<DocumentUser> users;

    public Document() {
    }

    public String getName() {
        return name;
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

    public Double getFilesize() {
        return filesize;
    }

    public void setFilesize(Double filesize) {
        this.filesize = filesize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<DocumentUser> getUsers() {
        return users;
    }

    public void setUsers(List<DocumentUser> users) {
        this.users = users;
    }
}
