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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

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
