package com.nagarro.librest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.librest.entities.Author;
import com.nagarro.librest.repository.AuthorRepo;
import com.nagarro.librest.service.AuthorService;


@RestController
public class AuthorController {

	@Autowired
	private AuthorRepo repo;
	
	@Autowired
	private AuthorService authorService;
	
	@PostMapping(path="/author",consumes= {"application/json"})
	public Author addAuthor(@RequestBody Author author) 
	{
		return repo.save(author);
		
	}
	
	@GetMapping(path="/author")
	public List<Author> getAuthors()
	{
		return repo.findAll();
	}
	
	@PutMapping(path="/author",consumes= {"application/json"})
	public ResponseEntity<Author> saveOrUpdateAuthor(@RequestBody Author author) 
	{
//		repo.save(author);
		return new ResponseEntity<Author>(authorService.updateAuthor(author),HttpStatus.OK);
	}
	
	
	
	@RequestMapping("/author/{name}")
	public ResponseEntity<Optional<Author>> getAuthor(@PathVariable("name") String name)
	{
//		return repo.findById(name);
		return new ResponseEntity<Optional<Author>>(authorService.getAuthorById(name),HttpStatus.OK);

	}
	
	@DeleteMapping("/author/{name}")
	public ResponseEntity<String> deleteAuthor(@PathVariable("name") String name)
	{
//		Author a=repo.getById(name);
//		repo.delete(a);
		authorService.deleteAuthor(name);
        return new ResponseEntity<String>("Author deleted successfully!.", HttpStatus.OK);

		
	}
}
