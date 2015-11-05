package com.example.dp2.afiperu.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando on 30/10/2015.
 */
public class RestVolunteer {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("attended")
    @Expose
    private Integer attended;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("comment")
    @Expose
    private String comment;

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
     * The attended
     */
    public Integer getAttended() {
        return attended;
    }

    /**
     *
     * @param attended
     * The attended
     */
    public void setAttended(Integer attended) {
        this.attended = attended;
    }

    /**
     *
     * @return
     * The rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     * The rating
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     * The comment
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * @param comment
     * The comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }


}
