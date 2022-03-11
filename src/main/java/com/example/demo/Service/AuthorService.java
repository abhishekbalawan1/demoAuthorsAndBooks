package com.example.demo.Service;

import com.example.demo.Entity.AuthorEntity;
import com.example.demo.Entity.BookEntity;
import com.example.demo.Exception.CustomException;
import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@NoArgsConstructor
@Data
public class AuthorService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Page<AuthorEntity> getAllAuthors(int page){
        Pageable pageable = PageRequest.of(page, 2);
        return authorRepository.getAllAuthors(pageable);
    }

    public List<AuthorEntity> getAuthorsByExample(String name){
        AuthorEntity author = new AuthorEntity();
        author.setName(name);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("address")
                .withIgnorePaths("id")
                .withIgnorePaths("age");
        Example<AuthorEntity> examples = Example.of(author, matcher);
        List<AuthorEntity> similarAuthors = authorRepository.findAll(examples);
        return similarAuthors;
    }

    public List<AuthorEntity> getAuthors(){
        List<AuthorEntity> authorList = new LinkedList<AuthorEntity>();
        authorRepository.findAll().forEach(authorList::add);
        if(authorList.size() == 0){
            throw new CustomException(HttpStatus.BAD_REQUEST, "There are no author records in the database.");
        }
        return authorList;
    }

    public void createAuthor(AuthorEntity author){
        authorRepository.save(author);
    }

    public void createBook(BookEntity bookEntity){
        bookRepository.save(bookEntity);
    }

    public AuthorEntity getAuthorDetails(int id){
        AuthorEntity author = authorRepository.getAuthor(id);
        if(author == null){
            throw new CustomException(HttpStatus.BAD_REQUEST, "No author for the given id.");
        }
        return author;
    }

    public List<BookEntity> getBooksOfAuthor(int authorId){
        List<BookEntity> listBookEntities = bookRepository.getBookByAuthor(authorId);
        if(listBookEntities.size() == 0){
            throw new CustomException(HttpStatus.BAD_REQUEST, "There are no books for the given author.");
        }
        return listBookEntities;
    }
}
