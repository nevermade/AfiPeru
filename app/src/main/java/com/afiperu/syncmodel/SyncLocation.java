package com.afiperu.syncmodel;

/**
 * Created by Nevermade on 21/10/2015.
 */

import com.afiperu.domain.Location;
import com.orm.SugarRecord;

import java.io.Serializable;

public class SyncLocation extends SugarRecord<SyncLocation> implements Serializable{

    private Double latitude;
    private Double longitude;
    private Integer session;
    private String address;

    public SyncLocation(){}
    public SyncLocation(Double latitude, Double longitude, Integer session, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.session = session;
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public static SyncLocation fromLocation(Location location, Integer session){
        return new SyncLocation(location.getLatitude(), location.getLongitude(), session, location.getAddress());
    }
}