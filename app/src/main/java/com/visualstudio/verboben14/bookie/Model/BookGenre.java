package com.visualstudio.verboben14.bookie.Model;

/**
 * Created by verboben14 on 2017. 04. 18..
 */

public enum BookGenre {
    SCIENCE_FICTION("Sci-fi"),
    DRAMA("Dráma"),
    ACTION("Akció"),
    ADVENTURE("Kaland"),
    ROMANCE("Romantikus"),
    MYSTERY("Rejtélyes"),
    HEALTH("Egészség"),
    TRAVEL("Utazás"),
    CHILDRENS("Gyermek"),
    SCIENCE("Tudományos"),
    HISTORY("Történelmi"),
    DICTIONARIES("Szótár"),
    COMICS("Képregény"),
    ART("Művészet"),
    RELIGION("Vallás"),
    POETRY("Költészet"),
    HORROR("Horror"),
    CRIME("Krimi");


    private String value;
    BookGenre(String value)
    {
        this.value = value;
    }
    public String getValue()
    {
        return value;
    }
}
