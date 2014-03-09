package edu.sjsu.cmpe.library.api.resources;

//import java.util.Map.Entry;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
//import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.*;
import edu.sjsu.cmpe.library.dto.*;
import edu.sjsu.cmpe.library.repository.ReviewRepositoryInterface;

@Path("/v1/books/{isbn}/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource 
{
	private final ReviewRepositoryInterface reviewRepository;
	 
    public ReviewResource(ReviewRepositoryInterface reviewRepository) 
    {
    	this.reviewRepository = reviewRepository;
    }
    
    @POST
    @Timed(name = "create-review")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createReview(@PathParam("isbn") LongParam isbn, Review request) throws Exception 
    {
		Review savedReview = reviewRepository.saveReview(isbn, request);
//		ReviewDto reviewResponse = new ReviewDto(savedReview);
//		reviewResponse.addLink(new LinkDto("view-review", "/books/"+ isbn + "/reviews/" + savedReview.getId(), "GET"));
//		System.out.println(savedReview.getId() + "\n" + savedReview.getRating() + "\n" +savedReview.getComment() + "\n" );
//		return Response.status(201).entity(reviewResponse.getLinks()).build();
    	if (savedReview == null) {
    		throw new Exception();
    	}
    	else {
    		LinksDto links = new LinksDto();
    		links.addLink(new LinkDto("view-review", "/books/" + isbn + "/reviews/" + savedReview.getId(), "GET"));
    		return Response.status(201).entity(links).build();
    	}
    }
    
    @GET
    @Path("/{id}")
    @Timed(name = "view-review")
    public ReviewDto getReviewById(@PathParam("isbn") LongParam isbn, @PathParam("id") long id )
    {
    	Review review = reviewRepository.getReviewById(isbn, id);
    	ReviewDto reviewResponse = new ReviewDto(review);
    	
    	reviewResponse.addLink(new LinkDto("view-review", "/books" + isbn + "/reviews/" + review.getId(), "GET"));
    	
    	//return Response.status(201).entity(reviewResponse.getLinks()).build();
    	return reviewResponse;
    }
    
    @GET
    @Timed(name = "view-all-reviews")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ReviewListDto getAllReviews() 
    {
    	ArrayList<Review> reviewList = reviewRepository.getAllReviews();
    	LinksDto reviewLinks = new LinksDto();
    	ReviewListDto reviewResponse = new ReviewListDto(reviewList, reviewLinks);
    	
    	//reviewResponse.addLink(new LinkDto());
    	
    	return reviewResponse;
    }
//    public Response getAllReviews()
//    {
//    	ArrayList<Review> reviewList = reviewRepository.getAllReviews();
//    	
//    	return Response.status(200).entity(reviewList).build();
//    }
//  public ArrayList<Review> getAllReviews() 
//  {
//  	ArrayList<Review> reviewList = reviewRepository.getAllReviews();
//  	//ReviewDto reviewReponse = new ReviewDto(reviewList);
//  	//reviewResponse.addLinl(new LinkDto()); 	
//  	return reviewList;	
//  }
    
   
}
