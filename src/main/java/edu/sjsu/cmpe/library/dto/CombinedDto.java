package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;
//import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
//import edu.sjsu.cmpe.library.domain.Review;

//@JsonPropertyOrder(alphabetic = true)
@JsonPropertyOrder({"book", "reviews", "authors"})
public class CombinedDto extends LinksDto 
{    
	private Book book;
	
	List<LinkDto> reviews = new ArrayList<LinkDto>();
	
	//private Author author;
	
	List<LinkDto> authors = new ArrayList<LinkDto>();
	
	//ArrayList<Author> authorList;
   
    //@param book
//    public CombinedDto(Author author) {
//		super();
//		this.author = author;
//    }
    
    public CombinedDto(List<LinkDto> authorLinks, List<LinkDto>reviewLinks) {
    	this.authors = authorLinks;
    	this.reviews = reviewLinks;
    }
    
//    public CombinedDto(ArrayList<Author> authorList) {
//    	this.authorList = authorList;
//    }

    //@return the book
//    public Author getAuthor() {
//    	return author;
//    }
//
//    //@param book - the book to set
//    public void setAuthor(Author author) {
//    	this.author = author;
//    }
    
    @JsonProperty("book")
    public Book getBook() {
    	return this.book;
    }
    
    public void setBook(Book newBook) {
    	this.book = newBook;
    }
    
//    public List<LinkDto> getAuthors() {
//    	return this.authorLinks;
//    }
//    
//    public void setAuthors(List<LinkDto> authors) {
//    	this.authorLinks = authors;
//    }    
    
    public void setLink(List<LinkDto> reviewLinks) {
    	this.reviews = reviewLinks;
    }
    
    @JsonProperty("reviews")
    public List<LinkDto> getLink() {
    	return reviews;
    }
    public void setAuthorLinks(List<LinkDto> authorLinks) {
    	this.authors = authorLinks;
    }
    
    @JsonProperty("authors")
    public List<LinkDto> getAuthorLinks() {
    	return authors;
    }
}
