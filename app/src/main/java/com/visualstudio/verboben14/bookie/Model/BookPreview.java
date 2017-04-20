package com.visualstudio.verboben14.bookie.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zsoltdemjan on 2017. 04. 18..
 *
 * "like_average":4.43655,
 //"like_count":134,
 / "reviews_count":47,
 //                "citations_count":84

     {
       "id":291291,
       "author":"Gödrösi Ádám",
       "title":"Street workout mindenkinek",
       "cover":"https://moly.hu/system/covers/normal/covers_388383.jpg"
    }
 */

public class BookPreview {

//    Firs ISBN search parameter
    @SerializedName("id")
    private long mId;

    private String mIsbn;

    @SerializedName("author")
    private String mAuthor;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("cover")
    private String mCover;

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

    public BookPreview(long id, String author, String title, String cover) {
        mId = id;
        mAuthor = author;
        mTitle = title;
        mCover = cover;
    }
}
