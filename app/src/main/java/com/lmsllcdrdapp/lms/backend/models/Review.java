package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("sessionNumber")
    @Expose
    private int sessionNumber;
    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("student")
    @Expose
    private Student student;
    @SerializedName("groupId")
    @Expose
    private String groupId;
    @SerializedName("group")
    @Expose
    private Group group;

    public Review() {
    }

    public Review(String id, String comment, int sessionNumber, String studentId, Student student, String groupId, Group group) {
        this.id = id;
        this.comment = comment;
        this.sessionNumber = sessionNumber;
        this.studentId = studentId;
        this.student = student;
        this.groupId = groupId;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
}
