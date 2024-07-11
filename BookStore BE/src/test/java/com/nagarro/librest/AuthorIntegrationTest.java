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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorIntegrationTest {
	
	@LocalServerPort
    private int port;
	
	@Autowired
    private TestH2Repository h2Repository;

	
	private String baseUrl = "http://localhost";
	
	private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }
    
    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/author");
    }
	
	@Test
	public void testAddAuthor() {
		Author author = new Author("Arihant");
		ResponseEntity<Author> response = restTemplate.postForEntity(baseUrl, author, Author.class);
		Author savedAuthor = response.getBody();
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Arihant", savedAuthor.getName());
        assertEquals(1, h2Repository.findAll().size());

	}
	
	@Test
    @Sql(statements = "INSERT INTO author VALUES ('Arihant')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM author WHERE name='Arihant'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetAuthors() {
        List<Author> author = restTemplate.getForObject(baseUrl, List.class);
        assertEquals(1, author.size());
        assertEquals(1, h2Repository.findAll().size());
    }
	
	
	@Test
	@Sql(statements = "DELETE FROM author WHERE name='Arihant'", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO author VALUES ('Arihant')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM author WHERE name='Arihant'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testFindAuthorByName() {
		System.out.println(baseUrl + "/{name}");
        Author author = restTemplate.getForObject(baseUrl + "/{name}", Author.class, "Arihant");
        assertAll(
                () -> assertNotNull(author),
                () -> assertEquals("Arihant", author.getName())
        );

    }
	
	@Test
    @Sql(statements = "INSERT INTO author VALUES ('Arihant')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM author WHERE name='Arihant'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testUpdateAuthor(){
		Author author = new Author("Arihant");
        restTemplate.put(baseUrl, author);
        Author authorFromDB = h2Repository.findById("Arihant").get();
        assertAll(
                () -> assertNotNull(authorFromDB),
                () -> assertEquals("Arihant", authorFromDB.getName())
        );

    }
	
	
	@Test
    @Sql(statements = "INSERT INTO author VALUES ('Arihant')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testDeleteAuthor(){
        int recordCount=h2Repository.findAll().size();
        assertEquals(1, recordCount);
        restTemplate.delete(baseUrl + "/{name}", "Arihant");
        assertEquals(0, h2Repository.findAll().size());

    }

}
