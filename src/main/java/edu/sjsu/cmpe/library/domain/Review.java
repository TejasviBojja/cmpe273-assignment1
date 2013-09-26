package edu.sjsu.cmpe.library.domain;
//import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {
	
	private long id;
    private String rating;
    private String comment;
    
   
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
    
}