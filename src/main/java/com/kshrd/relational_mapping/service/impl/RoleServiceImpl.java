package com.kshrd.relational_mapping.service.impl;

import com.kshrd.relational_mapping.model.dto.RoleDto;
import com.kshrd.relational_mapping.model.entity.Role;
import com.kshrd.relational_mapping.model.request.RoleRequest;
import com.kshrd.relational_mapping.repository.RoleRepository;
import com.kshrd.relational_mapping.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> mapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto getRoleById(Long id) {
        return mapper.map(roleRepository.getReferenceById(id), RoleDto.class);
    }

    @Override
    public RoleDto createRole(RoleRequest roleRequest) {
        Role role = roleRequest.toEntity();
        return mapper.map(roleRepository.save(role), RoleDto.class);
    }

    @Override
    public RoleDto updateRole(Long id, RoleRequest roleRequest) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Role not found"));
        role.setName(roleRequest.getName());
        return mapper.map(roleRepository.save(role), RoleDto.class);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
