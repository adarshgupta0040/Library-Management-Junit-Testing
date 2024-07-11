package com.nagarro.librest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.librest.entities.Author;


public interface AuthorRepo extends JpaRepository<Author, String> {

}
