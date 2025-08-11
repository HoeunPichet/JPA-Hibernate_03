package com.kshrd.relational_mapping.controller;


import com.kshrd.relational_mapping.model.dto.RoleDto;
import com.kshrd.relational_mapping.model.request.RoleRequest;
import com.kshrd.relational_mapping.model.response.ApiResponse;
import com.kshrd.relational_mapping.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleDto>>> getAllRoles() {
        List<RoleDto> roles = roleService.getAllRoles();
        ApiResponse<List<RoleDto>> response = ApiResponse.<List<RoleDto>>builder()
                .success(true)
                .message("Get all roles successfully!")
                .payload(roles)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDto>> getRoleById(@PathVariable Long id) {
        RoleDto role = roleService.getRoleById(id);
        ApiResponse<RoleDto> response = ApiResponse.<RoleDto>builder()
                .success(true)
                .message("Get role with ID " + id)
                .payload(role)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoleDto>> createRole(@RequestBody RoleRequest request) {
        RoleDto role = roleService.createRole(request);
        ApiResponse<RoleDto> response = ApiResponse.<RoleDto>builder()
                .success(true)
                .message("Role created successfully!")
                .status(HttpStatus.CREATED)
                .payload(role)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDto>> updateRole(@PathVariable Long id, @RequestBody RoleRequest request) {
        RoleDto role = roleService.updateRole(id, request);
        ApiResponse<RoleDto> response = ApiResponse.<RoleDto>builder()
                .success(true)
                .message("Role with ID " + id + " updated successfully!")
                .status(HttpStatus.OK)
                .payload(role)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("Role with ID " + id + " deleted successfully!")
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }
}
