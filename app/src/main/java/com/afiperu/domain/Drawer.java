package com.afiperu.domain;


import java.io.Serializable;

public class Drawer implements Serializable {
    private int fragmentId;
    private String name;
    private int iconId;

    public Drawer(int fragmentId, String name, int iconId) {
        this.fragmentId = fragmentId;
        this.name = name;
        this.iconId = iconId;
    }

    public int getFragmentId(){
        return fragmentId;
    }

    public String getName() {
        return name;
    }

    public int getIconId() {
        return iconId;
    }
}
