package com.example.dp2.afiperu.domain;

import java.io.Serializable;

/**
 * Created by Nevermade on 30/09/2015.
 */
public class Blogs implements Serializable, Comparable<Blogs>{
    private String title;
    private String author;
    private long uploadDate;
    private boolean isFavorite =false;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Blogs(String title, String author, long uploadDate, boolean isFavorite) {
        this.title = title;
        this.uploadDate = uploadDate;
        this.author = author;
        this.isFavorite = isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(long uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public int compareTo(Blogs o2){
                return Long.valueOf(o2.uploadDate).compareTo(uploadDate);
    }
}
