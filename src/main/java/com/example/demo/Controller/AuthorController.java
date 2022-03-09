package com.example.demo.Controller;
import com.example.demo.Entity.AuthorEntity;
import com.example.demo.Entity.BookEntity;
import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorController{

    @Autowired
    private AuthorService service;

    Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @GetMapping("/authors")
    public List<AuthorEntity> getAuthors(){
        logger.debug("Getting authors information...");
        List<AuthorEntity> listAuthors = service.getAuthors();
        logger.debug("Getting authors information completed.");
        return listAuthors;
    }

    @GetMapping("/authors/{id}")
    public AuthorEntity getAuthorDetails(@PathVariable int id){
        logger.debug("Getting author details...");
        AuthorEntity author = service.getAuthorDetails(id);
        logger.debug("Getting author details completed.");
        return author;
    }

    @GetMapping("/books/{Authid}")
    public List<BookEntity> getBooksOfAuthor(@PathVariable int Authid){
        logger.debug("Getting books of the author...");
        List<BookEntity> listBookEntities = service.getBooksOfAuthor(Authid);
        logger.debug("Getting books of the author completed.");
        return listBookEntities;
    }

    @PostMapping(value = "/authors")
    public void createAuthor(@Valid @RequestBody AuthorEntity authorEntity){
        logger.debug("Creating author...");
        service.createAuthor(authorEntity);
        logger.debug("Author created");
    }

    @PostMapping(value = "/books")
    public void createBook(@Valid @RequestBody BookEntity bookEntity){
        logger.debug("Creating book...");
        service.createBook(bookEntity);
        logger.debug("Book created");
    }

}
