package com.lmsllcdrdapp.lms.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchFrom implements Serializable {

    @SerializedName("keyword")
    @Expose
    private String keyword;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("languageId")
    @Expose
    private String languageId;
    @SerializedName("countryId")
    @Expose
    private String countryId;
    @SerializedName("cityId")
    @Expose
    private String cityId;

    public SearchFrom(String keyword, String categoryId, String languageId, String countryId, String cityId) {
        this.keyword = keyword;
        this.categoryId = categoryId;
        this.languageId = languageId;
        this.countryId = countryId;
        this.cityId = cityId;
    }

    public SearchFrom() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
