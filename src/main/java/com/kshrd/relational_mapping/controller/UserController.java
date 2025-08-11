package com.kshrd.relational_mapping.controller;

import com.kshrd.relational_mapping.model.dto.UserDto;
import com.kshrd.relational_mapping.model.request.UserRequest;
import com.kshrd.relational_mapping.model.response.ApiResponse;
import com.kshrd.relational_mapping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        ApiResponse<List<UserDto>> response = ApiResponse.<List<UserDto>>builder()
                .success(true)
                .message("Get all users successfully!")
                .payload(users)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        ApiResponse<UserDto> response = ApiResponse.<UserDto>builder()
                .success(true)
                .message("Get user with ID " + id)
                .payload(user)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody UserRequest request) {
        UserDto user = userService.createUser(request);
        ApiResponse<UserDto> response = ApiResponse.<UserDto>builder()
                .success(true)
                .message("User created successfully!")
                .status(HttpStatus.CREATED)
                .payload(user)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        UserDto user = userService.updateUser(id, request);
        ApiResponse<UserDto> response = ApiResponse.<UserDto>builder()
                .success(true)
                .message("User with ID " + id + " updated successfully!")
                .status(HttpStatus.OK)
                .payload(user)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("User with ID " + id + " deleted successfully!")
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }
}
