package com.example.dp2.afiperu.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nevermade on 30/09/2015.
 */
public class Comment implements Serializable, Comparable<Comment>{
    @SerializedName("id")
    @Expose
    private Integer iconId;
    private String name;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("face")
    @Expose
    private Integer face;
    @SerializedName("volunteer")
    @Expose
    private Volunteer volunteer;
    //private String gender;


    public Comment(String name, String comment) {
        this.name = name;
        this.comment = comment;
        //  this.gender = gender;
//        this.iconId = iconId;
    }

    public String getName() { return name; }

    public String getComment() {
        return comment;
    }

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getFace() {
        return face;
    }

    public void setFace(Integer face) {
        this.face = face;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    @Override
    public int compareTo(Comment another) {
        return Long.valueOf(another.name).compareTo(Long.valueOf(name));
    }
/**/
}
