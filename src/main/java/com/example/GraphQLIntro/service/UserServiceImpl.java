package com.example.GraphQLIntro.service;

import com.example.GraphQLIntro.entity.UserEntity;
import com.example.GraphQLIntro.model.User;
import com.example.GraphQLIntro.model.UserResponse;
import com.example.GraphQLIntro.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    // Find all users
    public UserResponse findAll() {
        List<UserEntity> userEntity = userRepository.findAll();
        List<User> userList = objectMapper.convertValue(userEntity, new TypeReference<List<User>>() {});
        UserResponse userResponse = UserResponse.builder()
                .userList(userList)
                .message("Successfully fetched the user list")
                .build();
        return userResponse;
    }

    // Find user by id
    public UserResponse findById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            User user = objectMapper.convertValue(userEntity.get(), User.class);
            UserResponse userResponse = UserResponse.builder()
                    .user(user)
                    .message("Fetched user successfully for id - " + id)
                    .build();
            return userResponse;
        } else {
            return UserResponse.builder()
                    .message("User not found for id - " + id)
                    .build();
        }
    }

    // Save a new user
    public UserResponse save(User user) {
        try {
            UserEntity userEntity = new UserEntity(user.getName(),user.getEmail());
            userRepository.save(userEntity);
            return UserResponse.builder()
                    .message("User Saved Successfully")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to save user");
        }
    }

    // Delete a user by id
    public String delete(Long id) {
        try {
            userRepository.deleteById(id);
            return "User deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user");
        }
    }


    // Update user by id
    public UserResponse update(Long id, UserEntity updatedUserEntity) {
        try {
            // Check if user exists
            Optional<UserEntity> existingUser = userRepository.findById(id);

            if (existingUser.isPresent()) {
                UserEntity userEntity = existingUser.get();

                // Update fields (set new values from the updatedUserEntity)
                if (updatedUserEntity.getName() != null) {
                    userEntity.setName(updatedUserEntity.getName());
                }
                if (updatedUserEntity.getEmail() != null) {
                    userEntity.setEmail(updatedUserEntity.getEmail());
                }
                userRepository.save(userEntity);
                User updatedUser = objectMapper.convertValue(userEntity, User.class);

                return UserResponse.builder()
                        .user(updatedUser)
                        .message("User updated successfully for id - " + id)
                        .build();
            } else {
                return UserResponse.builder()
                        .message("User not found for id - " + id)
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user", e);
        }
    }
}
