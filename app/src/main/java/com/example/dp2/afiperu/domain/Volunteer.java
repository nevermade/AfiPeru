package com.example.dp2.afiperu.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nevermade on 03/11/2015.
 */
public class Volunteer {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("names")
    @Expose
    private String names;
    @SerializedName("last_name")
    @Expose
    private String lastName;

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
     * The names
     */
    public String getNames() {
        return names;
    }

    /**
     *
     * @param names
     * The names
     */
    public void setNames(String names) {
        this.names = names;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
