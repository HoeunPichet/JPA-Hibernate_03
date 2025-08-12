package com.kshrd.relational_mapping.repository;

import com.kshrd.relational_mapping.model.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    @Query("""
            SELECT w FROM Workspace w
            LEFT JOIN FETCH w.userWorkspaces uw
            LEFT JOIN FETCH uw.user
            WHERE w.id = :id
            """)
    Optional<Workspace> findByIdWithUsers(@Param("id") Long id);
}
