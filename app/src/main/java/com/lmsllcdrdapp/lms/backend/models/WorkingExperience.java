package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WorkingExperience implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("at")
    @Expose
    private String at;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("fromDate:")
    @Expose
    private String fromDate;
    @SerializedName("years")
    @Expose
    private int years;

    public WorkingExperience() {
    }

    public WorkingExperience(String id, String at, String position, String fromDate, int years) {
        this.id = id;
        this.at = at;
        this.position = position;
        this.fromDate = fromDate;
        this.years = years;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }
}
