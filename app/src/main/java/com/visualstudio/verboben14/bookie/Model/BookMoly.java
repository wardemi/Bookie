package com.visualstudio.verboben14.bookie.Model;

import com.google.gson.annotations.SerializedName;

public class BookMoly {
    @SerializedName("id")
    private long mId;

    private String mIsbn;

    @SerializedName("author")
    private String mAuthor;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("cover")
    private String mCover;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("like_average")
    private Float mLikeAvg;

    public BookMoly(long id, String title, String cover, String description, String url, Float likeAvg) {
        mId = id;
        mTitle = title;
        mCover = cover;
        mDescription = description;
        mUrl = url;
        mLikeAvg = likeAvg;
    }

    public BookMoly() {}

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getIsbn() {
        return mIsbn;
    }

    public void setIsbn(String isbn) {
        mIsbn = isbn;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getCover() {
        return mCover;
    }

    public void setCover(String cover) {
        mCover = cover;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Float getLikeAvg() {
        return mLikeAvg;
    }

    public void setLikeAvg(Float likeAvg) {
        mLikeAvg = likeAvg;
    }
}