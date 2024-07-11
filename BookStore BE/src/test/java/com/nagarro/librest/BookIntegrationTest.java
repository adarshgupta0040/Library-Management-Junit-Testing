package com.nagarro.librest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import com.nagarro.librest.entities.Author;
import com.nagarro.librest.entities.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookIntegrationTest {

	@LocalServerPort
    private int port;
	
	@Autowired
    private TestH2BookRespository h2BookRepository;

	
	private String baseUrl = "http://localhost";
	
	private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }
    
    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/book");
    }
	
	@Test
	public void testAddBook() {
		Book book = new Book();
		book.setAddedOn("15 JUNE 2024");
		book.setAuthor("Arihant");
		book.setBookName("CSE");
		book.setBookCode(101);
		
		
		ResponseEntity<Book> response = restTemplate.postForEntity(baseUrl, book, Book.class);
		Book savedBook = response.getBody();
		
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(101, savedBook.getBookCode());
		assertEquals("CSE", savedBook.getBookName());
		assertEquals("Arihant", savedBook.getAuthor());
        assertEquals(1, h2BookRepository.findAll().size());

	}
	
	@Test
    @Sql(statements = "INSERT INTO book (book_code, book_name, author, added_on) VALUES (101,'CSE','Arihant','15 JUNE 2024')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM book WHERE book_code=101", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetAuthors() {
        List<Book> book = restTemplate.getForObject(baseUrl, List.class);
        assertEquals(1, book.size());
        assertEquals(1, h2BookRepository.findAll().size());
    }
	
	
	@Test
    @Sql(statements = "INSERT INTO book (book_code, book_name, author, added_on) VALUES (101,'CSE','Arihant','15 JUNE 2024')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM book WHERE book_code=101", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testFindBookByBookCode() {
		System.out.println(baseUrl + "/{bookCode}");
		Book book = restTemplate.getForObject(baseUrl + "/{bookCode}", Book.class, 101);
        assertAll(
                () -> assertNotNull(book),
                () -> assertEquals(101, book.getBookCode()),
                () -> assertEquals("CSE", book.getBookName())
        );

    }
	
	@Test
	@Sql(statements = "DELETE FROM book WHERE book_code=101", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO book (book_code, book_name, author, added_on) VALUES (101,'CSE','Arihant','15 JUNE 2024')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM book WHERE book_code=101", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testUpdateBook(){
		Book book = new Book(101,"CSE","Tom Johnson","15 JUNE 2024");
        restTemplate.put(baseUrl, book);
        Book bookFromDB = h2BookRepository.findById(101).get();
        assertAll(
                () -> assertNotNull(bookFromDB),
                () -> assertEquals("Tom Johnson", bookFromDB.getAuthor())
        );

    }
	
	
	@Test
    @Sql(statements ="INSERT INTO book (book_code, book_name, author, added_on) VALUES (101,'CSE','Arihant','15 JUNE 2024')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testDeleteBook(){
        int recordCount=h2BookRepository.findAll().size();
        assertEquals(1, recordCount);
        restTemplate.delete(baseUrl + "/{bookCode}", 101);
        assertEquals(0, h2BookRepository.findAll().size());

    }
}
