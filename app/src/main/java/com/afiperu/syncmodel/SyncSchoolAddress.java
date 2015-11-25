package com.afiperu.syncmodel;

import com.afiperu.rest.model.School;
import com.afiperu.rest.model.Volunteer;
import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Fernando on 09/11/2015.
 */
public class SyncSchoolAddress extends SugarRecord<SyncSchoolAddress> implements Serializable {

    public static final int TYPE_SCHOOL = 0;
    public static final int TYPE_VOLUNTEER = 1;

    private String name;
    private Double latitude;
    private Double longitude;
    private Integer type;

    public SyncSchoolAddress() {
    }

    private SyncSchoolAddress(String name, Double latitude, Double longitude, Integer type) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Integer getType() {
        return type;
    }

    public static SyncSchoolAddress fromSchool(School school) {
        return new SyncSchoolAddress(school.getName(), school.getLatitude(), school.getLongitude(), TYPE_SCHOOL);
    }

    public static SyncSchoolAddress fromVolunteer(Volunteer volunteer){
        return new SyncSchoolAddress(volunteer.getNames() + " " + volunteer.getLastName(), volunteer.getLatitude(),
                volunteer.getLongitude(), TYPE_VOLUNTEER);
    }
}
