package com.kshrd.relational_mapping.service.impl;

import com.kshrd.relational_mapping.model.composite.UserWorkspaceId;
import com.kshrd.relational_mapping.model.dto.UserDto;
import com.kshrd.relational_mapping.model.dto.WorkspaceDto;
import com.kshrd.relational_mapping.model.entity.User;
import com.kshrd.relational_mapping.model.entity.UserWorkspace;
import com.kshrd.relational_mapping.model.entity.Workspace;
import com.kshrd.relational_mapping.model.request.WorkspaceRequest;
import com.kshrd.relational_mapping.repository.UserRepository;
import com.kshrd.relational_mapping.repository.UserWorkspaceRepository;
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
    private final UserWorkspaceRepository userWorkspaceRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<WorkspaceDto> getAllWorkspaces() {
        List<WorkspaceDto> workspaces = workspaceRepository.findAll()
                .stream()
                .map(workspace -> {
                    WorkspaceDto workspaceDto = mapper.map(workspace, WorkspaceDto.class);

                    List<UserDto> userDtos = workspace.getUserWorkspaces().stream()
                            .map(UserWorkspace::getUser)
                            .map(u -> mapper.map(u, UserDto.class))
                            .collect(Collectors.toList());

                    workspaceDto.setUsers(userDtos);

                    return workspaceDto;
                })
                .collect(Collectors.toList());

        return workspaces;
    }

    @Override
    public WorkspaceDto getWorkspaceById(Long id) {
        Workspace workspace = workspaceRepository.findByIdWithUsers(id)
                .orElseThrow();
        WorkspaceDto workspaceDto = mapper.map(workspace, WorkspaceDto.class);
        List<UserDto> userDtos = workspace.getUserWorkspaces().stream()
                .map(UserWorkspace::getUser)
                .map(u -> mapper.map(u, UserDto.class))
                .collect(Collectors.toList());
        workspaceDto.setUsers(userDtos);

        return workspaceDto;
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

    @Override
    public void addUserToWorkspace(Long workspaceId, Long userId) {
        UserWorkspace userWorkspace = new UserWorkspace();

        UserWorkspaceId userWorkspaceId = new UserWorkspaceId();
        userWorkspaceId.setUserId(userId);
        userWorkspaceId.setWorkspaceId(workspaceId);

        userWorkspace.setId(userWorkspaceId);

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        userWorkspace.setUser(user);
        userWorkspace.setWorkspace(workspace);
        userWorkspace.setIsAccepted(false);

        userWorkspaceRepository.save(userWorkspace);
    }

    @Override
    public void acceptUserInWorkspace(Long workspaceId, Long userId, Boolean isAccepted) {
        UserWorkspaceId id = new UserWorkspaceId();
        id.setUserId(userId);
        id.setWorkspaceId(workspaceId);

        UserWorkspace userWorkspace = userWorkspaceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserWorkspace not found"));

        userWorkspace.setIsAccepted(isAccepted);

        userWorkspaceRepository.save(userWorkspace);
    }
}
