package com.visualstudio.verboben14.bookie.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsoltdemjan on 2017. 04. 18..
 * {
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

public class BookMolyResponse {

    @SerializedName("book")
    private List<BookMoly> mBookMolyList = new ArrayList<>();

    public List<BookMoly> getBooks() {
        return mBookMolyList;
    }

    public void setBooks(List<BookMoly> mBookMoly) {
        mBookMolyList = mBookMoly;
    }
}
