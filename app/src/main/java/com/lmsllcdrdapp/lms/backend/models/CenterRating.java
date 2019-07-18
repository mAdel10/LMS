package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CenterRating implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("rate")
    @Expose
    private int rate;
    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("centerId")
    @Expose
    private String centerId;
    @SerializedName("student")
    @Expose
    private Student student;
    @SerializedName("center")
    @Expose
    private Center center;

    public CenterRating(String id, int rate, String studentId, String centerId, Student student, Center center) {
        this.id = id;
        this.rate = rate;
        this.studentId = studentId;
        this.centerId = centerId;
        this.student = student;
        this.center = center;
    }

    public CenterRating() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }
}
