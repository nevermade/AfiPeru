package com.example.dp2.afiperu.rest.restModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nevermade on 23/10/2015.
 */
public class ActionResponse {
    @SerializedName("id")
    @Expose
    private Integer id;

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

}
