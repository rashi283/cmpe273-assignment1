package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;


//import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;

//@JsonPropertyOrder(alphabetic = true)
@JsonPropertyOrder({"review", "links"})
public class ReviewDto extends LinksDto 
{    
	private Review review;
	ArrayList<Review> reviewList;
   
	//public ReviewDto() {}
	
	@JsonCreator
    public ReviewDto(Review review) {
		super();
		this.review = review;
    }
	
	
    public Review getReview() {
    	return review;
    }

    public void setReview(Review review) {
    	this.review = review;
    }
    
//    public ReviewDto(ArrayList<Review> reviewList) {
//		super();
//		this.reviewList = reviewList;
//	}
    
    //@JsonProperty("review")
//    public ArrayList<Review> getReviewList() {
//    	return reviewList;
//    }
//
//    public void setReviewList(ArrayList<Review> reviewList) {
//    	this.reviewList = reviewList;
//    }
    
}
