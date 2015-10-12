package com.example.dp2.afiperu.domain;

import java.io.Serializable;

/**
 * Created by Nevermade on 01/10/2015.
 */
public class Users implements Serializable, Comparable<Users> {
    private String nickName;
    private String cod;
    private String name;
    private String lastName;
    private String secondLastName;
    private double score;

    public Users(String nickName, String cod, String name, String lastName, String secondLastName, double score) {
        this.nickName = nickName;
        this.cod = cod;
        this.name = name;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.score = score;
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

    @Override
    public int compareTo(Users o2) {
        return (o2.name).compareTo(name);
    }
}