package com.nagarro.librest;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nagarro.librest.entities.Book;

public interface TestH2BookRespository extends JpaRepository<Book, Integer>{

}
