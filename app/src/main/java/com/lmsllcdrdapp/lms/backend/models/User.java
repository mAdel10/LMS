package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("deviceToken")
    @Expose
    private String deviceToken;
    @SerializedName("authCode")
    @Expose
    private String authCode;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("blocke")
    @Expose
    private Boolean blocked;
    @SerializedName("isAdmin")
    @Expose
    private Boolean isAdmin;
    @SerializedName("student")
    @Expose
    private Student student;
    @SerializedName("instructor")
    @Expose
    private Instructor instructor;
    @SerializedName("center")
    @Expose
    private Center center;

    public final static String TYPE_STUDENT = "student";
    public final static String TYPE_INSTRUCTOR = "instructor";
    public final static String TYPE_CENTER = "center";

    public User() {
    }

    public User(String id, String email, String password, String phone, String image, String deviceToken, String authCode, String accessToken, String userType, Boolean isActive, Boolean blocked, Boolean isAdmin, Student student, Instructor instructor, Center center) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.image = image;
        this.deviceToken = deviceToken;
        this.authCode = authCode;
        this.accessToken = accessToken;
        this.userType = userType;
        this.isActive = isActive;
        this.blocked = blocked;
        this.isAdmin = isAdmin;
        this.student = student;
        this.instructor = instructor;
        this.center = center;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }
}