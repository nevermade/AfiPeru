package com.example.dp2.afiperu.rest.restModel;

/**
 * Created by Nevermade on 21/10/2015.
 */
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SessionResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("locationResponse")
    @Expose
    private LocationResponse locationResponse;
    @SerializedName("points_of_reunion")
    @Expose
    private List<PointsOfReunionResponse> pointsOfReunionResponse = new ArrayList<PointsOfReunionResponse>();
    @SerializedName("documentResponses")
    @Expose
    private List<DocumentResponse> documentResponses = new ArrayList<DocumentResponse>();

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
     * The locationResponse
     */
    public LocationResponse getLocationResponse() {
        return locationResponse;
    }

    /**
     *
     * @param locationResponse
     * The locationResponse
     */
    public void setLocationResponse(LocationResponse locationResponse) {
        this.locationResponse = locationResponse;
    }

    /**
     *
     * @return
     * The pointsOfReunionResponse
     */
    public List<PointsOfReunionResponse> getPointsOfReunionResponse() {
        return pointsOfReunionResponse;
    }

    /**
     *
     * @param pointsOfReunionResponse
     * The points_of_reunion
     */
    public void setPointsOfReunionResponse(List<PointsOfReunionResponse> pointsOfReunionResponse) {
        this.pointsOfReunionResponse = pointsOfReunionResponse;
    }

    /**
     *
     * @return
     * The documentResponses
     */
    public List<DocumentResponse> getDocumentResponses() {
        return documentResponses;
    }

    /**
     *
     * @param documentResponses
     * The documentResponses
     */
    public void setDocumentResponses(List<DocumentResponse> documentResponses) {
        this.documentResponses = documentResponses;
    }

}
