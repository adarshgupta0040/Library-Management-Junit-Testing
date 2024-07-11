package com.nagarro.librest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.librest.entities.Author;
import com.nagarro.librest.exceptions.ValidationException;
import com.nagarro.librest.repository.AuthorRepo;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepo authorRepository;


	public Author updateAuthor(Author author) {
	    Optional<Author> optionalExistingAuthor = authorRepository.findById(author.getName());
	    if (optionalExistingAuthor.isPresent()) {
	        // Fetch the existing author
	        Author existingAuthor = optionalExistingAuthor.get();
	        
	        // Update the name (though typically the name wouldn't change as it's the identifier)
	        existingAuthor.setName(author.getName());
	        
	        // Save and return the updated author
	        return authorRepository.save(existingAuthor);
	    } else {
	        // Handle the case where the author does not exist
	        throw new ValidationException("Author not found with name: " + author.getName());
	    }
	}



	public Optional<Author> getAuthorById(String name) {
		// TODO Auto-generated method stub
		return authorRepository.findById(name);
	}


	public void deleteAuthor(String name) {
		// TODO Auto-generated method stub
		Author a=authorRepository.getById(name);
		authorRepository.delete(a);
		
	}


}
