package com.example.dp2.afiperu.domain;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Kid implements Serializable, Comparable<Kid> {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("names")
    @Expose
    private String names;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("sessions")
    @Expose
    private Integer sessions;
    @SerializedName("comments")
    @Expose
    private List<Comment> comments;

    /**
     *
     * @return
     * The id
     */
    public Kid (String n, Integer age ,Boolean isMale){
        this.names = n;
        this.age=age;
        if (isMale) this.gender=0; else gender=1;
    }

    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSessions() {
        return sessions;
    }

    public void setSessions(Integer sessions) {
        this.sessions = sessions;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public int compareTo(Kid o2){
        return names.compareTo(o2.names);
    }
}
