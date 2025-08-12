package com.kshrd.relational_mapping.model.request;

import com.kshrd.relational_mapping.model.entity.Workspace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkspaceRequest {
    private String name;

    private String code;

    public Workspace toEntity() {
        return new Workspace(null, this.name, this.code, null);
    }
}
