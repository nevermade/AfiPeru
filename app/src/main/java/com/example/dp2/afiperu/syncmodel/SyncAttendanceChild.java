package com.example.dp2.afiperu.syncmodel;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Fernando on 08/11/2015.
 */
public class SyncAttendanceChild extends SugarRecord<SyncAttendanceChild> implements Serializable, Comparable<SyncAttendanceChild> {

    private Integer session;

    private Integer attendanceChildId;
    private SyncKid kid;
    private SyncComment comment;

    public SyncAttendanceChild(){}
    public SyncAttendanceChild(Integer attendanceChildId, SyncKid kid, SyncComment comment){
        this.attendanceChildId = attendanceChildId;
        this.kid = kid;
        this.comment = comment;
        this.session = 0;
    }

    public Integer getAttendanceChildId() {
        return attendanceChildId;
    }

    public SyncKid getKid() {
        return kid;
    }

    public SyncComment getComment() {
        return comment;
    }

    public void setComment(SyncComment comment) {
        this.comment = comment;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    @Override
    public int compareTo(SyncAttendanceChild o2){
        return kid.getNames().compareTo(o2.kid.getNames());
    }
}
