package com.example.dp2.afiperu.syncmodel;

/**
 * Created by Nevermade on 21/10/2015.
 */
import com.example.dp2.afiperu.domain.PointOfReunion;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;


public class SyncPointOfReunion extends SugarRecord<SyncPointOfReunion>{

    private Integer pointId;
    private Double latitude;
    private Double longitude;
    private Integer selected;

    private Integer session;

    public SyncPointOfReunion(){}
    public SyncPointOfReunion(Integer pointId, Double latitude, Double longitude, Integer selected) {
        this.pointId = pointId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.selected = selected;
        this.session = 0;
    }

    public Integer getPointId() {
        return pointId;
    }

    public Integer getSelected() {
        return selected;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public static SyncPointOfReunion fromPointOfReunion(PointOfReunion pointOfReunion){
        return new SyncPointOfReunion(pointOfReunion.getId(), pointOfReunion.getLatitude(), pointOfReunion.getLongitude(),
                pointOfReunion.getSelected());
    }

    public PointOfReunion toPointOfReunion(){
        PointOfReunion result = new PointOfReunion();
        result.setId(pointId);
        result.setLatitude(latitude);
        result.setLongitude(longitude);
        result.setSelected(selected);
        return result;
    }

    public static List<PointOfReunion> toPointOfReunion(List<SyncPointOfReunion> points){
        ArrayList<PointOfReunion> result = new ArrayList<>();
        for(SyncPointOfReunion point : points){
            result.add(point.toPointOfReunion());
        }
        return result;
    }
}
