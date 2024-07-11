package com.nagarro.librest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.librest.entities.Author;
import com.nagarro.librest.entities.Book;
import com.nagarro.librest.repository.BookRepo;
import com.nagarro.librest.service.BookService;



@RestController
public class BookController 
{
	@Autowired
	private BookRepo bookRepository;
	
	@Autowired
	private BookService bookService;
	
	
	@PostMapping(path="/book",consumes= {"application/json"})
//	public void addBook(@RequestBody Book book) 
	public ResponseEntity<Book> addBook(@RequestBody Book book) 
	
	{
//		Optional<Book> check=bookRepository.findById(book.getBookCode());
//		if(check.isEmpty())
//		{
//		bookRepository.save(book);
//		}
		return new ResponseEntity<Book>(bookService.addBook(book),HttpStatus.OK);
		
	}
	
	@GetMapping(path="/book")
	public List<Book> getBooks()
	{
		return bookRepository.findAll();
	}
	
	@PutMapping(path="/book",consumes= {"application/json"})
	public void saveOrUpdateBook(@RequestBody Book book) 
	{
		bookRepository.save(book);
		
	}
	
	@RequestMapping("/book/{bookCode}")
	public Optional<Book> getBook(@PathVariable("bookCode") int bookCode)
	{
		return bookRepository.findById(bookCode);

	}
	
	@DeleteMapping("/book/{bookCode}")
	public ResponseEntity<String> deleteBook(@PathVariable("bookCode") int bookCode)
	{
//		Book a=bookRepository.getById(bookCode);
//		bookRepository.delete(a);
		bookService.deleteBook(bookCode);
		return new ResponseEntity<String>("Book deleted successfully!.", HttpStatus.OK);
		
	}
}
