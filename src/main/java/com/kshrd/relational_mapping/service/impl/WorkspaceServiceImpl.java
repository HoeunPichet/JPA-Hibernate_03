package com.kshrd.relational_mapping.service.impl;

import com.kshrd.relational_mapping.model.dto.WorkspaceDto;
import com.kshrd.relational_mapping.model.entity.Workspace;
import com.kshrd.relational_mapping.model.request.WorkspaceRequest;
import com.kshrd.relational_mapping.repository.WorkspaceRepository;
import com.kshrd.relational_mapping.service.WorkspaceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<WorkspaceDto> getAllWorkspaces() {
        return workspaceRepository.findAll().stream()
                .map(workspace -> mapper.map(workspace, WorkspaceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public WorkspaceDto getWorkspaceById(Long id) {
        return mapper.map(workspaceRepository.getReferenceById(id), WorkspaceDto.class);
    }

    @Override
    public WorkspaceDto createWorkspace(WorkspaceRequest workspaceRequest) {
        Workspace workspace = workspaceRequest.toEntity();
        return mapper.map(workspaceRepository.save(workspace), WorkspaceDto.class);
    }

    @Override
    public WorkspaceDto updateWorkspace(Long id, WorkspaceRequest workspaceRequest) {
        Workspace workspace = workspaceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Workspace not found"));
        workspace.setName(workspaceRequest.getName());
        workspace.setCode(workspaceRequest.getCode());

        return mapper.map(workspaceRepository.save(workspace), WorkspaceDto.class);
    }

    @Override
    public void deleteWorkspace(Long id) {
        workspaceRepository.deleteById(id);
    }
}
