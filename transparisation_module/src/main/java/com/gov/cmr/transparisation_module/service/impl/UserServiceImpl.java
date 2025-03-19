package com.gov.cmr.transparisation_module.service.impl;

import com.gov.cmr.transparisation_module.exception.ResourceNotFoundException;
import com.gov.cmr.transparisation_module.model.DTO.UserDTO;
import com.gov.cmr.transparisation_module.model.entitys.User;
import com.gov.cmr.transparisation_module.repository.UserRepository;
import com.gov.cmr.transparisation_module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
