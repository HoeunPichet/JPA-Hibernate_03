package com.kshrd.relational_mapping.model.request;

import com.kshrd.relational_mapping.model.entity.User;
import com.kshrd.relational_mapping.model.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileRequest {
    private String bio;

    private String gender;

    private String address;

    public UserProfile toEntity(User user) {
        return new UserProfile(null, this.bio, this.gender, this.address, user);
    }
}
