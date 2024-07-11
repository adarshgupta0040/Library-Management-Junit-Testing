package com.nagarro.LibMang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.LibMang.entities.Author;
import com.nagarro.LibMang.entities.Book;
import com.nagarro.LibMang.service.AuthorService;
import com.nagarro.LibMang.service.BookService;

@Controller
public class EditController {
	
//	@Autowired
//	Book book;
	@Autowired
	AuthorService authors;
	@Autowired
	BookService bookService;
	
	
	@PostMapping("/Edit") // for post method mapping
	public ModelAndView edit_Book(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		
		String bookcode = request.getParameter("bookcode");
		int bookCode=Integer.parseInt(bookcode);
		String bookname = request.getParameter("bookname");
		String author = request.getParameter("author");
		String addedon = request.getParameter("addedOn");

		Book book = new Book(); 
		book.setAddedOn(addedon);
		book.setAuthor(author);
		book.setBookCode(bookCode);
		book.setBookName(bookname);
		
		mv.addObject("book",book);
		List<Author> Author=authors.retrieveAuthors();
		
		mv.addObject("Author",Author);
		mv.setViewName("EditBook");
		
		return mv;
	}
	
	// Edit Book Controller
	
	@PostMapping("/Editbook") // for post method mapping
	public ModelAndView add_Book(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		String bookcode = request.getParameter("bookCode");
		int bookCode=Integer.parseInt(bookcode);
		String bookname = request.getParameter("bookName");
		String author = request.getParameter("author");
		String addedon = request.getParameter("addedOn");
      
		    
		Book book = new Book(); 
		book.setAddedOn(addedon);
		book.setAuthor(author);
		book.setBookCode(bookCode);
		book.setBookName(bookname);
		
		
		System.out.println(book);
		bookService.saveBook(book, "PUT");
		HttpSession session = request.getSession();
		if(session ==null) {
			mv.setViewName("AddBook");;
		}
		List<Book> books= bookService.retrieveBooks();
		session.setAttribute("books", books);
		mv.setViewName("BookListing");
		
		return mv;
	}
	
	
	// Delete Book Controller

	@PostMapping("/Delete") // for post method mapping
	public ModelAndView deleteBook(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		
		String bookCode = request.getParameter("bookCode");
		int bookcode=Integer.parseInt(bookCode);
			
		    bookService.deleteBook(bookcode);
			HttpSession session = request.getSession();
			List<Book> books= bookService.retrieveBooks();
			session.setAttribute("books", books);
			mv.setViewName("BookListing");

		return mv;
	}
	
	@RequestMapping(path="/BookListing",method=RequestMethod.POST)
	public String Home() {
		return "BookListing";
	}
}
