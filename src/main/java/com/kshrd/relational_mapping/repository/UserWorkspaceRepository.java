package com.kshrd.relational_mapping.repository;

import com.kshrd.relational_mapping.model.composite.UserWorkspaceId;
import com.kshrd.relational_mapping.model.entity.UserWorkspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWorkspaceRepository extends JpaRepository<UserWorkspace, UserWorkspaceId> {
}
