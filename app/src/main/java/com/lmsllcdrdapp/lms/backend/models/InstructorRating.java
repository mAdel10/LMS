package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InstructorRating implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("rate")
    @Expose
    private float rate;
    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("instructorId")
    @Expose
    private String instructorId;
    @SerializedName("instructor")
    @Expose
    private Instructor instructor;
    @SerializedName("student")
    @Expose
    private Student student;

    public InstructorRating(String id, float rate, String studentId, String instructorId, Instructor instructor, Student student) {
        this.id = id;
        this.rate = rate;
        this.studentId = studentId;
        this.instructorId = instructorId;
        this.instructor = instructor;
        this.student = student;
    }

    public InstructorRating() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
