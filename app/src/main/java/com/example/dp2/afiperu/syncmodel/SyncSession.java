package com.example.dp2.afiperu.syncmodel;


import com.example.dp2.afiperu.domain.Document;
import com.example.dp2.afiperu.others.MarkerInfo;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SyncSession extends SugarRecord<SyncSession> implements Serializable, Comparable<SyncSession> {


    private Integer sessionId;

    private String name;

    private Integer date;

    private SyncLocation location;

    @Ignore
    private List<SyncPointOfReunion> pointsOfReunion = new ArrayList<SyncPointOfReunion>();
    @Ignore

    private List<Document> documents = new ArrayList<Document>();


    @Ignore
    private ArrayList<MarkerInfo> markers;

    public SyncSession(){};


    public SyncSession(String name, Integer date, ArrayList<MarkerInfo> markers) {
   
        this.name = name;
        this.date = date;
        this.markers = markers;
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

    public SyncLocation getLocation() {
        return location;
    }

    public void setLocation(SyncLocation location) {
        this.location = location;
    }

    public List<SyncPointOfReunion> getPointsOfReunion() {
        //return pointsOfReunion;
        return SyncPointOfReunion.find(SyncPointOfReunion.class, "session = ?", String.valueOf(this.getId()));
        //return Book.find(Book.class, "author = ?", new String{author.getId()})
    }

    public void setPointsOfReunion(List<SyncPointOfReunion> pointOfReunion) {
        this.pointsOfReunion = pointOfReunion;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public int compareTo(SyncSession o2){
        return Integer.valueOf(o2.date).compareTo(date);
    }


}
