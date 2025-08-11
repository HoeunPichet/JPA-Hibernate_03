package com.kshrd.relational_mapping.model.composite;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class UserWorkspaceId {
    private Long userId;

    private Long workspaceId;
}
