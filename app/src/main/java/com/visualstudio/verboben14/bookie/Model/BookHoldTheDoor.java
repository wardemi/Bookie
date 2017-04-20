package com.visualstudio.verboben14.bookie.Model;

/**
 * Created by zsoltdemjan on 2017. 04. 18..
 *
 * "like_average":4.43655,
 //"like_count":134,
 / "reviews_count":47,
 //                "citations_count":84
 */

public class BookHoldTheDoor {

//    Firs ISBN search parameter
    private long mId;
    private String mIsbn;
    private String mAuthor;
    private String mTitle;
    private String mCover;

//    More Parameter Options
    private String mDescription;
    private String mUrl;
    private Float mLikeAvg;

//    Own parameter
    private boolean mFavourite;

    public boolean isFavourite() {
        return mFavourite;
    }

    public void setFavourite(boolean favourite) {
        mFavourite = favourite;
    }

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

    public BookHoldTheDoor(int id, String isbn, String author, String title, String cover) {
        mId = id;
        mIsbn = isbn;
        mAuthor = author;
        mTitle = title;
        mCover = cover;
    }

    public BookHoldTheDoor(int id, String isbn, String author, String title, String cover, String description, String url, Float likeAvg, boolean favourite) {
        mId = id;
        mIsbn = isbn;
        mAuthor = author;
        mTitle = title;
        mCover = cover;
        mDescription = description;
        mUrl = url;
        mLikeAvg = likeAvg;
        mFavourite = favourite;
    }
}
