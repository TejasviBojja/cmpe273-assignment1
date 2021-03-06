package edu.sjsu.cmpe.library.domain;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
   

	private long isbn;
    private String title;
    @JsonProperty ("publication-date")
    private String publicationdate;
    private String language;
    @JsonProperty("num-pages")
    private String numpages;
    private String status="available";
    @JsonProperty("reviews")
    List<Review> reviewlist = new ArrayList<Review>();
    @JsonProperty("authors")
    List<Author> authorlist = new ArrayList<Author>();
    
	public List<Review> getReviewlist() {
		return reviewlist;
	}

	public void setReviewlist(List<Review> reviewlist) {
		this.reviewlist = reviewlist;
	}

	public List<Author> getAuthorlist() {
		return authorlist;
	}

	public void setAuthorlist(List<Author> authorlist) {
		this.authorlist = authorlist;
	}

	
    
    // add more fields here

    /**
     * @return the isbn
     */
    public long getIsbn() {
	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
    
    public String getPublicationdate() {
		return publicationdate;
	}

	public void setPublicationdate(String publicationdate) {
		this.publicationdate = publicationdate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getNumpages() {
		return numpages;
	}

	public void setNumpages(String numpages) {
		this.numpages = numpages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
