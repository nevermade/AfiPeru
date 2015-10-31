package com.example.dp2.afiperu.rest.model;

import com.example.dp2.afiperu.domain.NewPointOfReunion;
import com.example.dp2.afiperu.domain.PointOfReunion;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Fernando on 30/10/2015.
 */
public class MeetingPointsBody {

    @SerializedName("session_id")
    @Expose
    private Integer sessionId;

    @SerializedName("meeting_points")
    @Expose
    private List<PointOfReunion> previousPoints;

    @SerializedName("new_meeting_points")
    @Expose
    private List<NewPointOfReunion> newPoints;

    public MeetingPointsBody() {
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public List<PointOfReunion> getPreviousPoints() {
        return previousPoints;
    }

    public void setPreviousPoints(List<PointOfReunion> previousPoints) {
        this.previousPoints = previousPoints;
    }

    public List<NewPointOfReunion> getNewPoints() {
        return newPoints;
    }

    public void setNewPoints(List<NewPointOfReunion> newPoints) {
        this.newPoints = newPoints;
    }
}
