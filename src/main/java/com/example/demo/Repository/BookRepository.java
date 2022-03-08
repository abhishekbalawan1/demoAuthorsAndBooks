package com.example.demo.Repository;

import com.example.demo.Entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    @Query("Select bookObject from Book bookObject WHERE bookObject.authorId = :n")
    public List<Book> getBookByAuthor(@Param("n") int authorId);
}
