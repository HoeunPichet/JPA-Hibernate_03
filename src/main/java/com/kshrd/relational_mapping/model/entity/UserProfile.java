package com.kshrd.relational_mapping.model.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bio;

    @Column(nullable = false)
    private String gender;

    @Column(columnDefinition = "TEXT")
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
