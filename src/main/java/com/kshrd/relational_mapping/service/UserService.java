package com.kshrd.relational_mapping.service;

import com.kshrd.relational_mapping.model.dto.UserDto;
import com.kshrd.relational_mapping.model.request.UserRequest;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto createUser(UserRequest userRequest);

    UserDto updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);
}
