package com.example.dp2.afiperu.domain;

import java.io.Serializable;

public class Document implements Serializable, Comparable<Document> {
    private String name;
    private int iconId;
    private String filesize;
    private long uploadDate;

    public Document(String name, int iconId, String filesize, long uploadDate) {
        this.name = name;
        this.iconId = iconId;
        this.filesize = filesize;
        this.uploadDate = uploadDate;
    }

    public String getName() {
        return name;
    }

    public int getIconId() {
        return iconId;
    }

    public String getFilesize(){
        return filesize;
    }

    public long getUploadDate() {
        return uploadDate;
    }

    @Override
    public int compareTo(Document o2){
        return Long.valueOf(o2.uploadDate).compareTo(uploadDate);
    }
}
