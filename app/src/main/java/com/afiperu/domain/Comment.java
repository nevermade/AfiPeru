package com.afiperu.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nevermade on 30/09/2015.
 */
public class Comment implements Serializable, Comparable<Comment>{
    @SerializedName("id")
    @Expose
    private Integer commentId;
    @SerializedName("session_id")
    @Expose
    private Integer sessionId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("face")
    @Expose
    private Integer face;
    @SerializedName("author")
    @Expose
    private User author;

    public Comment() {
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getFace() {
        return face;
    }

    public void setFace(Integer face) {
        this.face = face;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public int compareTo(Comment o2){
        return o2.sessionId.compareTo(sessionId);
    }
}
