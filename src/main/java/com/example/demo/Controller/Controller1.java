package com.example.demo.Controller;
import com.example.demo.Exception.CustomException;
import com.example.demo.Entity.Author;
import com.example.demo.Entity.Book;
import com.example.demo.Service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Controller1 extends Throwable{
    @Autowired
    private Service servivce;

    Logger logger = LoggerFactory.getLogger(Controller1.class);

    @RequestMapping("/authorsByPage/{page}")
    public Page<Author> getAllAuthors(@PathVariable int page){
        return servivce.getAllAuthors(page);
    }

    @RequestMapping("/authors")
    public List<Author> getAuthors(){
        logger.trace("Getting authors information...");
        List<Author> listAuthors = servivce.getAuthors();
        if(listAuthors.size() == 0){
            throw new CustomException("1111", "There are no author records in the database.");
        }
        logger.trace("Getting authors information completed.");
        return listAuthors;
    }

    @RequestMapping("/authors/{id}")
    public Author getAuthorDetails(@PathVariable int id){
        logger.trace("Getting author details...");
        Author author = servivce.getAuthorDetails(id);
        if(author == null){
            throw new CustomException("1111", "No author for the given id.");
        }
        logger.trace("Getting author details completed.");
        return servivce.getAuthorDetails(id);
    }

    @RequestMapping("/books/{Authid}")
    public List<Book> getBooksOfAuthor(@PathVariable int Authid){
        logger.trace("Getting books of the author...");
        List<Book> listBooks = servivce.getBooksOfAuthor(Authid);
        if(listBooks.size() == 0){
            throw new CustomException("1111", "There are no books for the given author.");
        }
        logger.trace("Getting books of the author completed.");
        return listBooks;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authors")
    public void createAuthor(@RequestBody Author author){
        logger.trace("Creating author...");
        if(author.getName().isEmpty() ) {
            throw new CustomException("1111", "Author name is empty.");
        }
        if(author.getAddress().isEmpty()) {
            throw new CustomException("1111", "Address is empty.");
        }
        if(author.getAge()<=0) {
            throw new CustomException("1111", "Invalid age.");
        }
        servivce.createAuthor(author);
        logger.trace("Author created");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books")
    public void createBook(@Valid @RequestBody Book book){
        logger.trace("Creating book...");
        /*
        if(book.getBookName().isEmpty()){
            throw new CustomException("1111", "Book name is empty.");
        }
        if(book.getAuthorId()<=0){
            throw new CustomException("1111", "Invalid author id.");
        }
        if(book.getId()<=0){
            throw new CustomException("1111", "Invalid book id.");
        }
         */
        servivce.createBook(book);
        logger.trace("Book created");
    }

    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<Object> exceptionHandler(CustomException customException){
        return new ResponseEntity<>(customException.getErrorDescription(), HttpStatus.BAD_REQUEST);
    }
}
