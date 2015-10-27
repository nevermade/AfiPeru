package com.example.dp2.afiperu.domain;


import com.example.dp2.afiperu.domain.Document;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("points_of_reunion")
    @Expose
    private List<PointsOfReunion> pointsOfReunion = new ArrayList<PointsOfReunion>();
    @SerializedName("documents")
    @Expose
    private List<Document> documents = new ArrayList<Document>();



    private ArrayList<MarkerInfo> markers;

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
