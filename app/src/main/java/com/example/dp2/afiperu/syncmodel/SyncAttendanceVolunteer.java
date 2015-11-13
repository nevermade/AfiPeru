package com.example.dp2.afiperu.syncmodel;

import com.example.dp2.afiperu.rest.model.AttendanceVolunteer;
import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Fernando on 12/11/2015.
 */
public class SyncAttendanceVolunteer extends SugarRecord<SyncAttendanceVolunteer>
        implements Serializable, Comparable<SyncAttendanceVolunteer> {
    private Integer session;

    private Integer volunteerId;
    private String names;
    private Integer attended;
    private Integer rating;
    private String comment;

    private Integer needsync;

    public SyncAttendanceVolunteer() {
    }

    private SyncAttendanceVolunteer(Integer volunteerId, String names, Integer attended, Integer rating, String comment) {
        this.volunteerId = volunteerId;
        this.names = names;
        this.attended = attended;
        this.rating = rating;
        this.comment = comment;
        this.needsync = 0;
    }

    public Integer getVolunteerId() {
        return volunteerId;
    }

    public String getNames() {
        return names;
    }

    public Integer getAttended() {
        return attended;
    }

    public void setAttended(Integer attended) {
        this.attended = attended;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public void setNeedsync(Integer needsync) {
        this.needsync = needsync;
    }

    public static SyncAttendanceVolunteer fromAttendanceVolunteer(AttendanceVolunteer volunteer){
        return new SyncAttendanceVolunteer(volunteer.getVolunteer().getId(),
                volunteer.getVolunteer().getNames() + " " + volunteer.getVolunteer().getLastName(),
                volunteer.getAttended() ? 1 : 0, volunteer.getRating(), volunteer.getComment());
    }

    @Override
    public int compareTo(SyncAttendanceVolunteer o2){
        return 0;
    }
}
