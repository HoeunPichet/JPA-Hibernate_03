package com.kshrd.relational_mapping.model.request;

import com.kshrd.relational_mapping.model.entity.Role;
import com.kshrd.relational_mapping.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String username;

    private String email;

    private String password;

    private Long roleId;

    private UserProfileRequest userProfile;

    public User toEntity(Role role) {
        return new User(null, this.username, this.email, this.password, role, null);
    }
}
