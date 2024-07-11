package com.nagarro.librest.Service;

import static org.junit.jupiter.api.Assertions.fail;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nagarro.librest.entities.Author;
import com.nagarro.librest.entities.Book;
import com.nagarro.librest.exceptions.ValidationException;
import com.nagarro.librest.repository.AuthorRepo;
import com.nagarro.librest.repository.BookRepo;
import com.nagarro.librest.service.AuthorService;
import com.nagarro.librest.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
    @Mock
    private BookRepo bookRepository;

    @InjectMocks
    private BookService bookService;
    
    private Book book;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		book = Book.builder()
                .bookCode(103)
                .bookName("CSE")
                .author("J.K. Rowling")
                .addedOn("15 JUNE 2024")
                .build();
		
	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	@Test
	void testAddBook() throws Exception {
		
		// Given
        given(bookRepository.findById(103))
                .willReturn(Optional.empty()); // No existing book with code 103

        given(bookRepository.save(book))
                .willReturn(book); // Return the saved book

        // When
        Book savedBook = bookService.addBook(book);

        // Then
        assertThat(savedBook).isNotNull();
        assertThat( savedBook.getBookCode()).isEqualTo(103);
        assertThat(savedBook.getBookName()).isEqualTo("CSE");
        assertThat( savedBook.getAuthor()).isEqualTo("J.K. Rowling");
        assertThat(savedBook.getAddedOn()).isEqualTo("15 JUNE 2024");
    }
	
	@Test
    void testAddBookThrowsValidationException() {
        // Given
        given(bookRepository.findById(103)).willReturn(Optional.of(book)); // Existing book with code 103

        // When & Then
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            bookService.addBook(book);
        });

        assertThat(exception.getMessage()).isEqualTo("Book with code 103 already exists.");
    }
	
	@Test
	void testDeleteBook() throws Exception{
		
		//Given
		given(bookRepository.getById(103))
        .willReturn(book);
		
		//act
		bookService.deleteBook(103);
		
		//Then verify
		verify(bookRepository, times(1)).delete(book);
	}
	
	@Test
	void testDeleteBookThrowsValidationException() {
	    // Given
	    given(bookRepository.getById(103)).willReturn(null); // Simulate no book found

	    // When & Then
	    ValidationException exception = assertThrows(ValidationException.class, () -> {
	        bookService.deleteBook(103);
	    });

	    assertThat(exception.getMessage()).isEqualTo("Book with code 103 does not exist.");
	}
}

























//String name = "Sidney Sheldo";
//given(authorRepository.getById(name)).willReturn(existingAuthor);
//willDoNothing().given(authorRepository).delete(existingAuthor);
//
//// when -  action or the behaviour that we are going test
//authorService.deleteAuthor(name);
//
//// then - verify the output
//verify(authorRepository, times(1)).getById(name);
//verify(authorRepository, times(1)).delete(existingAuthor);



//@Test
//void testAddBook_ExistingBook() {
//// Given
//given(bookRepository.findById(104))
//        .willReturn(Optional.of(book)); // Existing book with the same book code
//
//// When/Then
//assertThrows(ValidationException.class, () -> {
//    bookService.addBook(book);
//});
//}
