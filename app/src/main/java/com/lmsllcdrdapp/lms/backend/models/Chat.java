package com.lmsllcdrdapp.lms.backend.models;

import android.content.Context;
import android.widget.ImageView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lmsllcdrdapp.lms.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class Chat implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("groupId")
    @Expose
    private String groupId;
    @SerializedName("group")
    @Expose
    private Group group;
    @SerializedName("user")
    @Expose
    private User user;

    public Chat(String id, String message, String date, String from, String groupId, Group group, User user) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.from = from;
        this.groupId = groupId;
        this.group = group;
        this.user = user;
    }

    public Chat(String id, String message, String from, String date) {
        this.id = id;
        this.message = message;
        this.from = from;
        this.date = date;
    }

    public Chat(String message, String date, String groupId) {
        this.message = message;
        this.date = date;
        this.groupId = groupId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        switch (user.getUserType()) {
            case "student":
                return user.getStudent().getFirstName() + " " + user.getStudent().getLastName();
            case "instructor":
                return user.getInstructor().getFirstName() + " " + user.getInstructor().getLastName();
            case "center":
                return user.getCenter().getName();
        }
        
        return "";
    }

    public void loadImage(ImageView imageView, Context context) {
        Picasso.with(context)
                .load(user.getImage())
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .resize(100, 100)
                .centerCrop()
                .placeholder(R.drawable.img_placeholder)
                .into(imageView);
    }
}
