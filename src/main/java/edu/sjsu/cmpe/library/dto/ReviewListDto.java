package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe.library.domain.Review;

//@JsonPropertyOrder(alphabetic = true)
@JsonPropertyOrder({"reviews", "links"})
public class ReviewListDto extends LinksDto 
{    
	private ArrayList<Review> reviewList;
	private LinksDto reviewLinks = new LinksDto();

	@JsonCreator
    public ReviewListDto(ArrayList<Review> reviewList, LinksDto reviewLinks) {
		super();
		this.reviewList = reviewList;
		this.reviewLinks = reviewLinks;
	}
    
	@JsonProperty("reviews")
    public ArrayList<Review> getReviewList() {
    	return reviewList;
    }

    public void setReviewList(ArrayList<Review> reviewList) {
    	this.reviewList = reviewList;
    } 
    
    @JsonProperty("links")
    public LinksDto getReviewLinks() {
    	return reviewLinks;
    }
    
    public void setReviewLinks(LinksDto reviewLinks) {
    	this.reviewLinks = reviewLinks;
    }
}


