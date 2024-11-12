package com.example.GraphQLIntro.service;

import com.example.GraphQLIntro.entity.UserEntity;
import com.example.GraphQLIntro.model.User;
import com.example.GraphQLIntro.model.UserResponse;

public interface UserService {
    UserResponse findAll();
    UserResponse findById(Long id);
    UserResponse save(User user);
    String  delete(Long id);
    UserResponse update(Long id, UserEntity updatedUserEntity);
}
