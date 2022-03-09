package com.example.demo.Repository;

import com.example.demo.Entity.AuthorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

    @Query("Select authObject from AuthorEntity authObject WHERE authObject.id = :n")
    public AuthorEntity getAuthor(@Param("n") int authorId);

    @Query("Select authObject from AuthorEntity authObject")
    public Page<AuthorEntity> getAllAuthors(Pageable pageable);
}

