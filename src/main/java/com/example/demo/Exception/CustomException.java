package com.example.demo.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException{
    HttpStatus code;
    String errorDescription;
}
