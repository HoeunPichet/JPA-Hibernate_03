package com.kshrd.relational_mapping.service;

import com.kshrd.relational_mapping.model.dto.RoleDto;
import com.kshrd.relational_mapping.model.request.RoleRequest;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAllRoles();

    RoleDto getRoleById(Long id);

    RoleDto createRole(RoleRequest roleRequest);

    RoleDto updateRole(Long id, RoleRequest roleRequest);

    void deleteRole(Long id);
}
