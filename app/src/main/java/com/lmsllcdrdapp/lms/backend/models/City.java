package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class City implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("isActive")
    @Expose
    private boolean isActive;
    @SerializedName("countryId")
    @Expose
    private String countryId;
    @SerializedName("county")
    @Expose
    private Country county;

    public City(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public City() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Country getCounty() {
        return county;
    }

    public void setCounty(Country county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return name;
    }
}
