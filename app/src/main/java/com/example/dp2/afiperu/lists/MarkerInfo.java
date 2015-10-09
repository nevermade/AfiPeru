package com.example.dp2.afiperu.lists;

import android.content.res.Resources;

import com.example.dp2.afiperu.R;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.io.Serializable;

/**
 * Created by Fernando on 08/10/2015.
 */
public class MarkerInfo implements Serializable {

    public static final int MARKER_KIND_EVENT_ADDRESS = 0;
    public static final int MARKER_KIND_SESSION_ADDRESS = 1;
    public static final int MARKER_KIND_SESSION_REUNION = 2;
    public static final int MARKER_KIND_INFO_SCHOOL = 3;
    public static final int MARKER_KIND_INFO_VOLUNTEER = 4;

    private double latitude;
    private double longitude;
    private int markerKind;
    private String textArg;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTitle(Resources resources) {
        int id = 0;
        switch(markerKind){
            case MARKER_KIND_EVENT_ADDRESS: id = R.string.marker_event_address; break;
            case MARKER_KIND_SESSION_ADDRESS: id = R.string.marker_session_address; break;
            case MARKER_KIND_SESSION_REUNION: id = R.string.marker_session_reunion; break;
            case MARKER_KIND_INFO_SCHOOL: id = R.string.marker_info_school; break;
            case MARKER_KIND_INFO_VOLUNTEER: id = R.string.marker_info_volunteer; break;
        }
        return id > 0 ? resources.getString(id, textArg) : null;
    }

    public BitmapDescriptor getColoredIcon(){
        float hue;
        switch(markerKind){
            case MARKER_KIND_EVENT_ADDRESS:
            case MARKER_KIND_SESSION_ADDRESS:
            case MARKER_KIND_INFO_SCHOOL:
                hue = BitmapDescriptorFactory.HUE_AZURE;
                break;
            case MARKER_KIND_SESSION_REUNION:
            case MARKER_KIND_INFO_VOLUNTEER:
            default:
                hue = BitmapDescriptorFactory.HUE_RED;
                break;
        }
        return BitmapDescriptorFactory.defaultMarker(hue);
    }

    public MarkerInfo(double latitude, double longitude, int markerKind, String textArg) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.markerKind = markerKind;
        this.textArg = textArg;
    }
}
