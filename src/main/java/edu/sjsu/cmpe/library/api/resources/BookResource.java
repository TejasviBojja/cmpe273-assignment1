package edu.sjsu.cmpe.library.api.resources;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;


//import edu.sjsu.cmpe.library.domain.Author;
//import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    /** bookRepository instance */
    private final BookRepositoryInterface bookRepository;

    /**
     * BookResource constructor
     * 
     * @param bookRepository
     *            a BookRepository instance
     */
    public BookResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn) {
	Book book = bookRepository.getBookByISBN(isbn.get());
	BookDto bookResponse = new BookDto(book);
	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),
		"GET"));
	bookResponse.addLink(new LinkDto("update-book",
		"/books/" + book.getIsbn(), "PUT"));
	
	bookResponse.addLink(new LinkDto("delete-book", "/books/" + book.getIsbn(),
			"DELETE"));
	bookResponse.addLink(new LinkDto("create-review", "/books/" + book.getIsbn(),
			"POST"));
	bookResponse.addLink(new LinkDto("view-all-reviews", "/books/" + book.getIsbn(),
			"GET"));
	// add more links

	return bookResponse;
    }

    @POST
    @Path("/{isbn}")
    @Timed(name = "create-book")
    public Response createBook(Book request) {
	// Store the new book in the BookRepository so that we can retrieve it.
	Book savedBook = bookRepository.saveBook(request);

	String location = "/books/" + savedBook.getIsbn();
	BookDto bookResponse = new BookDto(savedBook);
	bookResponse.addLink(new LinkDto("view-book", location, "GET"));
	bookResponse.addLink(new LinkDto("update-book", location, "PUT"));
	bookResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
	bookResponse.addLink(new LinkDto("create-review", location, "POST"));
	// Add other links if needed

	return Response.status(201).entity(bookResponse).build();
    }
    
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public BookDto removeBookByISBN(@PathParam("isbn") LongParam isbn) {
	Book book = bookRepository.removeBookByISBN(isbn.get());
	BookDto bookResponse = new BookDto(book);
	bookResponse.addLink(new LinkDto("create-book", "/books/" + book.getIsbn(),
			"POST"));
	return bookResponse;
    }
    
			
	    @PUT
	    @Path("/{isbn}")
	    @Timed(name = "update-book")
	    public BookDto updateBookByISBN(@PathParam("isbn") LongParam isbn,@QueryParam("status") String status){
		Book book = bookRepository.updateBookByISBN(isbn.get(),status);
		BookDto bookResponse = new BookDto(book);
		bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),
				"GET"));
		bookResponse.addLink(new LinkDto("update-book", "/books/" + book.getIsbn(),
				"PUT"));
		bookResponse.addLink(new LinkDto("delete-book", "/books/" + book.getIsbn(),
				"DELETE"));
		bookResponse.addLink(new LinkDto("create-review", "/books/" + book.getIsbn(),
				"POST"));
		bookResponse.addLink(new LinkDto("view-all-reviews", "/books/" + book.getIsbn(),
				"GET"));
		return bookResponse;
	    }
	
	    /*
	     * 
	     */
	    
	    
	    @POST
	    @Path("/{isbn}/{reviews}")
	    @Timed(name = "create-book-review")
	    public ReviewDto createReview(@PathParam("isbn") LongParam isbn, Review newReview){
	    
		// Store the new book in the BookRepository so that we can retrieve it.
	    
		bookRepository.createReview(isbn.get(),newReview);
		
		ReviewDto reviewResponse = new ReviewDto(newReview);
	    reviewResponse.addLink(new LinkDto("view-review","/books/isbn/reviews/","GET"));
		return reviewResponse;
		
		// Add other links if needed
		
	    }
	    
	    @GET
	    @Path("/{isbn}/{reviews}/{id}")
	    @Timed(name = "view-book-review")
	    public Response getReviewByIsbn(@PathParam("isbn") LongParam isbn){
	    	
	    	//@PathParam("isbn") LongParam isbn
	    	
		Book book = bookRepository.getBookByISBN(isbn.get());
		
		List<Review> getreview=new ArrayList<Review>();
		getreview=book.getReviewlist();
	    
	    return Response.status(201).entity(getreview).build();
	    
		// add more links

	    }
	    
	    
	    
	    @GET
	    @Path("{isbn}/{reviews}")	
	    @Timed(name = "view-all-reviews")
	    public Response getallReviewsbyIsbn(@PathParam("isbn") LongParam isbn){
	    	
	    	Book book = bookRepository.getBookByISBN(isbn.get());
	    	
        	List<Review> getreviews=new ArrayList<Review>();
        	
	    	getreviews=book.getReviewlist();
	 
	    	return Response.status(201).entity(getreviews).build();
	    	}
	    
	  /*  
	    @GET
	    @Path("/{isbn}/{authors}/{author_id}")
	    @Timed(name = "view-book-author")
	    public Response getAuthorByISBN(@PathParam("isbn") LongParam isbn){
	    	
	    	//@PathParam("isbn") LongParam isbn
	    
		Book book = bookRepository.getBookByISBN(isbn.get());
		
		List<Author> getauthor=new ArrayList<Author>();
		getauthor=book.getAuthorlist();
	    
	    return Response.status(201).entity(getauthor).build();
	    
		// add more links

	    }
	    */
	    
	    /*
	    
	    @GET
	    @Path("/{isbn}/{authors}/")
	    @Timed(name = " view-all-authors")
	    public Response getallAuthorsByIsbn(@PathParam("isbn") LongParam isbn){
	    	
	    	Book book = bookRepository.getBookByISBN(isbn.get());
	    	
	    	List<Author> getauthors=new ArrayList<Author>();
	    	getauthors=book.getAuthorlist();
	    	
	    return Response.status(201).entity(getauthors).build();
	    	
	    }
	    
	   */ 
	// add more links
	 }
	

