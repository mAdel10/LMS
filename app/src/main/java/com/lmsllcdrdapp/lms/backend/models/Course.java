package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("maxNumbers")
    @Expose
    private int maxNumbers;
    @SerializedName("rate")
    @Expose
    private float rate;
    @SerializedName("rateCount")
    @Expose
    private float rateCount;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("isOffered")
    @Expose
    private boolean isOffered;
    @SerializedName("priceAfterDiscount")
    @Expose
    private double priceAfterDiscount;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("languageId")
    @Expose
    private String languageId;
    @SerializedName("instructorId")
    @Expose
    private String instructorId;
    @SerializedName("centerId")
    @Expose
    private String centerId;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("language")
    @Expose
    private Language language;
    @SerializedName("center")
    @Expose
    private Center center;
    @SerializedName("instructor")
    @Expose
    private Instructor instructor;
    @SerializedName("numberOfSessions")
    @Expose
    private String numberOfSessions;
    @SerializedName("videoUrl")
    @Expose
    private String videoUrl;
    @SerializedName("sessions")
    @Expose
    private List<String> sessions;

    public Course(String id, String name, String image, String description, String content, int maxNumbers, float rate, float rateCount, double price, boolean isOffered, double priceAfterDiscount, String categoryId,
                  String languageId, String instructorId, String centerId, Category category, Language language, Center center, Instructor instructor, String numberOfSessions) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.content = content;
        this.maxNumbers = maxNumbers;
        this.rate = rate;
        this.rateCount = rateCount;
        this.price = price;
        this.isOffered = isOffered;
        this.priceAfterDiscount = priceAfterDiscount;
        this.categoryId = categoryId;
        this.languageId = languageId;
        this.instructorId = instructorId;
        this.centerId = centerId;
        this.category = category;
        this.language = language;
        this.center = center;
        this.instructor = instructor;
        this.numberOfSessions = numberOfSessions;
    }

    public Course(String name, String description, String content, int maxNumbers,
                  String numberOfSessions, double price, String categoryId, String languageId, String instructorId) {
        this.name = name;
        this.numberOfSessions = numberOfSessions;
        this.price = price;
        this.description = description;
        this.content = content;
        this.maxNumbers = maxNumbers;
        this.languageId = languageId;
        this.categoryId = categoryId;
        this.languageId = languageId;
        this.instructorId = instructorId;
    }

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getRateCount() {
        return rateCount;
    }

    public void setRateCount(float rateCount) {
        this.rateCount = rateCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMaxNumbers() {
        return maxNumbers;
    }

    public void setMaxNumbers(int maxNumbers) {
        this.maxNumbers = maxNumbers;
    }

    public boolean isOffered() {
        return isOffered;
    }

    public void setOffered(boolean offered) {
        isOffered = offered;
    }

    public double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getNumberOfSessions() {
        return numberOfSessions;
    }

    public void setNumberOfSessions(String numberOfSessions) {
        this.numberOfSessions = numberOfSessions;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<String> getSessions() {
        return sessions;
    }

    public void setSessions(List<String> sessions) {
        this.sessions = sessions;
    }
}