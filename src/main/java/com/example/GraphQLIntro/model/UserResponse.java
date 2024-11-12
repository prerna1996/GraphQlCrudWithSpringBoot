package com.example.GraphQLIntro.model;


import lombok.*;
import java.util.List;


@Builder
@Data
@Getter
@Setter
public class UserResponse {

    String message;
    User user;
    List<User> userList;

}
