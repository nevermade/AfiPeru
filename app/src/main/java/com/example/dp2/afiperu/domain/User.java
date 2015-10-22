package com.example.dp2.afiperu.domain;

import java.io.Serializable;

/**
 * Created by Nevermade on 01/10/2015.
 */
public class User implements Serializable, Comparable<User> {
    private String nickName;
    private String cod;
    private String name;
    private String lastName;
    private String secondLastName;
    private String profile;
    private double score;
    private boolean hasDownloaded;

    public User(String nickName, String cod, String name, String lastName, String secondLastName, String profile, double score, boolean hasDownloaded) {
        this.nickName = nickName;
        this.cod = cod;
        this.name = name;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.profile = profile;
        this.score = score;
        this.hasDownloaded=hasDownloaded;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getProfile(){
        return profile;
    }

    public boolean isHasDownloaded() {
        return hasDownloaded;
    }

    public void setHasDownloaded(boolean hasDownloaded) {
        this.hasDownloaded = hasDownloaded;
    }
    @Override
    public int compareTo(User o2) {
        return (o2.name).compareTo(name);
    }
}