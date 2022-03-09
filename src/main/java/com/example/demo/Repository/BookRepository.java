package com.example.demo.Repository;

import com.example.demo.Entity.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {

    @Query("Select bookObject from BookEntity bookObject WHERE bookObject.authorId = :n")
    public List<BookEntity> getBookByAuthor(@Param("n") int authorId);
}
