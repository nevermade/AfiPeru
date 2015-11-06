package com.example.dp2.afiperu.syncmodel;

import com.example.dp2.afiperu.domain.Document;
import com.example.dp2.afiperu.domain.DocumentUser;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SyncDocument extends SugarRecord<SyncDocument> implements Serializable, Comparable<SyncDocument> {

    private Integer docId;
    private String name;
    private String uploadDate;
    private String filesize;
    private String url;
    @Ignore
    private ArrayList<DocumentUser> users;

    public SyncDocument() {
    }

    public SyncDocument(Integer docId, String name, String uploadDate, String filesize, String url) {
        this.docId = docId;
        this.name = name;
        this.uploadDate = uploadDate;
        this.filesize = filesize;
        this.url = url;
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

    @Override
    public int compareTo(SyncDocument o2){
        return docId.compareTo(o2.docId);
    }

    public static SyncDocument fromDocument(Document document){
        return new SyncDocument(document.getDocId(), document.getName(), document.getUploadDate(), document.getFilesize(),
                document.getUrl());
    }

    public static ArrayList<SyncDocument> fromDocument(List<Document> documents){
        ArrayList<SyncDocument> result = new ArrayList<>();
        for(Document document : documents){
            result.add(fromDocument(document));
        }
        return result;
    }
}
