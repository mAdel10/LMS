package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Notification implements Serializable {


    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("fromId")
    @Expose
    private User fromId;
    @SerializedName("toIds")
    @Expose
    private List<String> toIds;
    @SerializedName("to")
    @Expose
    private List<User> to;
    @SerializedName("from")
    @Expose
    private List<User> from;

    public Notification(String id, String title, String body, String data, String type, User fromId, List<String> toIds, List<User> to, List<User> from) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.data = data;
        this.type = type;
        this.fromId = fromId;
        this.toIds = toIds;
        this.to = to;
        this.from = from;
    }

    public Notification() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getFromId() {
        return fromId;
    }

    public void setFromId(User fromId) {
        this.fromId = fromId;
    }

    public List<String> getToIds() {
        return toIds;
    }

    public void setToIds(List<String> toIds) {
        this.toIds = toIds;
    }

    public List<User> getTo() {
        return to;
    }

    public void setTo(List<User> to) {
        this.to = to;
    }

    public List<User> getFrom() {
        return from;
    }

    public void setFrom(List<User> from) {
        this.from = from;
    }
}
