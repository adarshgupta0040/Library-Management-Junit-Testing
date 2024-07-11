package com.nagarro.LibMang.Controller;

import com.nagarro.LibMang.controller.EditController;
import com.nagarro.LibMang.entities.Author;
import com.nagarro.LibMang.entities.Book;
import com.nagarro.LibMang.service.AuthorService;
import com.nagarro.LibMang.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EditControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private AuthorService authorService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private EditController editController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testEditBook() throws Exception {
    	Book book = new Book();
        String bookCode = "123";
        String bookName = "BookName";
        String authorName = "AuthorName";
        String addedOn = "2023-01-01";

        // Mocking request parameters
        when(request.getParameter("bookcode")).thenReturn(bookCode);
        when(request.getParameter("bookname")).thenReturn(bookName);
        when(request.getParameter("author")).thenReturn(authorName);
        when(request.getParameter("addedOn")).thenReturn(addedOn);

        // Mocking author retrieval
        Author author = new Author();
        author.setName(authorName);
        when(authorService.retrieveAuthors()).thenReturn(Arrays.asList(author));

        // Executing the method under test
        ModelAndView mv = editController.edit_Book(request, response);

        // Asserting the returned ModelAndView
        assertEquals("EditBook", mv.getViewName());
        assertEquals(Arrays.asList(author), mv.getModel().get("Author"));
        assertEquals(Book.class, mv.getModel().get("book").getClass());

        // Verifying interactions with mocks
//        verify(bookService).saveBook(any(Book.class), "PUT");
//        verify(session).setAttribute("books", anyList());
    }

    @Test
    public void testAddBook() throws Exception {
    	String bookCode = "123";
        String bookName = "BookName";
        String authorName = "AuthorName";
        String addedOn = "2023-01-01";

        // Mocking request parameters
        when(request.getParameter("bookCode")).thenReturn(bookCode);
        when(request.getParameter("bookName")).thenReturn(bookName);
        when(request.getParameter("author")).thenReturn(authorName);
        when(request.getParameter("addedOn")).thenReturn(addedOn);

        // Mocking book retrieval
        List<Book> books = Arrays.asList(new Book());
        when(bookService.retrieveBooks()).thenReturn(books);

        // Mocking HttpSession
        when(request.getSession()).thenReturn(session);

        // Executing the method under test
        ModelAndView mv = editController.add_Book(request, response);

        // Asserting the returned ModelAndView
        assertEquals("BookListing", mv.getViewName());

    }

    @Test
    public void testDeleteBook() throws Exception {
        String bookCode = "123";

        // Mocking request parameters
        when(request.getParameter("bookCode")).thenReturn(bookCode);

        // Mocking book retrieval
        List<Book> books = Arrays.asList(new Book());
        when(bookService.retrieveBooks()).thenReturn(books);
        
        // Mocking HttpSession
        when(request.getSession()).thenReturn(session);

        // Executing the method under test
        ModelAndView mv = editController.deleteBook(request, response);

        // Asserting the returned ModelAndView
        assertEquals("BookListing", mv.getViewName());

        // Verifying interactions with mocks
//        verify(bookService).deleteBook(Integer.parseInt(bookCode));
//        verify(session).setAttribute(eq("books"), anyList());
    }

    @Test
    public void testHome() {
        // Executing the method under test
        String viewName = editController.Home();

        // Asserting the returned view name
        assertEquals("BookListing", viewName);
    }
}










































//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import static org.mockito.Mockito.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.nagarro.LibMang.controller.EditController;
//import com.nagarro.LibMang.entities.Author;
//import com.nagarro.LibMang.entities.Book;
//import com.nagarro.LibMang.service.AuthorService;
//import com.nagarro.LibMang.service.BookService;
//
//@RunWith(MockitoJUnitRunner.class)
//public class EditControllerTest {
//    @Mock
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
//    private EditController editController;
//
//    @Test
//    public void testEditBook() throws Exception {
//        String bookCode = "123";
//        String bookName = "BookName";
//        String author = "AuthorName";
//        String addedOn = "2023-01-01";
//
//        when(request.getParameter("bookcode")).thenReturn(bookCode);
//        when(request.getParameter("bookname")).thenReturn(bookName);
//        when(request.getParameter("author")).thenReturn(author);
//        when(request.getParameter("addedOn")).thenReturn(addedOn);
//
//        Author author1 = new Author();
//        author1.setName("author1");
//        Author author2 = new Author();
//        author2.setName("author2");
//        List<Author> authors = Arrays.asList(author1, author2);
//        when(authorService.retrieveAuthors()).thenReturn(authors);
//
//        ModelAndView mv = editController.edit_Book(request, response);
//
//        assertEquals("EditBook", mv.getViewName());
//        assertEquals(authors, mv.getModel().get("Author"));
//        assertEquals(book, mv.getModel().get("book"));
//
//        verify(book).setAddedOn(addedOn);
//        verify(book).setAuthor(author);
//        verify(book).setBookCode(Integer.parseInt(bookCode));
//        verify(book).setBookName(bookName);
//    }
//
//    @Test
//    public void testAddBook() throws Exception {
//        String bookCode = "123";
//        String bookName = "BookName";
//        String author = "AuthorName";
//        String addedOn = "2023-01-01";
//
//        when(request.getParameter("bookCode")).thenReturn(bookCode);
//        when(request.getParameter("bookName")).thenReturn(bookName);
//        when(request.getParameter("author")).thenReturn(author);
//        when(request.getParameter("addedOn")).thenReturn(addedOn);
//        when(request.getSession()).thenReturn(session);
//
//        List<Book> books = Arrays.asList(book);
//        when(bookService.retrieveBooks()).thenReturn(books);
//
//        ModelAndView mv = editController.add_Book(request, response);
//
//        assertEquals("BookListing", mv.getViewName());
//
//        verify(book).setAddedOn(addedOn);
//        verify(book).setAuthor(author);
//        verify(book).setBookCode(Integer.parseInt(bookCode));
//        verify(book).setBookName(bookName);
//
//        verify(bookService, times(1)).saveBook(book, "PUT");
//        verify(session, times(1)).setAttribute("books", books);
//    }
//
//    @Test
//    public void testDeleteBook() throws Exception {
//        String bookCode = "123";
//
//        when(request.getParameter("bookCode")).thenReturn(bookCode);
//        when(request.getSession()).thenReturn(session);
//
//        List<Book> books = Arrays.asList(book);
//        when(bookService.retrieveBooks()).thenReturn(books);
//
//        ModelAndView mv = editController.deleteBook(request, response);
//
//        assertEquals("BookListing", mv.getViewName());
//
//        verify(bookService, times(1)).deleteBook(Integer.parseInt(bookCode));
//        verify(session, times(1)).setAttribute("books", books);
//    }
//
//    @Test
//    public void testHome() {
//        String viewName = editController.Home();
//        assertEquals("BookListing", viewName);
//    }
//}
