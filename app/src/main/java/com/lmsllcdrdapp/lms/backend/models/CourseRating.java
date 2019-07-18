package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CourseRating implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("rate")
    @Expose
    private float rate;
    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("courseId")
    @Expose
    private String courseId;
    @SerializedName("student")
    @Expose
    private Student student;
    @SerializedName("course")
    @Expose
    private Course course;

    public CourseRating(String id, float rate, String studentId, String courseId, Student student, Course course) {
        this.id = id;
        this.rate = rate;
        this.studentId = studentId;
        this.courseId = courseId;
        this.student = student;
        this.course = course;
    }

    public CourseRating() {
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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}