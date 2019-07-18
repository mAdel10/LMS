package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Instructor implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("patch")
    @Expose
    private boolean patch;
    @SerializedName("cvUrl")
    @Expose
    private String cvUrl;
    @SerializedName("profileCompleted")
    @Expose
    private boolean profileCompleted;
    @SerializedName("workingExperience")
    @Expose
    private List<WorkingExperience> workingExperience;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("categoryIds")
    @Expose
    private List<String> categoryIds;
    @SerializedName("rate")
    @Expose
    private float rate;
    @SerializedName("rateCount")
    @Expose
    private float rateCount;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("center")
    @Expose
    private Center center;

    public Instructor(String id, String firstName, String lastName, String position, boolean patch, String cvUrl, boolean profileCompleted, List<WorkingExperience> workingExperience, String userId, List<String> categoryIds, float rate, float rateCount, String dateOfBirth, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.patch = patch;
        this.cvUrl = cvUrl;
        this.profileCompleted = profileCompleted;
        this.workingExperience = workingExperience;
        this.userId = userId;
        this.categoryIds = categoryIds;
        this.rate = rate;
        this.rateCount = rateCount;
        this.dateOfBirth = dateOfBirth;
        this.user = user;
    }

    public Instructor(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Instructor() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isPatch() {
        return patch;
    }

    public void setPatch(boolean patch) {
        this.patch = patch;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public boolean isProfileCompleted() {
        return profileCompleted;
    }

    public void setProfileCompleted(boolean profileCompleted) {
        this.profileCompleted = profileCompleted;
    }

    public List<WorkingExperience> getWorkingExperience() {
        return workingExperience;
    }

    public void setWorkingExperience(List<WorkingExperience> workingExperience) {
        this.workingExperience = workingExperience;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public float getRateCount() {
        return rateCount;
    }

    public void setRateCount(float rateCount) {
        this.rateCount = rateCount;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
