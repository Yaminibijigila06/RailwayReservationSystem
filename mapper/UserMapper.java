package com.railway.RailwayReservationSystem.mapper;

import com.railway.RailwayReservationSystem.dto.UserDTO;
import com.railway.RailwayReservationSystem.entity.Role;
import com.railway.RailwayReservationSystem.entity.User;

public class UserMapper {

    public static User toEntity(UserDTO dto) {

        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .password(dto.getPassword())
                .role(Role.USER)
                .enabled(true)
                .build();
    }
}