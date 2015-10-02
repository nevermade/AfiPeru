package com.example.dp2.afiperu.lists;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Nevermade on 02/10/2015.
 */
public class PeopleKidsItem implements Serializable,Comparable<PeopleKidsItem> {
    private String name;
    private String lastName;
    private String secondLastName;
    private int age;
    private boolean isMale;

    public PeopleKidsItem(boolean isMale, int age, String secondLastName, String lastName, String name) {
        this.isMale = isMale;
        this.age = age;
        this.secondLastName = secondLastName;
        this.lastName = lastName;
        this.name = name;
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

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }

    @Override
    public int compareTo(PeopleKidsItem another) {
        return name.compareTo(another.name);
    }
}
