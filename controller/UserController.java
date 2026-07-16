package com.railway.RailwayReservationSystem.controller;

import com.railway.RailwayReservationSystem.entity.Booking;
import com.railway.RailwayReservationSystem.entity.User;
import com.railway.RailwayReservationSystem.service.BookingService;
import com.railway.RailwayReservationSystem.service.TrainService;
import com.railway.RailwayReservationSystem.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final TrainService trainService;
    private final UserService userService;
    private final BookingService bookingService;

    public UserController(TrainService trainService,
                          UserService userService,
                          BookingService bookingService) {

        this.trainService = trainService;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "user/dashboard";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "user/search";
    }

    @PostMapping("/search")
    public String searchTrain(@RequestParam String source,
                              @RequestParam String destination,
                              Model model) {

        model.addAttribute("trains",
                trainService.searchTrains(source, destination));

        return "user/search-result";
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication,
                          Model model) {

        model.addAttribute("user",
                userService.getUserByEmail(authentication.getName()));

        return "user/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User user) {

        userService.updateProfile(user);

        return "redirect:/user/profile";
    }


    @GetMapping("/payment/failed")
    public String paymentFailed(Model model) {

        model.addAttribute("message",
                "Payment Failed!");

        return "user/payment-failed";
    }

}