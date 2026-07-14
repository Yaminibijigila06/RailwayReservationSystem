package com.railway.RailwayReservationSystem.controller;

import com.railway.RailwayReservationSystem.dto.UserDTO;
import com.railway.RailwayReservationSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {

        model.addAttribute("user", new UserDTO());

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDTO userDTO,
                               BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }

        userService.registerUser(userDTO);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
//    @GetMapping("/user/dashboard")
//    public String userDashboard() {
//        return "user/dashboard";
//    }


}