package com.visualstudio.verboben14.bookie.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zsoltdemjan on 2017. 04. 18..
 * * {
   "book":{
      "id":317863,
      "authors":[
         {
            "id":118322,
            "name":"Morgan Rhodes"
         }
      ],
      "editors":[

      ],
      "title":"Falling âKingdoms - KirĂĄlyok harca",
      "subtitle":"",
      "cover":"https://moly.hu/system/covers/normal/covers_434693.jpg",
      "description":"Mytica âhĂĄrom kirĂĄlysĂĄgĂĄban mĂĄr rĂŠg a feledĂŠs homĂĄlyĂĄba merĂźlt a mĂĄgia. BĂĄr ĂŠvszĂĄzadok Ăłta bĂŠke uralkodik a birodalmak kĂśzĂśtt, a felszĂ­n alatt most megmozdul valami fenyegetĹ sĂśtĂŠtsĂŠg. \r\n\r\nMindhĂĄrom kirĂĄlysĂĄg vezetĹje egyeduralomra tĂśr, Ă­gy az alattvalĂłik ĂŠlete nagy fordulatot vesz. A fĹszereplĹk - kĂśztĂźk a kirĂĄlyi csalĂĄdok tagjai ĂŠs a lĂĄzadĂłk - rĂĄĂŠbrednek, hogy sorsuk visszafordĂ­thatatlanul ĂśsszefonĂłdik. Cleo, Jonas, Lucia ĂŠs Magnus egy olyan Ăşj, ĹrĂźlt vilĂĄgban talĂĄljĂĄk magukat, ahol gĂĄtlĂĄstalan ĂĄrulĂłk, hidegvĂŠrĹą gyilkosok ĂŠs titkos szĂśvetsĂŠgesek keresztezik Ăştjukat. UtazĂĄsuk sorĂĄn egyre tĂśbb vĂĄratlan fordulattal, titokkal ĂŠs ismeretlen ĂŠrzĂŠssel talĂĄljĂĄk szembe magukat.\r\n\r\nAz egyetlen dolog, amiben biztosak lehetnek, hogy az egyik birodalom el fog bukni. De vajon lehet bĂĄrki gyĹztes, amikor minden szĂŠthullik kĂśrĂźlĂśttĂźk?\r\n\r\nA TrĂłnok harca YA verzĂłjakĂŠnt emlegetett Falling Kingdoms vĂŠgre magyarul!",
      "url":"https://moly.hu/konyvek/morgan-rhodes-falling-kingdoms-kiralyok-harca",
      "tags":[
         {
            "id":5637,
            "name":"magyar nyelvĹą"
         },
         {
            "id":2126,
            "name":"ifjĂşsĂĄgi"
         },
         {
            "id":13,
            "name":"regĂŠny"
         },
         {
            "id":7,
            "name":"fantasy"
         },
         {
            "id":854,
            "name":"mĂĄgia"
         },
         {
            "id":5693,
            "name":"high fantasy"
         }
      ],
      "like_average":0.0,
      "like_count":0,
      "reviews_count":0,
      "citations_count":3
   }
}
 */


public class BookMoly implements Parcelable {
    @SerializedName("id")
    private long mId;

    private String mIsbn;

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

    protected BookMoly (Parcel in) {
        mId = in.readLong();
        mTitle = in.readString();
        mCover = in.readString();
        mDescription = in.readString();
        mUrl = in.readString();
        mLikeAvg = in.readFloat();
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mAuthor);
        dest.writeString(mTitle);
        dest.writeString(mCover);
        dest.writeString(mDescription);
        dest.writeString(mIsbn);
        dest.writeString(mUrl);
        dest.writeFloat(mLikeAvg);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BookMoly> CREATOR = new Creator<BookMoly>() {
        @Override
        public BookMoly createFromParcel(Parcel in) {
            return new BookMoly(in);
        }

        @Override
        public BookMoly[] newArray(int size) {
            return new BookMoly[size];
        }
    };
}