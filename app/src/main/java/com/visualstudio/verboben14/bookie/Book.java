package com.visualstudio.verboben14.bookie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by verboben14 on 2017. 04. 18..
 */

public class Book {
    private String title;
    private String author;
    private int dateOfIssue;
    private List<BookGenre> categories;
    private String isbn;
    private String publisher;
    private int pageCount;
    private String language;
    private int weight;                     //  in grams

    public Book() {     //  default constructor
        this.title = "";
        this.author = "";
        this.dateOfIssue = 0;
        this.categories = new ArrayList<BookGenre>();
        this.isbn = "";
        this.publisher = "";
        this.pageCount = 0;
        this.language = "";
        this.weight = 0;
    }

    public Book(String title, String author, int dateOfIssue, List<BookGenre> categories, String isbn, String publisher, int pageCount, String language, int weight) {
        this.title = title;
        this.author = author;
        this.dateOfIssue = dateOfIssue;
        this.categories = categories;
        this.isbn = isbn;
        this.publisher = publisher;
        this.pageCount = pageCount;
        this.language = language;
        this.weight = weight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(int dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getCategories() {
        String categoriesString = "";
        for (int i = 0; i < this.categories.size(); i++)
        {
            categoriesString += this.categories.get(i).getValue() + ", ";
        }
        return categoriesString.substring(0, categoriesString.length()-3);
    }

    public void addCategory(BookGenre bookGenre) {
        this.categories.add(bookGenre);
    }

    public void removeCategory(BookGenre bookGenre)
    {
        int i = 0;
        while(i < this.categories.size() && !categories.get(i).getValue().equals(bookGenre.getValue()))
        {
            i++;
        }
        if(i < this.categories.size())
        {
            this.categories.remove(i);
        }
    }

    public void removeAllCategories()
    {
        this.categories.clear();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
