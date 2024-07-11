package com.nagarro.LibMang.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.LibMang.controller.AddController;
import com.nagarro.LibMang.entities.Author;
import com.nagarro.LibMang.entities.Book;
import com.nagarro.LibMang.service.AuthorService;
import com.nagarro.LibMang.service.BookService;

@ExtendWith(MockitoExtension.class)
public class AddControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private AuthorService authorService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private AddController addController;
    
    private List<Author> authors;
    
    @Mock
    private Book book;

    @Test
    public void testEditBook() throws Exception {
    	
    	String authorName = "author1";
        // Mocking current date
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        Month month = currentDate.getMonth();
        int year = currentDate.getYear();
        String expectedDate = day + " " + month + " " + year;
        
        Author author = new Author();
        author.setName(authorName);
        when(authorService.retrieveAuthors()).thenReturn(Arrays.asList(author));

        // Calling the method under test
        ModelAndView mv = addController.editBook(request, response);
        

        // Assertions
        assertEquals("AddBook", mv.getViewName());
        assertEquals(Arrays.asList(author), mv.getModel().get("Author"));
//        assertEquals(authors, mv.getModel().get("Author"));
        assertEquals(expectedDate, mv.getModel().get("date"));
    }

    @Test
  public void testAddBook() throws Exception {
      String bookCode = "123";
      String bookName = "Book1";
      String author = "Author1";
      String addedOn = "15 JUNE 2024";

      when(request.getParameter("bookCode")).thenReturn(bookCode);
      when(request.getParameter("bookName")).thenReturn(bookName);
      when(request.getParameter("author")).thenReturn(author);
      when(request.getParameter("addedOn")).thenReturn(addedOn);
      when(request.getSession()).thenReturn(session);

      List<Book> books = Arrays.asList(book);;
      when(bookService.retrieveBooks()).thenReturn(books);

      ModelAndView mv = addController.addBook(request, response);

      assertEquals("BookListing", mv.getViewName());

      verify(book).setAddedOn(addedOn);
      verify(book).setAuthor(author);
      verify(book).setBookCode(Integer.parseInt(bookCode));
      verify(book).setBookName(bookName);

      verify(bookService, times(1)).saveBook(book, "POST");
      verify(session, times(1)).setAttribute("books", books);
  }


}




















//
//import static org.mockito.Mockito.*;
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import static org.mockito.Mockito.*;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.nagarro.LibMang.controller.AddController;
//import com.nagarro.LibMang.entities.Author;
//import com.nagarro.LibMang.entities.Book;
//import com.nagarro.LibMang.service.AuthorService;
//import com.nagarro.LibMang.service.BookService;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AddControllerTest {
//
//	@Mock
//    private Book book;
//
//    @Mock
//    private AuthorService authorService;
//
//    @Mock
//    private BookService bookService;
//
//    @Mock
//    private HttpServletRequest request;
//
//    @Mock
//    private HttpServletResponse response;
//
//    @Mock
//    private HttpSession session;
//
//    @InjectMocks
//    private AddController addController;
//
//    @Before
//    public void setUp() {
////        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testEditBook() throws Exception {
//        Author author1 = new Author();
//        author1.setName("author1");
//        Author author2 = new Author();
//        author2.setName("author2");
//
//        List<Author> authors = Arrays.asList(author1, author2);
//        when(authorService.retrieveAuthors()).thenReturn(authors);
//
//        ModelAndView mv = addController.editBook(request, response);
//
//        assertEquals("AddBook", mv.getViewName());
//        assertEquals(authors, mv.getModel().get("Author"));
//
//        LocalDate currentDate = LocalDate.now();
//        int day = currentDate.getDayOfMonth();
//        Month month = currentDate.getMonth();
//        int year = currentDate.getYear();
//        String expectedDate = day + " " + month + " " + year;
//
//        assertEquals(expectedDate, mv.getModel().get("date"));
//    }
//    
//    @Test
//    public void testAddBook() throws Exception {
//        String bookCode = "123";
//        String bookName = "Book1";
//        String author = "Author1";
//        String addedOn = "15 JUNE 2024";
//
//        when(request.getParameter("bookCode")).thenReturn(bookCode);
//        when(request.getParameter("bookName")).thenReturn(bookName);
//        when(request.getParameter("author")).thenReturn(author);
//        when(request.getParameter("addedOn")).thenReturn(addedOn);
//        when(request.getSession()).thenReturn(session);
//
//        List<Book> books = Arrays.asList(book);;
//        when(bookService.retrieveBooks()).thenReturn(books);
//
//        ModelAndView mv = addController.addBook(request, response);
//
//        assertEquals("BookListing", mv.getViewName());
//
//        verify(book).setAddedOn(addedOn);
//        verify(book).setAuthor(author);
//        verify(book).setBookCode(Integer.parseInt(bookCode));
//        verify(book).setBookName(bookName);
//
//        verify(bookService, times(1)).saveBook(book, "POST");
//        verify(session, times(1)).setAttribute("books", books);
//    }
//
//}
