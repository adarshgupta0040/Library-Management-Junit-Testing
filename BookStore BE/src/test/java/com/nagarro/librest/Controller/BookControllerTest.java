package com.nagarro.librest.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
//import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.librest.controller.AuthorController;
import com.nagarro.librest.controller.BookController;
import com.nagarro.librest.entities.Author;
import com.nagarro.librest.entities.Book;
import com.nagarro.librest.repository.AuthorRepo;
import com.nagarro.librest.repository.BookRepo;
import com.nagarro.librest.service.AuthorService;
import com.nagarro.librest.service.BookService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = BookController.class)
class BookControllerTest {

	@MockBean
	BookService bookService;

	@MockBean
	BookRepo bookRepository;

	@Autowired
	MockMvc mockMvc;

	@Autowired
    private ObjectMapper objectMapper;
	
	private BookController bookController;
	private Book existingBook;
	
	@BeforeEach
	void setUp() throws Exception {
		
		existingBook = Book.builder()
                .bookCode(103)
                .bookName("CSE")
                .author("J.K. Rowling")
                .addedOn("15 JUNE 2024")
                .build();
	}

	@Test
	public void testAddBook() throws Exception {
				
        // given - precondition or setup
        Book book = Book.builder()
                .bookCode(103)
                .bookName("CSE")
                .author("J.K. Rowling")
                .addedOn("15 JUNE 2024")
                .build();
        
		given(bookService.addBook(any(Book.class))).willAnswer((invocation) -> invocation.getArgument(0));

		// when - action or behaviour that we are going test
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/book").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(book)));

		// then - verify the result or output using assert statements
		response.andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.bookCode", is(book.getBookCode())))
				.andExpect(jsonPath("$.bookName", is(book.getBookName())))
				.andExpect(jsonPath("$.author", is(book.getAuthor())))
				.andExpect(jsonPath("$.addedOn", is(book.getAddedOn())));
		
	}
	
	@Test
	public void testGetBooks() throws Exception{
		
		// given - setup
        List<Book> listOfMockBook = new ArrayList<>();
        
        Book book1 = Book.builder()
                .bookCode(103)
                .bookName("CSE")
                .author("J.K. Rowling")
                .addedOn("15 JUNE 2024")
                .build();
        
        Book book2 = Book.builder()
                .bookCode(104)
                .bookName("IT")
                .author("Arihant")
                .addedOn("16 JUNE 2024")
                .build();
        
        listOfMockBook.add(book1);
        listOfMockBook.add(book2);
        
        
        Mockito.when(bookRepository.findAll()).thenReturn(listOfMockBook);

        // when - action
        mockMvc.perform(MockMvcRequestBuilders.get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookCode").value(103))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addedOn").value("15 JUNE 2024"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookCode").value(104))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addedOn").value("16 JUNE 2024"));
	}
	
	@Test
	public void testUpdateBook() throws Exception{
		
		Book book = Book.builder()
                .bookCode(103)
                .bookName("CSE")
                .author("J.K. Rowling")
                .addedOn("15 JUNE 2024")
                .build();
		
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		
		// when - action or behaviour that we are going test
				ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/book")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(book)));
		
				response.andDo(print())
					.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetBook() throws Exception{
		
		int bookCode = 103;
        
        Mockito.when(bookRepository.findById(103)).thenReturn(Optional.of(existingBook));
        
        // when - action
        mockMvc.perform(MockMvcRequestBuilders.get("/book/{id}",bookCode )	
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"bookCode\":103,\"bookName\":\"CSE\",\"author\":\"J.K. Rowling\",\"addedOn\":\"15 JUNE 2024\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookCode").value("103"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName").value("CSE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("J.K. Rowling"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addedOn").value("15 JUNE 2024"));
	}
	
	@Test
	public void testDeleteAuthor() throws Exception{
		
		int bookCode = 103;

        // given - precondition or setup
        doNothing().when(bookService).deleteBook(bookCode);
        
        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/book/{id}", bookCode));

        // test & verify o/p
        response.andExpect(status().isOk())
                .andDo(print());

    }
	
	
}
