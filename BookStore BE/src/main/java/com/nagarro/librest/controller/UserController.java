package com.nagarro.librest.controller;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.librest.entities.Users;
import com.nagarro.librest.repository.UserRepo;

@RestController
public class UserController {

	@Autowired 
	UserRepo repo;
	@PostMapping(path="/users",consumes= {"application/json"})
	public void addUsers(@RequestBody Users user) 
	{
		repo.save(user);
		
	}
	
	@PutMapping(path="/users",consumes= {"application/json"})
	public void saveOrUpdateUsers(@RequestBody Users user) 
	{
		repo.save(user);
		
	}
	
	@GetMapping(path="/users")
	public List<Users> getUsers()
	{
		return repo.findAll();
	}
	
	@RequestMapping("/users/{uname}")
	public Optional<Users> getUser(@PathVariable("uname") String uname)
	{
		return repo.findById(uname);

	}
	
	@DeleteMapping("/users/{uname}")
	public  void  deleteUser(@PathVariable("uname") String uname)
	{
		Users a=repo.getById(uname);
		repo.delete(a);
		
	}
}