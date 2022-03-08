package com.example.demo.Repository;

import com.example.demo.Entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("Select authObject from Author authObject WHERE authObject.id = :n")
    public Author getAuthor(@Param("n") int authorId);

    @Query("Select authObject from Author authObject")
    public Page<Author> getAllAuthors(Pageable pageable);
}

