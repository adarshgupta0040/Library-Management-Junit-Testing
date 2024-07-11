package com.nagarro.librest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.librest.entities.Author;
import com.nagarro.librest.entities.Book;
import com.nagarro.librest.exceptions.ValidationException;
import com.nagarro.librest.repository.BookRepo;

@Service
public class BookService {
	
	@Autowired
	private BookRepo bookRepository;

	public Book addBook(Book book) {
		
		Optional<Book> existingBook = bookRepository.findById(book.getBookCode());
		if(existingBook.isEmpty())
		{
			return bookRepository.save(book);
		}
		else {
			throw new ValidationException("Book with code " + book.getBookCode() + " already exists.");
		}
		
//		List<Book> existingBooks = bookRepository.findAll();  // Replace with your actual method to fetch existing books
//	    
//	    // Check if a book with the same ID already exists
//	    for (Book existingBook : existingBooks) {
//	        if (existingBook.getBookCode() == book.getBookCode()) {
//	            System.out.println("Book with ID " + book.getBookCode() + " already exists. Not adding.");
//	            throw new ValidationException("Book with code " + book.getBookCode() + " already exists.");
//	        }
//	    }
//	    
//	    // If not, save the new book
//	    return bookRepository.save(book);
	}


	public void deleteBook(int bookCode) {
        Book book = bookRepository.getById(bookCode);
        if (book == null) {
            throw new ValidationException("Book with code " + bookCode + " does not exist.");
        }
        bookRepository.delete(book);
    }

}
