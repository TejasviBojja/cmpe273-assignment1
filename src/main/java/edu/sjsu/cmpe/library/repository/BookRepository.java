package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.ConcurrentHashMap;

import edu.sjsu.cmpe.library.domain.Book;
//import edu.sjsu.cmpe.library.domain.Review;


public class BookRepository implements BookRepositoryInterface {
    /** In-memory map to store books. (Key, Value) -> (ISBN, Book) */
    private final ConcurrentHashMap<Long, Book> bookInMemoryMap;
    //private final ConcurrentHashMap<Long, Review> reviewInMemoryMap;
    
    /** Never access this key directly; instead use generateISBNKey() */
    private long isbnKey;
  //  private long idKey;
    

    public BookRepository(ConcurrentHashMap<Long, Book> bookMap) {
	checkNotNull(bookMap, "bookMap must not be null for BookRepository");
	bookInMemoryMap = bookMap;
	isbnKey = 0;
    }

   /* public BookRepository(ConcurrentHashMap<Long, Review> reviewMap) {
    	checkNotNull(reviewMap, "reviewMap must not be null for BookRepository");
    	reviewInMemoryMap = reviewMap;
    	idKey = 0;
    	
        }
*/
    /**
     * This should be called if and only if you are adding new books to the
     * repository.
     * 
     * @return a new incremental ISBN number
     */
    private final Long generateISBNKey() {
	// increment existing isbnKey and return the new value
	return Long.valueOf(++isbnKey);
    }

/*
    private final Long generateIDKey() {
		// TODO Auto-generated method stub
		return Long.valueOf(++idKey);
	}
	*/
	
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

	// Finally, save the new book into the map
	bookInMemoryMap.putIfAbsent(isbn, newBook);

	return newBook;
    }
    
    
 /*
    @Override
    public Book createReview(Book newBook, Review newReview){
    	checkNotNull(newBook, "newBook instance must not be null");
    	Long id = generateIDKey();
    	newReview.setId(id);
    	
    	List<newReview> reviewlist= new ArrayList<newReview>();
    	reviewlist.add(newReview);
    	return reviewlist;
    	
    	//reviewInMemoryMap.putIfAbsent(id, newReview);
    	//return newReview;
    }
    
  */


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
    
    

