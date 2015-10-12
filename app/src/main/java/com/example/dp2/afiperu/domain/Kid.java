package com.example.dp2.afiperu.domain;


import java.io.Serializable;

public class Kid implements Serializable, Comparable<Kid> {
    private String name;
    private int age;
    private boolean written;

    public Kid(String name, int age, boolean written) {
        this.name = name;
        this.age = age;
        this.written = written;
    }

    public String getName() { return name; }

    public int getAge() {
        return age;
    }

    public boolean isWritten() {
        return written;
    }

    @Override
    public int compareTo(Kid o2){
        return name.compareTo(o2.name);
    }
}
