package com.afiperu.syncmodel;

import com.afiperu.domain.Kid;
import com.orm.SugarRecord;

import java.io.Serializable;

public class SyncKid extends SugarRecord<SyncKid> implements Serializable, Comparable<SyncKid> {

    private Integer attendanceChild;

    private Integer kidId;
    private String names;
    private Integer age;
    private Integer gender;
    private Integer sessions;

    public SyncKid(){}
    private SyncKid(Integer kidId, String names, Integer age, Integer gender, Integer sessions) {
        this.kidId = kidId;
        this.names = names;
        this.age = age;
        this.gender = gender;
        this.sessions = sessions;
        this.attendanceChild = 0;
    }

    public Integer getKidId() {
        return kidId;
    }

    public String getNames() {
        return names;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getGender() {
        return gender;
    }

    public Integer getSessions() {
        return sessions;
    }

    public void setAttendanceChild(Integer attendanceChild) {
        this.attendanceChild = attendanceChild;
    }

    @Override
    public int compareTo(SyncKid o2){
        return names.compareTo(o2.names);
    }

    public static SyncKid fromKid(Kid kid){
        return new SyncKid(kid.getId(), kid.getNames() + " " + kid.getLastName(), kid.getAge(), kid.getGender(),
                kid.getSessions());
    }
}
