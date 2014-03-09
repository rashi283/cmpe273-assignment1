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
//	private Book book;
//	private List<LinkDto> authorLinks = new ArrayList<LinkDto>();
//	ArrayList<Author> authorList;
   
    //@param book
    public AuthorDto(Author author) {
		super();
		this.author = author;
    }
    
//    public AuthorDto(List<LinkDto> authors) {
//    	this.authorLinks = authors;
//    }
//    
//    public AuthorDto(ArrayList<Author> authorList) {
//    	this.authorList = authorList;
//    }

    //@return the book
    public Author getAuthor() {
    	return author;
    }

    //@param book - the book to set
    public void setAuthor(Author author) {
    	this.author = author;
    }
}
    
//    public Book getBook() {
//    	return this.book;
//    }
//    
//    public void setBook(Book newBook) {
//    	this.book = newBook;
//    }
//    
//    public List<LinkDto> getAuthors() {
//    	return this.authorLinks;
//    }
//    
//    public void setAuthors(List<LinkDto> authors) {
//    	this.authorLinks = authors;
//    }
//      
//}
