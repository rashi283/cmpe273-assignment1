package edu.sjsu.cmpe.library.api.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.*;
import edu.sjsu.cmpe.library.dto.*;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;


@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource 
{
    /** bookRepository instance */
    private final BookRepositoryInterface bookRepository;

    /* BookResource constructor
     * @param bookRepository a BookRepository instance */

    // BookResource(){}   
    public BookResource(BookRepositoryInterface bookRepository) 
    {
    	this.bookRepository = bookRepository;
    }
    
    /** API 1 - CREATE BOOK 
     * @throws Exception */
    @POST
    @Timed(name = "create-book")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book request) throws Exception 
    {
		Book savedBook = bookRepository.saveBook(request);
		if (savedBook == null) {
			throw new Exception();
		}
		else {
			LinksDto links = new LinksDto();
			String location = "/books/" + savedBook.getIsbn();
			links.addLink(new LinkDto("view-book", location, "GET"));
			links.addLink(new LinkDto("update-book", location, "PUT"));
			links.addLink(new LinkDto("delete-book", location, "DELETE"));
			links.addLink(new LinkDto("create-review", location + "/reviews", "POST"));
			
			return Response.status(201).entity(links).build();
		} 
			
    }
    
    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public CombinedDto getBookByIsbn(@PathParam("isbn") LongParam isbn, @Context Request req, @Context UriInfo ui) 
    {
		Book book = bookRepository.getBookByISBN(isbn.get());
		Book formattedBook = formatBook(book);
		//BookDto bookResponse = new BookDto(book);
		
		ArrayList<Author> authorList = book.getAuthor();
		List<LinkDto> authors = new ArrayList<LinkDto>();
		String location;
		
		for(Integer i = 0; i<authorList.size() ; i++) {
			location = "/books/" + isbn.get() + "/authors/" + (i+1);
			System.out.println(location);
			authors.add(new LinkDto("view-author", location, "GET"));		
		}
		
		CombinedDto comboResponse = new CombinedDto(authors);
		comboResponse.setBook(formattedBook);
		location = "/books/" + isbn.get();
		// add more links
//		LinksDto links = new LinksDto();
		comboResponse.addLink(new LinkDto("view-book", location,"GET"));
		comboResponse.addLink(new LinkDto("update-book","/books/" + formattedBook.getIsbn(), "PUT"));
		comboResponse.addLink(new LinkDto("delete-book", "/books/" + formattedBook.getIsbn(), "DELETE"));
		comboResponse.addLink(new LinkDto("create-review", "/books/" + formattedBook.getIsbn() + "/reviews", "POST"));
		comboResponse.addLink(new LinkDto("view-all-reviews", "/books/" + formattedBook.getIsbn() + "/reviews", "GET"));
		//bookResponse.addLink(new LinkDto("view-author", "books/" + book.getIsbn() + "/authors/1", "GET"));
			
		//return Response.ok(comboResponse).build();
		return comboResponse;
    }
    
    private Book formatBook(Book book) {
		Book mainBook = new Book();
		mainBook.setIsbn(book.getIsbn());
		mainBook.setLanguage(book.getLanguage());
		mainBook.setNum_Pages(book.getNum_Pages());
		mainBook.setDate(book.getDate());
		mainBook.setTitle(book.getTitle());
		mainBook.setStatus(book.getStatus());
		return mainBook;
	}
    
    /** API 3 - DELETE BOOK 
     * @throws Exception */
    @DELETE
    @Path("/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getBookByIsbnToDelete(@PathParam("isbn") LongParam isbn) throws Exception
    {
    	Book deletedBook = bookRepository.deleteBookByIsbn(isbn.get());
    	if (deletedBook == null) {
    		throw new Exception();
    	}
    	else {
    		LinksDto links = new LinksDto();
    		links.addLink(new LinkDto("create-book", "/books", "POST"));
    		return Response.ok(links).build();
    	}		
    }

    /** API 4 - UPDATE BOOK */
    // /books/{isbn}?status={new-status}
    @PUT
	@Path("/{isbn}")
	@Timed(name = "update-book")
	public Response updateBook(@PathParam("isbn") LongParam isbn, @Context UriInfo uriInfo, String content) throws Exception 
	{
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		Book updateResponse = bookRepository.getBookByISBN(isbn.get());
		for(Map.Entry<String, List<String>> entry : queryParams.entrySet()){
			bookRepository.updateBookInfo(updateResponse, entry);
		}
		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-book", "/books/" + isbn.toString() , "GET"));
		links.addLink(new LinkDto("update-book", "/books/" + isbn.toString() , "PUT"));
		links.addLink(new LinkDto("delete-book", "/books/" + isbn.toString() , "DELETE"));
		links.addLink(new LinkDto("create-review", "/books/" + isbn.toString() + "/reviews", "POST"));
		links.addLink(new LinkDto("view-all-reviews", "/books" + isbn.toString() + "/reviews", "GET"));

		return Response.ok(links).build();
	}

    /** API 8 - VIEW BOOK AUTHOR
     * @throws Exception */
    // /books/{isbn}/authors/{id}
    @GET
    @Path("/{isbn}/authors/{id}")
    @Timed(name = "view-author")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AuthorDto getAuthorById(@PathParam("isbn") LongParam isbn, @PathParam("id") Integer id) throws Exception 
    {
    	ArrayList<Author> authorList = bookRepository.getAuthorByIsbn(isbn.get());
    	Author retrievedAuthor = new Author();
    	for(Integer i = 0; i <= authorList.size(); i++) {
    		if(i==id) {
    			retrievedAuthor = authorList.get(i-1);
    			break; }
    		else {
    			continue; }
    	}
    	//LinkDto links = new LinkDto();
    	if(retrievedAuthor == null) {
    		throw new Exception();
    	}
    	else {
	    	AuthorDto authorResponse = new AuthorDto(retrievedAuthor);
	    	authorResponse.addLink(new LinkDto("view-author", "/books/"+ isbn.get() 
	    			+ "/authors/" + retrievedAuthor.getId(), "GET"));
	    	
	    	return authorResponse;
    	}
    }

    /** API 9 - VIEW ALL BOOK AUTHORS */
    @GET
    @Path("/{isbn}/authors")
    @Timed(name = "view-author")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AuthorsLinksDto getAllAuthors(@PathParam("isbn") LongParam isbn) 
    {	
    	ArrayList<Author> authorList = bookRepository.getAuthorByIsbn(isbn.get());
    	LinksDto links = new LinksDto();
    	//String links = "[]";
    	AuthorsLinksDto authorsLinksResponse = new AuthorsLinksDto(authorList, links);
    	return authorsLinksResponse;
    	//return authorResponse;
    }
        
}


