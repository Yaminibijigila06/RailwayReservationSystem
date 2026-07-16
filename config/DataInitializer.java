package com.railway.RailwayReservationSystem.config;

import com.railway.RailwayReservationSystem.entity.Role;
import com.railway.RailwayReservationSystem.entity.User;
import com.railway.RailwayReservationSystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository,
                                PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.findByEmail("admin@railway.com").isEmpty()) {

                User admin = User.builder()
                        .name("System Administrator")
                        .email("admin@railway.com")
                        .phone("9999999999")
                        .password(passwordEncoder.encode("Admin@Railway2026!"))
                        .role(Role.ADMIN)
                        .enabled(true)
                        .build();

                userRepository.save(admin);

                System.out.println("====================================");
                System.out.println("ADMIN ACCOUNT CREATED");
                System.out.println("Email    : admin@railway.com");
                System.out.println("Password : Admin@Railway2026!");
                System.out.println("====================================");
            }
        };
    }
}