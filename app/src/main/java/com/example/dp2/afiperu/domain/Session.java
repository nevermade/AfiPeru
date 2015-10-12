package com.example.dp2.afiperu.domain;


import java.io.Serializable;
import java.util.ArrayList;

public class Session implements Serializable, Comparable<Session> {
    private String name;
    private long date;
    private ArrayList<MarkerInfo> markers;

    public Session(String name, long date, ArrayList<MarkerInfo> markers) {
   
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
    public int compareTo(Session o2){
        return Long.valueOf(o2.date).compareTo(date);
    }
}
