package com.nagarro.LibMang.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.nagarro.LibMang.entities.Author;
import com.nagarro.LibMang.service.AuthorService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAuthors() {
        String json = "[{\"name\": \"John Doe\"}, {\"name\": \"Jane Smith\"}]";

        // Mock the restTemplate's response
        when(restTemplate.getForObject("http://localhost:8090/author", String.class))
                .thenReturn(json);

        // Calling the service method
        List<Author> authors = authorService.retrieveAuthors();

        // Verify that restTemplate.getForObject was called once
        verify(restTemplate, times(1)).getForObject("http://localhost:8090/author", String.class);

        // Assert that authors list is not null and contains expected authors
        assertNotNull(authors);
        assertEquals(2, authors.size());

        Author author1 = authors.get(0);
        assertEquals("John Doe", author1.getName());

        Author author2 = authors.get(1);
        assertEquals("Jane Smith", author2.getName());
    }

    @Test
    public void testRetrieveAuthors_EmptyResponse() {
        String json = "[]";

        // Mock the restTemplate's response
        when(restTemplate.getForObject("http://localhost:8090/author", String.class))
                .thenReturn(json);

        // Calling the service method
        List<Author> authors = authorService.retrieveAuthors();

        // Verify that restTemplate.getForObject was called once
        verify(restTemplate, times(1)).getForObject("http://localhost:8090/author", String.class);

        // Assert that authors list is not null and is empty
        assertNotNull(authors);
        assertTrue(authors.isEmpty());
    }
    
    @Test
    public void testRetrieveAuthors_RestTemplateReturnsNull() {
        // Mock restTemplate returning null
        when(restTemplate.getForObject("http://localhost:8090/author", String.class))
                .thenReturn(null);

        // Call the service method
        List<Author> authors = authorService.retrieveAuthors();

        // Verify that restTemplate.getForObject was called once
        verify(restTemplate, times(1)).getForObject("http://localhost:8090/author", String.class);

        // Assert that authors list is null when restTemplate returns null
        assertNull(authors);
    }
}
