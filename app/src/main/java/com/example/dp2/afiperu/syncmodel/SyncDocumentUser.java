package com.example.dp2.afiperu.syncmodel;

import com.example.dp2.afiperu.domain.DocumentUser;
import com.example.dp2.afiperu.domain.Profile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Fernando on 03/11/2015.
 */
public class SyncDocumentUser extends SugarRecord<SyncDocumentUser> implements Serializable, Comparable<SyncDocumentUser> {

    public SyncDocumentUser() {
    }

    public SyncDocumentUser(int userId, String name, String lastName, String username, Integer seen) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.seen = seen;
    }

    private int userId;
    private String name;
    private String lastName;
    private String username;
    private Integer seen;
    private SyncDocument document;

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public Integer getSeen() {
        return seen;
    }

    public void setSeen(Integer seen) {
        this.seen = seen;
    }

    public void setDocument(SyncDocument document) {
        this.document = document;
    }

    @Override
    public int compareTo(SyncDocumentUser o2) {
        return name.compareTo(o2.name);
    }
}
