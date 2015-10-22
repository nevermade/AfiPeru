package com.example.dp2.afiperu.rest.restModel;

/**
 * Created by Nevermade on 21/10/2015.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DocumentResponse {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("size")
    @Expose
    private Double size;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The date
     */
    public Integer getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The size
     */
    public Double getSize() {
        return size;
    }

    /**
     *
     * @param size
     * The size
     */
    public void setSize(Double size) {
        this.size = size;
    }

}