package com.railway.RailwayReservationSystem.service.impl;
import com.railway.RailwayReservationSystem.entity.Role;

import com.railway.RailwayReservationSystem.dto.UserDTO;
import com.railway.RailwayReservationSystem.entity.User;
import com.railway.RailwayReservationSystem.mapper.UserMapper;
import com.railway.RailwayReservationSystem.repository.UserRepository;
import com.railway.RailwayReservationSystem.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByPhone(userDTO.getPhone())) {
            throw new RuntimeException("Phone number already exists");
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        User user = UserMapper.toEntity(userDTO);
        user.setRole(Role.USER);
        user.setEnabled(true);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
    @Override
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateProfile(User user) {

        return userRepository.save(user);
    }
}