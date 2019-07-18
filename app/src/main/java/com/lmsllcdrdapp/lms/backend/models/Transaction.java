package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("cost")
    @Expose
    private double cost;
    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("groupId")
    @Expose
    private String groupId;
    @SerializedName("group")
    @Expose
    private Group group;
    @SerializedName("student")
    @Expose
    private Student student;

    public Transaction(String id, Date date, double cost, String studentId, String groupId, Group group, Student student) {
        this.id = id;
        this.date = date;
        this.cost = cost;
        this.studentId = studentId;
        this.groupId = groupId;
        this.group = group;
        this.student = student;
    }

    public Transaction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
