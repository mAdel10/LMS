package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import java.io.Serializable;

public class WorkingHour implements Serializable {

    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("day")
    @Expose
    private int day;
    @SerializedName("teacher_id")
    @Expose
    private long teacherId;

    public String getTo() {
        return Utilities.formatTime(to);
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return Utilities.formatTime(from);
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }
}
