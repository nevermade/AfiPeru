package com.afiperu.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fernando on 24/11/2015.
 */
public class PushBody {

    @SerializedName("push_events")
    @Expose
    private Integer pushEvents;
    @SerializedName("push_fees")
    @Expose
    private Integer pushFees;
    @SerializedName("push_documents")
    @Expose
    private Integer pushDocuments;
    @SerializedName("push_reports")
    @Expose
    private Integer pushReports;

    public Integer getPushEvents() {
        return pushEvents;
    }

    public void setPushEvents(Integer pushEvents) {
        this.pushEvents = pushEvents;
    }

    public Integer getPushFees() {
        return pushFees;
    }

    public void setPushFees(Integer pushFees) {
        this.pushFees = pushFees;
    }

    public Integer getPushDocuments() {
        return pushDocuments;
    }

    public void setPushDocuments(Integer pushDocuments) {
        this.pushDocuments = pushDocuments;
    }

    public Integer getPushReports() {
        return pushReports;
    }

    public void setPushReports(Integer pushReports) {
        this.pushReports = pushReports;
    }

    public PushBody() {

    }

    public PushBody(Integer pushEvents, Integer pushFees, Integer pushDocuments, Integer pushReports) {
        this.pushEvents = pushEvents;
        this.pushFees = pushFees;
        this.pushDocuments = pushDocuments;
        this.pushReports = pushReports;
    }
}
