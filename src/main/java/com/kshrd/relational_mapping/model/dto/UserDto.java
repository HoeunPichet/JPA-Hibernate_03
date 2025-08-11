package com.kshrd.relational_mapping.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String username;

    private String email;

    private RoleDto role;

    private UserProfileDto userProfile;
}
