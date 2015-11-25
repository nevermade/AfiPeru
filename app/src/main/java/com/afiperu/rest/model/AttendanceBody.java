package com.afiperu.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando on 30/10/2015.
 */
public class AttendanceBody {

    @SerializedName("session_id")
    @Expose
    private Integer sessionId;
    @SerializedName("volunteers")
    @Expose
    private List<RestVolunteer> volunteers = new ArrayList<RestVolunteer>();

    /**
     *
     * @return
     * The sessionId
     */
    public Integer getSessionId() {
        return sessionId;
    }

    /**
     *
     * @param sessionId
     * The session_id
     */
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    /**
     *
     * @return
     * The volunteers
     */
    public List<RestVolunteer> getVolunteers() {
        return volunteers;
    }

    /**
     *
     * @param volunteers
     * The volunteers
     */
    public void setVolunteers(List<RestVolunteer> volunteers) {
        this.volunteers = volunteers;
    }
}
