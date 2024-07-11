package com.nagarro.LibMang.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.LibMang.entities.Book;
import com.nagarro.LibMang.service.BookService;
import com.nagarro.LibMang.service.LoginService;

public class HomeControllerTest {

    @Mock
    private BookService displayBooks;

    @Mock
    private LoginService loginService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private com.nagarro.LibMang.controller.homeController homeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCheckUser_SuccessfulLogin() {
        // Mocking request parameters
        String username = "user";
        String password = "pass";
        when(request.getParameter("uname")).thenReturn(username);
        when(request.getParameter("pass")).thenReturn(password);
        when(request.getSession()).thenReturn(session);
        when(loginService.checkLogin(username, password)).thenReturn(true);

        // Mocking book retrieval
        Book book = new Book();
        List<Book> books = Collections.singletonList(book);
        when(displayBooks.retrieveBooks()).thenReturn(books);

        // Calling the method under test
        ModelAndView mv = homeController.checkUser(request, response);

        // Assertions
        assertEquals("BookListing", mv.getViewName());
        verify(session).setAttribute("username", username);
        verify(session).setAttribute("books", books);
    }

    @Test
    public void testCheckUser_UnsuccessfulLogin() {
        // Mocking request parameters
        String username = "user";
        String password = "wrongpass";
        when(request.getParameter("uname")).thenReturn(username);
        when(request.getParameter("pass")).thenReturn(password);
        when(loginService.checkLogin(username, password)).thenReturn(false);

        // Calling the method under test
        ModelAndView mv = homeController.checkUser(request, response);

        // Assertions
        assertEquals("login", mv.getViewName());
        verify(session, never()).setAttribute(anyString(), any());
    }

    @Test
    public void testCheck_User() {
        // Mocking request session
        when(request.getSession()).thenReturn(session);

        // Calling the method under test
        ModelAndView mv = homeController.check_User(request, response);

        // Assertions
        assertEquals("login", mv.getViewName());
        verify(session).removeAttribute("username");
        verify(session).invalidate();
    }
}




















































//
//import static org.mockito.Mockito.*;
//import static org.junit.Assert.*;
//
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
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.nagarro.LibMang.entities.Book;
//import com.nagarro.LibMang.controller.homeController;
//import com.nagarro.LibMang.service.BookService;
//import com.nagarro.LibMang.service.LoginService;
//
//@RunWith(MockitoJUnitRunner.class)
//public class HomeControllerTest {
//
//    @Mock
//    private BookService displayBooks;
//
//    @Mock
//    private LoginService loginService;
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
//    private homeController homeController;
//
//    @Before
//    public void setUp() {
//        when(request.getSession()).thenReturn(session);
//    }
//
//    @Test
//    public void testCheckUser_SuccessfulLogin() throws Exception {
//        String username = "testuser";
//        String password = "testpassword";
//
//        when(request.getParameter("uname")).thenReturn(username);
//        when(request.getParameter("pass")).thenReturn(password);
//        when(loginService.checkLogin(username, password)).thenReturn(true);
//
//        List<Book> books = Arrays.asList(new Book()); // Mocking a list of books
//        when(displayBooks.retrieveBooks()).thenReturn(books);
//
//        ModelAndView mv = homeController.checkUser(request, response);
//
//        assertEquals("BookListing", mv.getViewName());
//
//        verify(session).setAttribute("username", username);
//        verify(session).setAttribute("books", books);
//    }
//
//    @Test
//    public void testCheckUser_UnsuccessfulLogin() throws Exception {
//        String username = "testuser";
//        String password = "testpassword";
//
//        when(request.getParameter("uname")).thenReturn(username);
//        when(request.getParameter("pass")).thenReturn(password);
//        when(loginService.checkLogin(username, password)).thenReturn(false);
//
//        ModelAndView mv = homeController.checkUser(request, response);
//
//        assertEquals("login", mv.getViewName());
//
//        verify(session, never()).setAttribute(anyString(), any());
//    }
//
//    @Test
//    public void testCheck_User_Logout() throws Exception {
//        ModelAndView mv = homeController.check_User(request, response);
//
//        assertEquals("login", mv.getViewName());
//
//        verify(session).removeAttribute("username");
//        verify(session).invalidate();
//    }
//}
