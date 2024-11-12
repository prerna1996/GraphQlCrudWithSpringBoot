package com.example.GraphQLIntro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @GeneratedValue
    @Id
    private long id;
    private String name;
    private String email;
    public UserEntity(String name, String email){
        this.email =email;
        this.name = name;
    }
}
