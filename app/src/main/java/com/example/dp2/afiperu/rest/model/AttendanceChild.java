package com.example.dp2.afiperu.rest.model;

import com.example.dp2.afiperu.domain.Comment;
import com.example.dp2.afiperu.domain.Kid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nevermade on 03/11/2015.
 */
public class AttendanceChild {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("child")
    @Expose
    private Kid child;
    @SerializedName("commented")
    @Expose
    private Integer commented;
    @SerializedName("comments")
    @Expose
    private List<Comment> comments = new ArrayList<Comment>();

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
    public List<Comment> getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     * The comments
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
