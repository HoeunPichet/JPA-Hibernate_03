package com.kshrd.relational_mapping.service;

import com.kshrd.relational_mapping.model.dto.WorkspaceDto;
import com.kshrd.relational_mapping.model.request.WorkspaceRequest;

import java.util.List;

public interface WorkspaceService {
    List<WorkspaceDto> getAllWorkspaces();

    WorkspaceDto getWorkspaceById(Long id);

    WorkspaceDto createWorkspace(WorkspaceRequest workspaceRequest);

    WorkspaceDto updateWorkspace(Long id, WorkspaceRequest workspaceRequest);

    void deleteWorkspace(Long id);

    void addUserToWorkspace(Long workspaceId, Long userId);

    void acceptUserInWorkspace(Long workspaceId, Long userId, Boolean isAccepted);
}
