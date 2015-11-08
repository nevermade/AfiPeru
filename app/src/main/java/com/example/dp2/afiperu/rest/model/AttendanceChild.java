package com.example.dp2.afiperu.rest.model;

import com.example.dp2.afiperu.domain.Comment;
import com.example.dp2.afiperu.domain.Kid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nevermade on 03/11/2015.
 */
public class AttendanceChild implements Serializable, Comparable<AttendanceChild> {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("child")
    @Expose
    private Kid child;
    @SerializedName("commented")
    @Expose
    private Integer commented;
    @SerializedName("comment")
    @Expose
    private Comment comment;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The child
     */
    public Kid getChild() {
        return child;
    }

    /**
     *
     * @param child
     * The child
     */
    public void setChild(Kid child) {
        this.child = child;
    }

    /**
     *
     * @return
     * The commented
     */
    public Integer getCommented() {
        return commented;
    }

    /**
     *
     * @param commented
     * The commented
     */
    public void setCommented(Integer commented) {
        this.commented = commented;
    }

    /**
     *
     * @return
     * The comments
     */
    public Comment getComment() {
        return comment;
    }

    /**
     *
     * @param comment
     * The comments
     */
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public int compareTo(AttendanceChild another) {
        return child.compareTo(another.child);
    }
}
