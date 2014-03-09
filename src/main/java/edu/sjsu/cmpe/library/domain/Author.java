package edu.sjsu.cmpe.library.domain;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Author
{
	private String name;
	private Integer id;
	
	public Author() {}
	
	//
	//@JsonIgnore
	@JsonProperty("name")
	public String getAuthor() {
		return name;
	}
	
	public void setAuthor(String newAuthor){
		this.name = newAuthor;
	}
	
	//@JsonIgnore
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer newId) {
		this.id = newId;
	}
}