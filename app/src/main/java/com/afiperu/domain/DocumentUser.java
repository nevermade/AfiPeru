package com.afiperu.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Fernando on 03/11/2015.
 */
public class DocumentUser implements Serializable {

    public DocumentUser() {
    }

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("names")
    @Expose
    private String name;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("profiles")
    @Expose
    private ArrayList<Profile> profiles;
    @SerializedName("seen")
    @Expose
    private Integer seen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<Profile> profiles) {
        this.profiles = profiles;
    }

    public Integer getSeen() {
        return seen;
    }

    public void setSeen(Integer seen) {
        this.seen = seen;
    }
}
