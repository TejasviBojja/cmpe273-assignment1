package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.domain.Author;


public class BookRepository implements BookRepositoryInterface {
   // private static final int Long = 0;


	//private static final String String = null;


	/** In-memory map to store books. (Key, Value) -> (ISBN, Book) */
    private final ConcurrentHashMap<Long, Book> bookInMemoryMap;
    
    
    /** Never access this key directly; instead use generateISBNKey() */
    private long isbnKey;
    private long idKey;
    private long author_idKey;
    

    public BookRepository(ConcurrentHashMap<Long, Book> bookMap) {
	checkNotNull(bookMap, "bookMap must not be null for BookRepository");
	bookInMemoryMap = bookMap;
	isbnKey = 0;
	idKey = 0;
	author_idKey = 0;
    }

   
    
    /**
     * This should be called if and only if you are adding new books to the
     * repository.
     * 
     * @return a new incremental ISBN number
     */
    private final Long generateISBNKey() {
	// increment existing isbnKey and return the new value
	return ++isbnKey;
    }


    private final Long generateIDKey() {
		// TODO Auto-generated method stub
		return ++idKey;
	}
	
    private final Long generateAUTHOR_IDKey() {
		// TODO Auto-generated method stub
		return ++author_idKey;
	}
	
    /**
     * This will auto-generate unique ISBN for new books.
     */
    @Override
    public Book saveBook(Book newBook) {
	checkNotNull(newBook, "newBook instance must not be null");
	// Generate new ISBN
	Long isbn = generateISBNKey();
	newBook.setIsbn(isbn);
	// TODO: create and associate other fields such as author
	Author authorobj = new Author();
	Long author_id = generateAUTHOR_IDKey();
	authorobj.setAuthor_id(author_id);
	// Finally, save the new book into the map
	bookInMemoryMap.putIfAbsent(isbn, newBook);

	return newBook;
    }
    
    

    @Override
    public void createReview(Long isbn, Review newReview){
    	//checkNotNull(newBook, "newBook instance must not be null");
    	//Review newReview = new Review(); 
    	//newReview.getRating();
    	//newReview.getComment();
    	
    	Long id = generateIDKey();
    	newReview.setId(id);
    
    	List<Review> reviewlist= new ArrayList<Review>();
    	reviewlist.add(newReview);
    	
    	Book book= getBookByISBN(isbn);
    	book.setReviewlist(reviewlist);
    	
    	//bookInMemoryMap.putifAbsent(isbn, newReview);
    	//return newReview;
    	saveBook(book);
    }
    


	/**
     * @see edu.sjsu.cmpe.library.repository.BookRepositoryInterface#getBookByISBN(java.lang.Long)
     */
    @Override
    public Book getBookByISBN(Long isbn) {
	checkArgument(isbn > 0,
		"ISBN was %s but expected greater than zero value", isbn);
	return bookInMemoryMap.get(isbn);
    }
    
    @Override
    public Book removeBookByISBN(Long isbn){
    	checkArgument(isbn > 0, "ISBN was %s but expected greater than zero value",isbn);
    	return bookInMemoryMap.remove(isbn);
    }
    
    @Override
    public Book updateBookByISBN(Long isbn, String status){
    	//checkArgument(isbn > 0, "ISBN was %s but expected greater than zero value",isbn);
    	Book book=getBookByISBN(isbn);
        book.setStatus(status);
       return book;
    	//return bookInMemoryMap.put(isbn,status);
    }
    
}
    
    

