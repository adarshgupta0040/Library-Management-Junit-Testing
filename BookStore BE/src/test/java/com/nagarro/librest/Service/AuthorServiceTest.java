package com.nagarro.librest.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nagarro.librest.entities.Author;
import com.nagarro.librest.repository.AuthorRepo;
import com.nagarro.librest.service.AuthorService;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {
	
    @Mock
    private AuthorRepo authorRepository;

    @InjectMocks
    private AuthorService authorService;
    
    private Author existingAuthor;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		existingAuthor = Author.builder()
                .name("Sidney Sheldon")
                .build();
	}

	@Test
	void testUpdateAuthor() throws Exception {
		
		// Given
        given(authorRepository.findById("Sidney Sheldon")).willReturn(Optional.of(existingAuthor));
        given(authorRepository.save(existingAuthor)).willReturn(existingAuthor);

        // When
        Author updatedAuthor = authorService.updateAuthor(existingAuthor);

        // Then
        verify(authorRepository).findById("Sidney Sheldon");
        verify(authorRepository).save(existingAuthor);
	}

	
	@Test
	void testGetAuthorById() throws Exception {
		System.out.println(existingAuthor);
		 // given
        given(authorRepository.findById("Sidney Sheldon")).willReturn(Optional.of(existingAuthor));

        // when
        Author savedAuthor = authorService.getAuthorById(existingAuthor.getName()).get();

        // then
        assertThat(savedAuthor).isNotNull();

	}
	
	@Test
	void testDeleteAuthor() throws Exception {
		
		String name = "Sidney Sheldo";
		given(authorRepository.getById(name)).willReturn(existingAuthor);
		willDoNothing().given(authorRepository).delete(existingAuthor);

        // when -  action or the behaviour that we are going test
        authorService.deleteAuthor(name);

        // then - verify the output
        verify(authorRepository, times(1)).getById(name);
        verify(authorRepository, times(1)).delete(existingAuthor);
	}
}
