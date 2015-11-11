package com.example.dp2.afiperu.syncmodel;

import com.example.dp2.afiperu.domain.Document;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.List;

public class SyncDocument extends SugarRecord<SyncDocument> implements Serializable, Comparable<SyncDocument> {

    private Integer session;

    private Integer docId;
    private String name;
    private String uploadDate;
    private String filesize;
    private String url;

    private String lastUri;

    public SyncDocument(){}
    public SyncDocument(Integer docId, String name, String uploadDate, String filesize, String url) {
        this.docId = docId;
        this.name = name;
        this.uploadDate = uploadDate;
        this.filesize = filesize;
        this.url = url;
        this.session = 0;
    }

    public String getName() {
        return name;
    }

    public Integer getDocId() {
        return docId;
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

    public void setSession(Integer session) {
        this.session = session;
    }

    public String getLastUri() {
        return lastUri;
    }

    public void setLastUri(String lastUri) {
        this.lastUri = lastUri;
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
}
