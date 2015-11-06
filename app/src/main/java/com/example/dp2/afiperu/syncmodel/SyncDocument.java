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

    public void setName(String name) {
        this.name = name;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public String getFilesize() {
        return filesize;
    }

    public String getUrl() {
        return url;
    }

    public List<SyncDocumentUser> queryUsers(){
        return SyncDocumentUser.find(SyncDocumentUser.class, "document = ?", String.valueOf(getId()));
    }

    @Override
    public int compareTo(SyncDocument o2){
        return o2.docId.compareTo(docId);
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
