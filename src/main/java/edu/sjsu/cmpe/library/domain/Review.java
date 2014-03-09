package edu.sjsu.cmpe.library.domain;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Review
{
	private long id;
	
	private Integer rating;
	
	@JsonProperty("comment")
	private String comment;
	
	public Review() {}
	
	public Review(long id)
	{
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer newRating) {
		this.rating = newRating;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String newComment) {
		this.comment = newComment;
	}
}