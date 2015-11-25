package com.afiperu.domain;

import java.io.Serializable;

/**
 * Created by Fernando on 24/09/2015.
 */
public class Attendance implements Serializable, Comparable<Attendance> {

    private int picId;
    private String picURL;
    private String name;
    private boolean toggleOn;

    public Attendance(int picId, String picURL, String name, boolean toggleOn){
        this.picId = picId;
        this.picURL = picURL;
        this.name = name;
        this.toggleOn = toggleOn;
    }

    public int getPicId(){
        return picId;
    }

    public String getPicURL(){
        return picURL;
    }

    public String getName(){
        return name;
    }

    public boolean isToggleOn(){
        return toggleOn;
    }

    public int compareTo(Attendance o2){
        return name.compareTo(o2.name);
    }

}
