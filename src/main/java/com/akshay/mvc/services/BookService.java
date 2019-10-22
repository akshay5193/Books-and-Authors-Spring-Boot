package com.akshay.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.akshay.mvc.models.Book;
import com.akshay.mvc.repositories.BookRepository;


@Service
public class BookService {
	
	 // adding the book repository as a dependency
    private final BookRepository bookRepository;
    
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    // returns all the books
    public List<Book> allBooks() {
//        List<Book> allBooks = bookRepository.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.ASC,"created_at"));
    	return bookRepository.findAll();
    }
    
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    
    

    
    // updates book information
    public Book updateBook(Long id, String title, String description, String language , Integer numOfPages) {
    	
    	Optional<Book> findBook = bookRepository.findById(id);
    	if(findBook.isPresent()) {
    		Book bookToUpdate = new Book();
    		bookToUpdate = findBook.get();
    		
    		bookToUpdate.setTitle(title);
        	bookToUpdate.setDescription(description);
        	bookToUpdate.setLanguage(language);
        	bookToUpdate.setNumberOfPages(numOfPages);
        	
        	bookRepository.save(bookToUpdate);
        	return bookToUpdate;
        } 
    	else {
            return null;
        }
    }
    
    
public Book updateBook(Book book) {
    		
        	bookRepository.save(book);
        	return book;
    }
    
    
    public void deleteBook (Long id) {
    	bookRepository.deleteById(id);
//    	Optional<Book> findBook = bookRepository.findById(id);
//    	System.out.println("Checking if there is a book...");
//    	if(findBook.isPresent()) {
//    		System.out.println("Book Found!!!");
//    		bookRepository.deleteById(id);
//        } 
//    	else {
//            System.out.println("No such book to delete");
//        }
    }
}
