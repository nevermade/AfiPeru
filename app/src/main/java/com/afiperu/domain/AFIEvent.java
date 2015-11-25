package com.afiperu.domain;

import java.io.Serializable;

/**
 * Created by Fernando on 20/10/2015.
 */
public class AFIEvent implements Serializable {

    private long dateTime;
    private String title;
    private String addressStr;

    public AFIEvent(String addressStr, long dateTime, String title) {
        this.addressStr = addressStr;
        this.dateTime = dateTime;
        this.title = title;
    }

    public long getDateTime() {
        return dateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getAddressStr() {
        return addressStr;
    }
}
