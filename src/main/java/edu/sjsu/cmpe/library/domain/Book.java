package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"isbn", "title", "publication-date", "language", "num-pages", "status" })
public class Book 
{
    private long isbn;
    private String title;
    
    @JsonProperty("publication-date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private String date;

    private String language;
    private String status;
    
    //@JsonProperty("num-pages")
    private Integer num_pages;
    
    //@JsonProperty("authors")
    //private ArrayList<Author> authorList;
    
    //@JsonProperty("authors")
    private ArrayList<Author> authors;
    
    public Book() {}

    public long getIsbn() {
    	return isbn;
    }

    public void setIsbn(long isbn) {
    	this.isbn = isbn;
    }

    public String getTitle() {
    	return title;
    }

    public void setTitle(String title) {
    	this.title = title;
    }   

    public String getDate() {
    	return date;
    }
    public void setDate(String newDate) {
    	this.date = newDate;
    }

    public String getLanguage() {
    	return language;
    }

    public void setLanguage(String newLanguage) {
    	this.language = newLanguage;
    }
    
    @JsonProperty("num-pages")
    public Integer getNum_Pages() {
    	return num_pages;
    }

    public void setNum_Pages(Integer newNum_Pages) {
    	this.num_pages = newNum_Pages;
    }
    
    public String getStatus() {
    	return status;
    }

    public void setStatus(String newStatus) {
    	this.status = newStatus;
    }
    
    public void setAuthor(ArrayList<Author> newAuthorList) {
    	//this.authorList = newAuthorList;
    	this.authors = newAuthorList;
    }
    
    @JsonProperty("authors")
    public ArrayList<Author> getAuthor() {
    	//return authorList;
    	return authors;
    }
}
