package com.nagarro.librest;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.librest.entities.Author;

public interface TestH2Repository extends JpaRepository<Author, String>{

}
