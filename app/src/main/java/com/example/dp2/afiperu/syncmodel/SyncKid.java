package com.example.dp2.afiperu.syncmodel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.io.Serializable;

public class SyncKid extends SugarRecord<SyncKid> implements Serializable, Comparable<SyncKid> {

    private Integer kidId;

    private String names;
    private String lastName;
    private Integer age;
    private Integer gender;


    public SyncKid(){}


    /**
     *
     * @return
     * The id
     */
    public Integer getKidId() {
        return kidId;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setKidId(Integer id) {
        this.kidId = kidId;
    }

    /**
     *
     * @return
     * The names
     */
    public String getNames() {
        return names;
    }

    /**
     *
     * @param names
     * The names
     */
    public void setNames(String names) {
        this.names = names;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     * The age
     */
    public Integer getAge() {
        return age;
    }

    /**
     *
     * @param age
     * The age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     *
     * @return
     * The gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public int compareTo(SyncKid o2){
        return names.compareTo(o2.names);
    }
}
