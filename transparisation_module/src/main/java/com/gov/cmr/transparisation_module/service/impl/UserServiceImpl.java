package com.gov.cmr.transparisation_module.service.impl;

import com.gov.cmr.transparisation_module.exception.ResourceNotFoundException;
import com.gov.cmr.transparisation_module.model.DTO.UserDTO;
import com.gov.cmr.transparisation_module.model.entitys.User;
import com.gov.cmr.transparisation_module.repository.UserRepository;
import com.gov.cmr.transparisation_module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Map DTO to entity using Lombok's builder
        User user = mapToEntity(userDTO);
        // Override createdAt with current time using toBuilder()
        user = user.toBuilder().createdAt(LocalDateTime.now()).build();
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));
        return mapToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        // Update fields using toBuilder() from Lombok
        existingUser = existingUser.toBuilder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .address(userDTO.getAddress())
                // Optionally, keep the original createdAt value
                .build();

        User updatedUser = userRepository.save(existingUser);
        return mapToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    // Mapping from User to UserDTO using Lombok's builder
    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .address(user.getAddress())
                .createdAt(user.getCreatedAt())
                .build();
    }

    // Mapping from UserDTO to User using Lombok's builder.
    // Note: We do not set createdAt here because it is overridden in createUser.
    private User mapToEntity(UserDTO userDTO) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .address(userDTO.getAddress())
                .build();
    }
}
