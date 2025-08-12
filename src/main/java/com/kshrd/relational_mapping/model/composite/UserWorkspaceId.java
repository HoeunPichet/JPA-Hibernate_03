package com.kshrd.relational_mapping.model.composite;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class UserWorkspaceId implements Serializable {
    private Long userId;

    private Long workspaceId;
}
