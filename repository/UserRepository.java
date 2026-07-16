package com.railway.RailwayReservationSystem.repository;
import com.railway.RailwayReservationSystem.entity.Role;

import com.railway.RailwayReservationSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByRole(Role role);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}