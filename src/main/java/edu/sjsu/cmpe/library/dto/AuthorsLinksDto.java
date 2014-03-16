package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
//import java.util.List;
import edu.sjsu.cmpe.library.domain.Author;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonPropertyOrder(alphabetic=true)
public class AuthorsLinksDto extends LinksDto 
{	
	private ArrayList<Author> authorsList;
	private LinksDto authorsLinks = new LinksDto();
   
    public AuthorsLinksDto(ArrayList<Author> authorsList, LinksDto authorsLinks) {
    	//super();
    	this.authorsList = authorsList;
    	this.authorsLinks = authorsLinks;
    }
      
    public void setAuthorList(ArrayList<Author> authorsList) {
    	this.authorsList = authorsList;
    }
    
    @JsonProperty("authors")
    public ArrayList<Author> getAuthorsList() {
    	return this.authorsList;
    }    

    public void setAuthorsLinks(LinksDto authorsLinks) {
    	this.authorsLinks = authorsLinks;
    }
    
    @JsonProperty("links")
    public LinksDto getAuthorsLinks() {
    	return this.authorsLinks;
    }
      
}
