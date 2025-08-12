package com.kshrd.relational_mapping.service.impl;

import com.kshrd.relational_mapping.model.dto.UserDto;
import com.kshrd.relational_mapping.model.entity.*;
import com.kshrd.relational_mapping.model.request.UserRequest;
import com.kshrd.relational_mapping.repository.RoleRepository;
import com.kshrd.relational_mapping.repository.UserRepository;
import com.kshrd.relational_mapping.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        return mapper.map(userRepository.getReferenceById(id), UserDto.class);
    }

    @Override
    public UserDto createUser(UserRequest userRequest) {
        Role role = roleRepository.getReferenceById(userRequest.getRoleId());
        User user = userRequest.toEntity(role);
        UserProfile userProfile = userRequest.getUserProfile().toEntity(user);
        user.setUserProfile(userProfile);

        return mapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto updateUser(Long id, UserRequest userRequest) {
        Role role = roleRepository.getReferenceById(userRequest.getRoleId());
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(role);

        UserProfile userProfile = user.getUserProfile();
        userProfile.setBio(userRequest.getUserProfile().getBio());
        userProfile.setGender(userRequest.getUserProfile().getGender());
        userProfile.setAddress(userRequest.getUserProfile().getAddress());

        return mapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
