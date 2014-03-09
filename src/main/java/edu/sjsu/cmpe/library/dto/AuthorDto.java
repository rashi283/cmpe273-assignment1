package edu.sjsu.cmpe.library.dto;

//import java.util.ArrayList;
//import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.sjsu.cmpe.library.domain.Author;
//import edu.sjsu.cmpe.library.domain.Book;

//@JsonPropertyOrder(alphabetic = true)
@JsonPropertyOrder({"author", "links"})
public class AuthorDto extends LinksDto 
{    
	private Author author;
   
    //@param book
    public AuthorDto(Author author) {
		super();
		this.author = author;
    }
    
    //@return the book
    public Author getAuthor() {
    	return author;
    }

    //@param book - the book to set
    public void setAuthor(Author author) {
    	this.author = author;
    }
}
    
