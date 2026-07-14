package com.railway.RailwayReservationSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter a valid 10-digit phone number")
    private String phone;

    @Size(min = 6, message = "Password must contain at least 6 characters")
    private String password;

    private String confirmPassword;
}