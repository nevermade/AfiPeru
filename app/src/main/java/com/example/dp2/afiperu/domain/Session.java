package com.example.dp2.afiperu.domain;


import java.io.Serializable;

public class Session implements Serializable, Comparable<Session> {
    private String name;
    private long date;

    public Session(String name, long date) {
        this.name = name;
        this.date = date;
    }

    public String getName() { return name; }

    public long getDate() {
        return date;
    }

    @Override
    public int compareTo(Session o2){
        return Long.valueOf(o2.date).compareTo(date);
    }
}
