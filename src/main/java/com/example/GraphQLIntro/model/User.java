package com.example.GraphQLIntro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private long id;
    private String name;
    private String email;

    public User(String name, String email) {
        this.name =name;
        this.email =email;
    }
}
