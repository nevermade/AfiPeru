package com.example.dp2.afiperu.domain;

import com.example.dp2.afiperu.others.MarkerInfo;

import java.io.Serializable;

public class NewsArticle implements Serializable, Comparable<NewsArticle> {
    private int picId;
    private String picURL;
    private String title;
    private String authorName;
    private long uploadDate;
    private String content;
    private MarkerInfo marker;

    public NewsArticle(int picId, String picURL, String title, String authorName, long uploadDate, String content, MarkerInfo marker) {
        this.picId = picId;
        this.picURL = picURL;
        this.title = title;
        this.authorName = authorName;
        this.uploadDate = uploadDate;
        this.content = content;
        this.marker = marker;
    }

    public int getPicId(){
        return picId;
    }

    public String getPicURL(){
        return picURL;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName(){
        return authorName;
    }

    public long getUploadDate() {
        return uploadDate;
    }

    public String getContent(){
        return content;
    }

    public MarkerInfo getMarker(){
        return marker;
    }

    @Override
    public int compareTo(NewsArticle o2){
        return Long.valueOf(o2.uploadDate).compareTo(uploadDate);
    }
}
