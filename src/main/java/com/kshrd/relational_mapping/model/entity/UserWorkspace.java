package com.kshrd.relational_mapping.model.entity;


import com.kshrd.relational_mapping.model.composite.UserWorkspaceId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "user_workspaces")
public class UserWorkspace {
    @EmbeddedId
    private UserWorkspaceId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("workspaceId")
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    private Boolean isAccepted;
}
