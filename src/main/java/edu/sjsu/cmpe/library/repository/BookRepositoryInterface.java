package edu.sjsu.cmpe.library.repository;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.List;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Author;


/* Book repository interface.
 * What is repository pattern?
 * @see http://martinfowler.com/eaaCatalog/repository.html */

public interface BookRepositoryInterface 
{    
	/* * Save a new book in the repository
     * @param newBook - a book instance to be create in the repository
     * @return a newly created book instance with auto-generated ISBN */
    Book saveBook(Book newBook);

    /* Retrieve an existing book by ISBN
     * @param isbn - a valid ISBN
     * @return a book instance */
    Book getBookByISBN(Long isbn);

    // TODO: add other operations here!
    //Boolean deleteBook(Book delBook);
    Book deleteBookByIsbn(Long isbn);
    
    void updateBookInfo(Book book, Entry<String, List<String>> entry);
    
    ArrayList<Author> getAuthorByIsbn(Long isbn);
}
