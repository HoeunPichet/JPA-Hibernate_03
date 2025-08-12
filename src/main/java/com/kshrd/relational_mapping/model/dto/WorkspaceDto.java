package com.kshrd.relational_mapping.model.dto;

import com.kshrd.relational_mapping.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceDto {
    private Long id;

    private String name;

    private String code;

    private List<UserDto> users;
}
