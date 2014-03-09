package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map.Entry;
import java.util.List;

//import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;

public class BookRepository implements BookRepositoryInterface {
    
	/** In-memory map to store books. (Key, Value) -> (ISBN, Book) */
    private final ConcurrentHashMap<Long, Book> bookInMemoryMap;

    /** Never access this key directly; instead use generateISBNKey() */
    private long isbnKey;

    public BookRepository(ConcurrentHashMap<Long, Book> bookMap) 
    {
		checkNotNull(bookMap, "bookMap must not be null for BookRepository");
		bookInMemoryMap = bookMap;
		isbnKey = 0;
    }

    private final Long generateISBNKey() {
		return Long.valueOf(++isbnKey);
    }

    @Override
    public Book saveBook(Book newBook) 
    {
    	checkNotNull(newBook, "newBook instance must not be null");	
		Long isbn = generateISBNKey();
		newBook.setIsbn(isbn);
		// TODO: create and associate other fields such as author
		ArrayList<Author> newAuthor = newBook.getAuthor();
		for(Integer i=0; i<newAuthor.size(); i++) {
			//System.out.println(newAuthor.get(i).getAuthor());
			newAuthor.get(i).setId(i+1);
			//System.out.println(newAuthor.get(i).getId());
		}
			
		// Finally, save the new book into the map
		bookInMemoryMap.putIfAbsent(isbn, newBook);
	
		return newBook;
    }

//    @Override
//    public Boolean deleteBook(Book delBook)
//    {
//    	//Long isbn;	
//        checkNotNull(delBook, "Delete Book instance must not be null");
//        bookInMemoryMap.remove(delBook);
//        return true;
//    }
    @Override
    public Book deleteBookByIsbn(Long isbn) {
    	checkArgument( isbn > 0, "ISBN was %s but expected value greater than zero", isbn);
    	Book oldBook = bookInMemoryMap.remove(isbn);
    	return oldBook;
    }
    
    //BookRepositoryInterface#getBookByISBN(java.lang.Long)
    @Override
    public Book getBookByISBN(Long isbn) {
	checkArgument(isbn > 0,
		"ISBN was %s but expected greater than zero value", isbn);
	return bookInMemoryMap.get(isbn);
    }
    
    public void updateBookInfo(Book book, Entry<String, List<String>> entry) {
    	String str = entry.getValue().toString();
    	if (entry.getKey().equals("status")) {
    		book.setStatus(str.substring(1, str.length()));
    	}
    	else if (entry.getKey().equals("title")) {
    		book.setTitle(str.substring(1, str.length()));
    	}
    	else if(entry.getKey().equals("language")) {
    		book.setLanguage(str.substring(1, str.length()));
    	}
    	else if(entry.getKey().equals("publication-date")) {
    		book.setDate(str.substring(1, str.length()));
    	}
    }
    
    public ArrayList<Author> getAuthorByIsbn(Long isbn)
    {
    	Book newBook = bookInMemoryMap.get(isbn);
    	ArrayList<Author> authorList = newBook.getAuthor();
    	return authorList;
    }
}
