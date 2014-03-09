package edu.sjsu.cmpe.library.repository;

import java.util.ArrayList;

import com.yammer.dropwizard.jersey.params.LongParam;

import edu.sjsu.cmpe.library.domain.Review;

public interface ReviewRepositoryInterface 
{
	Review saveReview(LongParam isbn, Review review);
	Review getReviewById(LongParam isbn, long id);
	ArrayList<Review> getAllReviews();

}
