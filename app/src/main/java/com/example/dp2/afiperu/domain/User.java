package com.example.dp2.afiperu.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nevermade on 01/10/2015.
 */
public class User implements Serializable, Comparable<User> {
    @SerializedName("id")
    @Expose
    private Integer cod;
    @SerializedName("names")
    @Expose
    private String name;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("username")
    @Expose
    private String nickName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("profiles")
    @Expose
    private List<Profile> profiles = new ArrayList<Profile>();
    @SerializedName("actions")
    @Expose
    private List<Action> actions = new ArrayList<Action>();
    @SerializedName("can_reapply")
    @Expose
    private Integer can_reapply;
    @SerializedName("period")
    @Expose
    private Period period;
    @SerializedName("auth_token")
    @Expose
    private String authToken;

    private String secondLastName;
    private String profile;
    private double score;
    private boolean hasDownloaded;

    public User(String nickName, int cod, String name, String lastName, String secondLastName, String profile, double score, boolean hasDownloaded) {
        this.nickName = nickName;
        this.cod = cod;
        this.name = name;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.profile = profile;
        this.score = score;
        this.hasDownloaded=hasDownloaded;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public boolean isHasDownloaded() {
        return hasDownloaded;
    }

    public void setHasDownloaded(boolean hasDownloaded) {
        this.hasDownloaded = hasDownloaded;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public Integer isCan_reapply() {
        return can_reapply;
    }

    public void setCan_reapply(Integer can_reapply) {
        this.can_reapply = can_reapply;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(User o2) {
        return (o2.name).compareTo(name);
    }
}