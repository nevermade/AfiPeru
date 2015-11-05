package com.example.dp2.afiperu.domain;


import com.example.dp2.afiperu.rest.model.AttendanceChild;
import com.example.dp2.afiperu.rest.model.AttendanceVolunteer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.dsl.Ignore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Session implements Serializable, Comparable<Session> {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("meeting_points")
    @Expose
    private List<PointOfReunion> pointsOfReunion = new ArrayList<PointOfReunion>();
    @Ignore
    @SerializedName("documents")
    @Expose
    private ArrayList<Document> documents = new ArrayList<Document>();
    @SerializedName("attendance_children")
    @Expose
    private List<AttendanceChild> attendanceChildren = new ArrayList<AttendanceChild>();
    @SerializedName("attendance_volunteers")
    @Expose
    private List<AttendanceVolunteer> attendanceVolunteers = new ArrayList<AttendanceVolunteer>();
    public Session(){};


    public Session(String name, Integer date) {
   
        this.name = name;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<PointOfReunion> getPointsOfReunion() {
        return pointsOfReunion;
    }

    public void setPointsOfReunion(List<PointOfReunion> pointsOfReunion) {
        this.pointsOfReunion = pointsOfReunion;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }

    public List<AttendanceChild> getAttendanceChildren() {
        return attendanceChildren;
    }

    public void setAttendanceChildren(List<AttendanceChild> attendanceChildren) {
        this.attendanceChildren = attendanceChildren;
    }

    public List<AttendanceVolunteer> getAttendanceVolunteers() {
        return attendanceVolunteers;
    }

    public void setAttendanceVolunteers(List<AttendanceVolunteer> attendanceVolunteers) {
        this.attendanceVolunteers = attendanceVolunteers;
    }

    @Override
    public int compareTo(Session o2){
        return Integer.valueOf(o2.date).compareTo(date);
    }


}
