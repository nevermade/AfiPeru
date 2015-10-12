package com.example.dp2.afiperu.lists;


import java.io.Serializable;
import java.util.ArrayList;

public class SessionItem implements Serializable, Comparable<SessionItem> {
    private String name;
    private long date;
    private ArrayList<MarkerInfo> markers;

    public SessionItem(String name, long date, ArrayList<MarkerInfo> markers) {
        this.name = name;
        this.date = date;
        this.markers = markers;
    }

    public String getName() { return name; }

    public long getDate() {
        return date;
    }

    public ArrayList<MarkerInfo> getMarkers() {
        ArrayList result = new ArrayList<>(markers.size());
        for(MarkerInfo marker : markers){
            result.add(marker.clone());
        }
        return result;
    }

    @Override
    public int compareTo(SessionItem o2){
        return Long.valueOf(o2.date).compareTo(date);
    }
}
