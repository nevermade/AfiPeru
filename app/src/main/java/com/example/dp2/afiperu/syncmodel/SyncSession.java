package com.example.dp2.afiperu.syncmodel;

import com.example.dp2.afiperu.domain.Session;
import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.List;

public class SyncSession extends SugarRecord<SyncSession> implements Serializable, Comparable<SyncSession> {

    private Integer sessionId;
    private String name;
    private Long date;
    private SyncLocation location;

    public SyncSession(){}
    public SyncSession(Integer sessionId, String name, Long date) {
        this.sessionId = sessionId;
        this.name = name;
        this.date = date;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public String getName() {
        return name;
    }

    public Long getDate() {
        return date;
    }

    public SyncLocation getLocation() {
        return location;
    }

    public void setLocation(SyncLocation location) {
        this.location = location;
    }

    public List<SyncPointOfReunion> queryPointsOfReunion() {
        return SyncPointOfReunion.find(SyncPointOfReunion.class, "session = ?", String.valueOf(this.getSessionId()));
    }

    public List<SyncDocument> queryDocuments(){
        return SyncDocument.find(SyncDocument.class, "session = ?", String.valueOf(this.getSessionId()));
    }

    public List<SyncAttendanceChild> queryAttendanceChildren(){
        return SyncAttendanceChild.find(SyncAttendanceChild.class, "session = ?", String.valueOf(this.getSessionId()));
    }

    @Override
    public int compareTo(SyncSession o2){
        return o2.date.compareTo(date);
    }

    public static SyncSession fromSession(Session session){
        SyncSession result = new SyncSession(session.getId(), session.getName(), session.getDate());
        return result;
    }

}
