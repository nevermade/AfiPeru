package com.example.dp2.afiperu.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fernando on 16/11/2015.
 */
public class SuccessBody {

    @SerializedName("success")
    @Expose
    private Integer success;

    public SuccessBody() {
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
