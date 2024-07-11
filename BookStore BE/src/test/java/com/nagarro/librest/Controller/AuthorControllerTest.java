package com.nagarro.librest.Controller;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.util.List;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.doNothing;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.librest.controller.AuthorController;
import static org.mockito.BDDMockito.*;
import com.nagarro.librest.entities.Author;
import com.nagarro.librest.repository.AuthorRepo;
import com.nagarro.librest.service.AuthorService;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = AuthorController.class)
public class AuthorControllerTest {

	@MockBean
	AuthorService authorService;
	
	@MockBean
    AuthorRepo authorRepository;
	
	@Autowired
	MockMvc mockMvc;
	
	private Author existingAuthor;
	

    @Autowired
    private ObjectMapper objectMapper;
    
	@BeforeEach
	void setUp() throws Exception {
		
		existingAuthor = Author.builder()
                .name("Sidney Sheldon").build();
	}
	
	
	@Test
	public void testAddAuthor() throws Exception{
		// given - setup
        Author mockAuthor = new Author();
        mockAuthor.setName("Arihant");

        Mockito.when(authorRepository.save(any(Author.class))).thenReturn(mockAuthor);
        
     // when - action
        mockMvc.perform(MockMvcRequestBuilders.post("/author")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"name\": \"Arihant\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Arihant"));
    
                
	}
	
	@Test
	public void testGetAuthors() throws Exception{
		
		// given - setup
        List<Author> listOfMockAuthor = new ArrayList<>();
        
        listOfMockAuthor.add(new Author("Arihant"));
        listOfMockAuthor.add(new Author("RD Sharma"));
        
        
        Mockito.when(authorRepository.findAll()).thenReturn(listOfMockAuthor);

        // when - action
        mockMvc.perform(MockMvcRequestBuilders.get("/author")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Arihant"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("RD Sharma"));
	}
	
	@Test
	public void testGetAuthor() throws Exception{
		
		String authorName = "Arihant";
		Author mockAuthor = new Author();
        mockAuthor.setName("Arihant");
        
        Mockito.when(authorService.getAuthorById("Arihant")).thenReturn(Optional.of(mockAuthor));
        
        // when - action
        mockMvc.perform(MockMvcRequestBuilders.get("/author/{id}",authorName )
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Arihant\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Arihant"));
	}
	
	

	@Test
	public void testSaveOrUpdateAuthor() throws Exception{
		
		Author author = new Author();
		author.setName("Arihant");
		
		Mockito.when(authorService.updateAuthor(author)).thenReturn(author);

        mockMvc.perform(MockMvcRequestBuilders.put("/author")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"name\": \"Arihant\" }"))
            .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testDeleteAuthor() throws Exception{
		
		String authorName = "Arihant";

        doNothing().when(authorService).deleteAuthor(authorName);

        mockMvc.perform(MockMvcRequestBuilders.delete("/author/{name}", authorName))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
	

