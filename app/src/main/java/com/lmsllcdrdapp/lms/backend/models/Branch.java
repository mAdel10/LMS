package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Branch implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("lat")
    @Expose
    private int lat;
    @SerializedName("lng")
    @Expose
    private int lng;
    @SerializedName("branchType")
    @Expose
    private String branchType;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("branch")
    @Expose
    private Branch branch;
    @SerializedName("user")
    @Expose
    private User user;


    public Branch(String id, String area, int lat, int lng, String branchType, String userId, Branch branch, User user) {
        this.id = id;
        this.area = area;
        this.lat = lat;
        this.lng = lng;
        this.branchType = branchType;
        this.userId = userId;
        this.branch = branch;
        this.user = user;
    }

    public Branch() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
