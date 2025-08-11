package com.kshrd.relational_mapping.controller;

import com.kshrd.relational_mapping.model.dto.WorkspaceDto;
import com.kshrd.relational_mapping.model.request.WorkspaceRequest;
import com.kshrd.relational_mapping.model.response.ApiResponse;
import com.kshrd.relational_mapping.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/workspace")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<WorkspaceDto>>> getAllWorkspaces() {
        List<WorkspaceDto> workspaces = workspaceService.getAllWorkspaces();
        ApiResponse<List<WorkspaceDto>> response = ApiResponse.<List<WorkspaceDto>>builder()
                .success(true)
                .message("Get all workspaces successfully!")
                .payload(workspaces)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkspaceDto>> getWorkspaceById(@PathVariable Long id) {
        WorkspaceDto workspace = workspaceService.getWorkspaceById(id);
        ApiResponse<WorkspaceDto> response = ApiResponse.<WorkspaceDto>builder()
                .success(true)
                .message("Get workspace with ID " + id)
                .payload(workspace)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WorkspaceDto>> createWorkspace(@RequestBody WorkspaceRequest request) {
        WorkspaceDto workspace = workspaceService.createWorkspace(request);
        ApiResponse<WorkspaceDto> response = ApiResponse.<WorkspaceDto>builder()
                .success(true)
                .message("Workspace created successfully!")
                .status(HttpStatus.CREATED)
                .payload(workspace)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkspaceDto>> updateWorkspace(@PathVariable Long id, @RequestBody WorkspaceRequest request) {
        WorkspaceDto workspace = workspaceService.updateWorkspace(id, request);
        ApiResponse<WorkspaceDto> response = ApiResponse.<WorkspaceDto>builder()
                .success(true)
                .message("Workspace with ID " + id + " updated successfully!")
                .status(HttpStatus.OK)
                .payload(workspace)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteWorkspace(@PathVariable Long id) {
        workspaceService.deleteWorkspace(id);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("Workspace with ID " + id + " deleted successfully!")
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }
}
