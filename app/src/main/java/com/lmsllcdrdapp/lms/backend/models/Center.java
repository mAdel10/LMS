package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Center implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("partiners")
    @Expose
    private List<Partner> partner;
    @SerializedName("categoryIds")
    @Expose
    private List<String> categoryIds;
    @SerializedName("rate")
    @Expose
    private float rate;
    @SerializedName("rateCount")
    @Expose
    private int rateCount;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("lat")
    @Expose
    private float lat;
    @SerializedName("lng")
    @Expose
    private float lng;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("workingHours")
    @Expose
    private List<WorkingHour> workingHours;

    public Center(String id, String name, List<Partner> partner, List<String> categoryIds, float rate, int rateCount, String address, float lat, float lng, String userId, User user) {
        this.id = id;
        this.name = name;
        this.partner = partner;
        this.categoryIds = categoryIds;
        this.rate = rate;
        this.rateCount = rateCount;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.userId = userId;
        this.user = user;
    }

    public Center() {

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

    public List<Partner> getPartner() {
        return partner;
    }

    public void setPartner(List<Partner> partner) {
        this.partner = partner;
    }

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<WorkingHour> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<WorkingHour> workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public String toString() {
        return name;
    }
}
