package com.nagarro.librest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.librest.entities.Book;



public interface BookRepo extends JpaRepository<Book, Integer> {

}
