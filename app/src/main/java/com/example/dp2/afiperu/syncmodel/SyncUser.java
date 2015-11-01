package com.example.dp2.afiperu.syncmodel;

import com.example.dp2.afiperu.domain.Action;
import com.example.dp2.afiperu.domain.Profile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nevermade on 01/10/2015.
 */
public class SyncUser extends SugarRecord<SyncUser> implements Serializable, Comparable<SyncUser> {

    private Integer cod;

    private String name;

    private String lastName;

    private String nickName;
    @Ignore
    private List<Profile> profiles = new ArrayList<Profile>();
    @Ignore
    private List<Action> actions = new ArrayList<Action>();

    private String authToken;

    private String secondLastName;
    private String profile;
    private double score;
    private boolean hasDownloaded;

    public SyncUser(){}
    public SyncUser(String nickName, int cod, String name, String lastName, String secondLastName, String profile, double score, boolean hasDownloaded) {
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

    @Override
    public int compareTo(SyncUser o2) {
        return name.compareTo(o2.name);
    }
}