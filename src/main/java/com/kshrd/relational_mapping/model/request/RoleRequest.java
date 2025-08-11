package com.kshrd.relational_mapping.model.request;

import com.kshrd.relational_mapping.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {
    private String name;

    public Role toEntity() {
        return new Role(null, this.name);
    }
}
