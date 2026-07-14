package com.railway.RailwayReservationSystem.service;

import com.railway.RailwayReservationSystem.dto.UserDTO;
import com.railway.RailwayReservationSystem.entity.User;

public interface UserService {

    User registerUser(UserDTO userDTO);

    User getUserByEmail(String email);

    User updateProfile(User user);

}