package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Certificate implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("certificateNumber")
    @Expose
    private int certificateNumber;
    @SerializedName("certificatePDF")
    @Expose
    private String certificatePDF;
    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("groupId")
    @Expose
    private String groupId;
    @SerializedName("student")
    @Expose
    private Student student;
    @SerializedName("group")
    @Expose
    private Group group;

    public Certificate(String id, int certificateNumber, String certificatePDF, String studentId, String groupId, Student student, Group group) {
        this.id = id;
        this.certificateNumber = certificateNumber;
        this.certificatePDF = certificatePDF;
        this.studentId = studentId;
        this.groupId = groupId;
        this.student = student;
        this.group = group;
    }

    public Certificate() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(int certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificatePDF() {
        return certificatePDF;
    }

    public void setCertificatePDF(String certificatePDF) {
        this.certificatePDF = certificatePDF;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
