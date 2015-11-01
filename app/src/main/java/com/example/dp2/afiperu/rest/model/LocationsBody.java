package com.example.dp2.afiperu.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Fernando on 30/10/2015.
 */
public class LocationsBody {

    @SerializedName("schools")
    @Expose
    private List<School> schools;
    @SerializedName("volunteers")
    @Expose
    private List<Volunteer> volunteers;

    public LocationsBody() {
    }

    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }
}
