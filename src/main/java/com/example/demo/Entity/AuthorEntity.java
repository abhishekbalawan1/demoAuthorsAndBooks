package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity {
    @Id
    @NotNull
    int id;

    @NotEmpty
    @Size(min = 2, message = "Author name should be at least 2 characters in length.")
    String name;

    @NotEmpty
    @Size(min = 2, message = "Address should be at least 2 characters in length.")
    String address;

    @NotNull
    int age;

}
