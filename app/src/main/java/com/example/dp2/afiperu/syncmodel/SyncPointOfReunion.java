package com.example.dp2.afiperu.syncmodel;

/**
 * Created by Nevermade on 21/10/2015.
 */
import com.orm.SugarRecord;


public class SyncPointOfReunion extends SugarRecord<SyncPointOfReunion>{


    private Double latitude;

    private Double longitude;

    private SyncSession session;

    public SyncPointOfReunion(){};
    /**
     *
     * @return
     * The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public SyncSession getSession() {
        return session;
    }

    public void setSession(SyncSession session) {
        this.session = session;
    }
}
