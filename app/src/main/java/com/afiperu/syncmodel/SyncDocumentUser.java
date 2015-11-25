package com.afiperu.syncmodel;

import com.afiperu.domain.DocumentUser;
import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Fernando on 03/11/2015.
 */
public class SyncDocumentUser extends SugarRecord<SyncDocumentUser> implements Serializable, Comparable<SyncDocumentUser> {

    public SyncDocumentUser(){}
    private SyncDocumentUser(String names, String username, Integer seen) {
        this.names = names;
        this.username = username;
        this.seen = seen;
        this.session = 0;
    }

    private Integer session;
    private SyncDocument document;

    private String names;
    private String username;
    private Integer seen;

    public String getNames() {
        return names;
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

    public void setSession(Integer session) {
        this.session = session;
    }

    public void setDocument(SyncDocument document) {
        this.document = document;
    }

    @Override
    public int compareTo(SyncDocumentUser o2) {
        return names.compareTo(o2.names);
    }

    public static SyncDocumentUser fromDocumentUser(DocumentUser user){
        return new SyncDocumentUser(user.getName() + " " + user.getLastName(), user.getUsername(), user.getSeen());
    }
}
