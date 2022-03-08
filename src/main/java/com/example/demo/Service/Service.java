package com.example.demo.Service;

import com.example.demo.Entity.Author;
import com.example.demo.Entity.Book;
import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    Service(){
    }

    public Page<Author> getAllAuthors(int page){
        Pageable pageable = PageRequest.of(page, 2);
        return authorRepository.getAllAuthors(pageable);
    }

    public List<Author> getAuthors(){
        List<Author> authorList = new LinkedList<Author>();
        authorRepository.findAll().forEach(authorList::add);
        return authorList;
    }

    public List<Book> getBooks(){
        List<Book> bookList = new LinkedList<Book>();
        bookRepository.findAll().forEach(bookList::add);
        return bookList;
    }

    public void createAuthor(Author author){
        authorRepository.save(author);
    }

    public void createBook(Book book){
        bookRepository.save(book);
    }

    public Author getAuthorDetails(int id){
        return authorRepository.getAuthor(id);
    }

    public List<Book> getBooksOfAuthor(int authorId){
        return bookRepository.getBookByAuthor(authorId);
    }
}
