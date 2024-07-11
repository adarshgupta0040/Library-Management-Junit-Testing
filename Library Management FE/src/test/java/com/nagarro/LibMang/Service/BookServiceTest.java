package com.nagarro.LibMang.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import com.nagarro.LibMang.entities.Book;
import com.nagarro.LibMang.service.BookService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookServiceTest {

	@Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveBooks_Success() {
        String json = "[{\"bookCode\": 1, \"bookName\": \"Book1\", \"author\": \"Author1\", \"addedOn\": \"15 JUNE 2024\"}, " +
                      "{\"bookCode\": 2, \"bookName\": \"Book2\", \"author\": \"Author2\", \"addedOn\": \"15 JUNE 2024\"}]";

        // Mock the restTemplate's response
        when(restTemplate.getForObject("http://localhost:8090/book", String.class))
                .thenReturn(json);

        // Call the service method
        List<Book> books = bookService.retrieveBooks();

        // Verify that restTemplate.getForObject was called once
        verify(restTemplate, times(1)).getForObject("http://localhost:8090/book", String.class);

        // Assert that books list is not null and contains expected books
        assertNotNull(books);
        assertEquals(2, books.size());

        Book book1 = books.get(0);
        assertEquals(1, book1.getBookCode());
        assertEquals("Book1", book1.getBookName());
        assertEquals("Author1", book1.getAuthor());
        assertEquals("15 JUNE 2024", book1.getAddedOn());

        Book book2 = books.get(1);
        assertEquals(2, book2.getBookCode());
        assertEquals("Book2", book2.getBookName());
        assertEquals("Author2", book2.getAuthor());
        assertEquals("15 JUNE 2024", book2.getAddedOn());
    }

    @Test
    public void testRetrieveBooks_EmptyResponse() {

        String json = "[]";

        // Mock the restTemplate's response
        when(restTemplate.getForObject("http://localhost:8090/book", String.class))
                .thenReturn(json);

        // Call the service method
        List<Book> books = bookService.retrieveBooks();

        // Verify that restTemplate.getForObject was called once
        verify(restTemplate, times(1)).getForObject("http://localhost:8090/book", String.class);

        // Assert that books list is not null and is empty
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }

    @Test
    public void testRetrieveBooks_ExceptionHandling() {
        // Mock restTemplate throwing RuntimeException
        when(restTemplate.getForObject("http://localhost:8090/book", String.class))
                .thenReturn(null);

        // Call the service method
        List<Book> books = bookService.retrieveBooks();

        // Verify that restTemplate.getForObject was called once
        verify(restTemplate, times(1)).getForObject("http://localhost:8090/book", String.class);

        // Assert that books list is null due to exception
        assertNull(books);
    }
    
    @Test
    public void testDeleteBook_Success() {
        // Mock the restTemplate's response
        doNothing().when(restTemplate).delete("http://localhost:8090/book/1");

        // Call the service method
        bookService.deleteBook(1);

        // Verify that restTemplate.delete was called once with the correct URL
        verify(restTemplate, times(1)).delete("http://localhost:8090/book/1");
    }

    @Test
    void testSaveBook_PostMethod_Success() {

        Book book = new Book();
        book.setBookCode(123); 
        book.setBookName("Sample Book");
        book.setAuthor("Sample Author");
        book.setAddedOn("2024-06-25");

        // Mock the HttpHeaders and HttpEntity for postForEntity method
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Book> requestEntity = new HttpEntity(book, headers);

        // Mock the postForEntity method call
        when(restTemplate.postForEntity(
                eq("http://localhost:8090/book"),
                eq(requestEntity),
                eq(String.class)
        )).thenReturn(ResponseEntity.ok().body("Post Method Success"));

        // Call the method under test
        bookService.saveBook(book, "POST");

        // Verify
        verify(restTemplate).postForEntity(
                eq("http://localhost:8090/book"),
                eq(requestEntity),
                eq(String.class)
        );
    }

    @Test
    void testSaveBook_PutMethod_Success() {
        // Create a Book object for testing
        Book book = new Book();
        book.setBookCode(123);  // Set relevant attributes as needed
        book.setBookName("Sample Book");
        book.setAuthor("Sample Author");
        book.setAddedOn("2024-06-25");

        // Mock the HttpHeaders and HttpEntity for exchange method
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Book> requestEntity = new HttpEntity(book, headers);

        // Mock the exchange method call
        when(restTemplate.exchange(
                eq("http://localhost:8090/book"),
                eq(HttpMethod.PUT),
                eq(requestEntity),
                eq(String.class)
        )).thenReturn(ResponseEntity.ok().body("Put Method Success"));

        // Call the method under test
        bookService.saveBook(book, "PUT");

        // Verify
        verify(restTemplate).exchange(
                eq("http://localhost:8090/book"),
                eq(HttpMethod.PUT),
                eq(requestEntity),
                eq(String.class)
        );
    }


    
    private Book createSampleBook() {
        Book book = new Book();
        book.setBookCode(1);
        book.setBookName("Sample Book");
        book.setAuthor("Sample Author");
        book.setAddedOn("15 JUNE 2024");
        return book;
    }

    private HttpEntity<Book> createHttpEntityForBook() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(createSampleBook(), headers);
    }

}
