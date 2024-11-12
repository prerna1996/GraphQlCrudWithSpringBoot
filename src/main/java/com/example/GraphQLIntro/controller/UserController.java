package com.example.GraphQLIntro.controller;


import com.example.GraphQLIntro.entity.UserEntity;
import com.example.GraphQLIntro.model.User;
import com.example.GraphQLIntro.model.UserResponse;
import com.example.GraphQLIntro.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @QueryMapping
    public UserResponse users() {
        return userService.findAll();
    }

    @QueryMapping
    public UserResponse user(@Argument Long id) {
        return userService.findById(id);
    }

    @MutationMapping
    public UserResponse createUser(@Argument String name, @Argument String email) {
        User user = new User(name, email);
        return userService.save(user);
    }

    @MutationMapping
    public UserResponse updateUser(@Argument Long id, @Argument String name, @Argument String email) {
        UserEntity user = new UserEntity(name, email);
        return userService.update(id, user);
    }

    @MutationMapping
    public String deleteUser(@Argument Long id) {
        return userService.delete(id);
    }

}
