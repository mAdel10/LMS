package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("timeTable")
    @Expose
    private List<TimeTable> timeTable;
    @SerializedName("sessions")
    @Expose
    private List<Session> sessions;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("studentIds")
    @Expose
    private List<String> studentIds;
    @SerializedName("courseId")
    @Expose
    private String courseId;
    @SerializedName("course")
    @Expose
    private Course course;
    @SerializedName("enrolledNumber")
    @Expose
    private int enrolledNumber;
    @SerializedName("instructorId")
    @Expose
    private String instructorId;

    private boolean isSelected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<TimeTable> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(List<TimeTable> timeTable) {
        this.timeTable = timeTable;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<String> studentIds) {
        this.studentIds = studentIds;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getEnrolledNumber() {
        return enrolledNumber;
    }

    public void setEnrolledNumber(int enrolledNumber) {
        this.enrolledNumber = enrolledNumber;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }
}
