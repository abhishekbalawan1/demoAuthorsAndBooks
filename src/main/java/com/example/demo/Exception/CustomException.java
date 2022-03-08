package com.example.demo.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException{
    String errorMessage;
    String errorDescription;
}
