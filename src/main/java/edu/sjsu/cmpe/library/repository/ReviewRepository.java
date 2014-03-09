package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.concurrent.ConcurrentHashMap;

//import java.util.List;

//import java.util.Iterator;
//import java.util.Map;

import com.yammer.dropwizard.jersey.params.LongParam;

import edu.sjsu.cmpe.library.domain.Review;

public class ReviewRepository implements ReviewRepositoryInterface 
{	
	private final HashMap<Long, Review> reviewInMemoryMap;
	private long idKey;

	public ReviewRepository(HashMap<Long, Review> reviewMap) {
		checkNotNull(reviewMap, "reviewMap must not be null for reviewRepository");
		reviewInMemoryMap = reviewMap;
		idKey = 0;
	}
    
    private final Long generateIdKey() {
        return Long.valueOf(++idKey);
    }
    
	@Override
	public Review saveReview(LongParam isbn, Review newReview) 
	{
		checkNotNull(newReview, "newReview instance must not be null"); 
        long id = generateIdKey();
        newReview.setId(id);
        //reviewInMemoryMap.putIfAbsent(id, newReview);
		reviewInMemoryMap.put(id, newReview);
		System.out.println(reviewInMemoryMap.size());
        return newReview;
	}

	@Override
	public Review getReviewById(LongParam isbn, long id) 
	{
		checkArgument(id > 0, "ISBN was %s but expected greater than zero value", id);
		//    return reviewInMemoryMap.get(id);
		Review foundReview = new Review();
		foundReview = reviewInMemoryMap.get(id);
		return foundReview;
	}
	
	@Override
	public ArrayList<Review> getAllReviews()
	{
		ArrayList<Review> allReviews = new ArrayList<Review>();
		
		long id;
		String comment;
		Integer rating;
		
		for (long i = 1; i <= reviewInMemoryMap.size(); i++) {
			Review retrievedReview = new Review();
			id = reviewInMemoryMap.get(i).getId();
			retrievedReview.setId(id);
			comment = reviewInMemoryMap.get(i).getComment();
			retrievedReview.setComment(comment);
			rating = reviewInMemoryMap.get(i).getRating();
			retrievedReview.setRating(rating);
			allReviews.add(retrievedReview);
		}
		
		//HashMap hm = new HashMap();
		//hm = (HashMap) reviewInMemoryMap.values();
		for(long i = 1; i <= reviewInMemoryMap.size(); i++) {
			System.out.println(reviewInMemoryMap.get(i));
		}	
		return allReviews;
	}

}
