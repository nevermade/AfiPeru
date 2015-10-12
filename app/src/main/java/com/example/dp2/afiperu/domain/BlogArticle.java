package com.example.dp2.afiperu.domain;

import java.io.Serializable;

public class BlogArticle implements Serializable {
    private String title;
    private long uploadDate;
    private String content;
    private int authorIconId;
    private String authorIconURL;
    private String authorName;
    private String authorShortDesc;

    public BlogArticle(String title, long uploadDate, String content, int authorIconId, String authorIconURL,
                       String authorName, String authorShortDesc) {
        this.title = title;
        this.uploadDate = uploadDate;
        this.content = content;
        this.authorIconId = authorIconId;
        this.authorIconURL = authorIconURL;
        this.authorName = authorName;
        this.authorShortDesc = authorShortDesc;
    }

    public String getTitle() {
        return title;
    }

    public long getUploadDate() {
        return uploadDate;
    }

    public String getContent(){
        return content;
    }

    public int getAuthorIconId(){
        return authorIconId;
    }

    public String getAuthorIconURL(){
        return authorIconURL;
    }

    public String getAuthorName(){
        return authorName;
    }

    public String getAuthorShortDesc(){
        return authorShortDesc;
    }
}
