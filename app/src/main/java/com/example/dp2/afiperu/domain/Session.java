package com.example.dp2.afiperu.domain;


import com.example.dp2.afiperu.domain.Document;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Session extends SugarRecord<Session> implements Serializable, Comparable<Session> {

    @SerializedName("id")
    @Expose
    private Integer sessionId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("points_of_reunion")
    @Expose
    private List<PointsOfReunion> pointsOfReunion = new ArrayList<PointsOfReunion>();
    @Ignore
    @SerializedName("documents")
    @Expose
    private List<Document> documents = new ArrayList<Document>();


    @Ignore
    private ArrayList<MarkerInfo> markers;

    public Session(){};


    public Session(String name, Integer date, ArrayList<MarkerInfo> markers) {
   
        this.name = name;
        this.date = date;
        this.markers = markers;
    }



    public ArrayList<MarkerInfo> getMarkers() {
        ArrayList result = new ArrayList<>(markers.size());
        for(MarkerInfo marker : markers){
            result.add(marker.clone());
        }
        return result;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
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

    public List<PointsOfReunion> getPointsOfReunion() {
        return pointsOfReunion;
    }

    public void setPointsOfReunion(List<PointsOfReunion> pointsOfReunion) {
        this.pointsOfReunion = pointsOfReunion;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public int compareTo(Session o2){
        return Integer.valueOf(o2.date).compareTo(date);
    }


}
