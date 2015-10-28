package com.example.dp2.afiperu.domain;

/**
 * Created by Nevermade on 21/10/2015.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


public class PointsOfReunion{

    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;


    public PointsOfReunion(){};
    /**
     *
     * @return
     * The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
