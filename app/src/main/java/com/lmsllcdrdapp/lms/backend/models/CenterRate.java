package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CenterRate {

    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("centerId")
    @Expose
    private String centerId;
    @SerializedName("rate")
    @Expose
    private float rate;

    public CenterRate(String studentId, String centerId, float rate) {
        this.studentId = studentId;
        this.centerId = centerId;
        this.rate = rate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCenterIdId() {
        return centerId;
    }

    public void setCenterIdId(String courseId) {
        this.centerId = courseId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
