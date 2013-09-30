package edu.sjsu.cmpe.library.domain;
//import com.fasterxml.jackson.annotation.JsonProperty;

public class Author {
	
	
	private long author_id;
    private String name;
    
	public long getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(long author_id) {
		this.author_id = author_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
    
}