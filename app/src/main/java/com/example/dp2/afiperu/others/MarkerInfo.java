package com.example.dp2.afiperu.others;

import android.content.res.Resources;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.syncmodel.SyncLocation;
import com.example.dp2.afiperu.syncmodel.SyncPointOfReunion;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Fernando on 08/10/2015.
 */
public class MarkerInfo implements Serializable {

    public static final int MARKER_KIND_EVENT_ADDRESS = 0;
    public static final int MARKER_KIND_SESSION_ADDRESS = 1;
    public static final int MARKER_KIND_SESSION_REUNION_ENABLED = 2;
    public static final int MARKER_KIND_SESSION_REUNION_CREATED = 3;
    public static final int MARKER_KIND_SESSION_REUNION_DISABLED = 4;
    public static final int MARKER_KIND_INFO_SCHOOL = 5;
    public static final int MARKER_KIND_INFO_VOLUNTEER = 6;

    public String markerId;
    public int pointId;
    public String address;
    public double latitude;
    public double longitude;
    private int markerKind;
    private String textArg;

    public String getTitle(Resources resources) {
        int id = 0;
        switch(markerKind){
            case MARKER_KIND_EVENT_ADDRESS: id = R.string.marker_event_address; break;
            case MARKER_KIND_SESSION_ADDRESS: id = R.string.marker_session_address; break;
            case MARKER_KIND_SESSION_REUNION_ENABLED:
            case MARKER_KIND_SESSION_REUNION_CREATED:
            case MARKER_KIND_SESSION_REUNION_DISABLED:
                id = R.string.marker_session_reunion; break;
            case MARKER_KIND_INFO_SCHOOL: id = R.string.marker_info_school; break;
            case MARKER_KIND_INFO_VOLUNTEER: id = R.string.marker_info_volunteer; break;
        }
        return id > 0 ? resources.getString(id, textArg) : null;
    }

    public static BitmapDescriptor getColoredIcon(int markerKind){
        float hue;
        switch(markerKind){
            case MARKER_KIND_EVENT_ADDRESS:
            case MARKER_KIND_SESSION_ADDRESS:
            case MARKER_KIND_INFO_SCHOOL:
                hue = BitmapDescriptorFactory.HUE_AZURE;
                break;
            case MARKER_KIND_SESSION_REUNION_ENABLED:
            case MARKER_KIND_INFO_VOLUNTEER:
            default:
                hue = BitmapDescriptorFactory.HUE_RED;
                break;
            case MARKER_KIND_SESSION_REUNION_CREATED:
                hue = BitmapDescriptorFactory.HUE_YELLOW;
                break;
            case MARKER_KIND_SESSION_REUNION_DISABLED:
                hue = BitmapDescriptorFactory.HUE_GREEN;
                break;
        }
        return BitmapDescriptorFactory.defaultMarker(hue);
    }

    public BitmapDescriptor getColoredIcon(){
        return getColoredIcon(markerKind);
    }

    public boolean isCreated(){
        return markerKind == MARKER_KIND_SESSION_REUNION_CREATED;
    }

    public boolean isEnabled(){
        return markerKind == MARKER_KIND_SESSION_REUNION_ENABLED;
    }

    public boolean isDisabled(){
        return markerKind == MARKER_KIND_SESSION_REUNION_DISABLED;
    }

    public void setEnabled(boolean enabled){
        markerKind = enabled ? MARKER_KIND_SESSION_REUNION_ENABLED : MARKER_KIND_SESSION_REUNION_DISABLED;
    }

    public boolean toggleEnabled(){
        if(markerKind == MARKER_KIND_SESSION_REUNION_ENABLED){
            setEnabled(false);
            return false;
        }else{
            setEnabled(true);
            return true;
        }
    }

    public MarkerInfo(int pointId, double latitude, double longitude, int markerKind, String textArg) {
        this.pointId = pointId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.markerKind = markerKind;
        this.textArg = textArg;
    }

    //Sólo para sesiones
    public MarkerInfo(SyncLocation location){
        this(-1, location.getLatitude(), location.getLongitude(), MARKER_KIND_SESSION_ADDRESS, null);
    }
    public MarkerInfo(SyncPointOfReunion pointOfReunion, boolean selected){
        this(pointOfReunion.getPointId(), pointOfReunion.getLatitude(), pointOfReunion.getLongitude(),
                selected ? MARKER_KIND_SESSION_REUNION_ENABLED : MARKER_KIND_SESSION_REUNION_DISABLED, null);
    }
    public MarkerInfo(LatLng point){
        this(-1, point.latitude, point.longitude, MARKER_KIND_SESSION_REUNION_CREATED, null);
    }

    public SyncPointOfReunion toPointOfReunion(){
        if(markerKind == MARKER_KIND_SESSION_REUNION_ENABLED || markerKind == MARKER_KIND_SESSION_REUNION_DISABLED) {
            SyncPointOfReunion point = new SyncPointOfReunion(pointId, latitude, longitude,
                    markerKind == MARKER_KIND_SESSION_REUNION_ENABLED ? 1 : 0);
            return point;
        }
        return null;
    }

}
