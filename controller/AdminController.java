package com.railway.RailwayReservationSystem.controller;

import com.railway.RailwayReservationSystem.repository.BookingRepository;
import com.railway.RailwayReservationSystem.repository.TrainRepository;
import com.railway.RailwayReservationSystem.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final TrainRepository trainRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public AdminController(TrainRepository trainRepository,
                           UserRepository userRepository,
                           BookingRepository bookingRepository) {

        this.trainRepository = trainRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("trainCount", trainRepository.count());
        model.addAttribute("userCount", userRepository.count());
        model.addAttribute("bookingCount", bookingRepository.count());

        double revenue = bookingRepository.findAll()
                .stream()
                .mapToDouble(b -> b.getSeatsBooked() * b.getTrain().getFare())
                .sum();

        model.addAttribute("revenue", revenue);

        return "admin/dashboard";
    }

    @GetMapping("/users")
    public String users(Model model) {

        model.addAttribute("users", userRepository.findAll());

        return "admin/users";
    }

    @GetMapping("/bookings")
    public String bookings(Model model) {

        model.addAttribute("bookings", bookingRepository.findAll());

        return "admin/bookings";
    }

    @GetMapping("/reports")
    public String reports(Model model) {

        model.addAttribute("trainCount", trainRepository.count());
        model.addAttribute("userCount", userRepository.count());
        model.addAttribute("bookingCount", bookingRepository.count());

        double revenue = bookingRepository.findAll()
                .stream()
                .mapToDouble(b -> b.getSeatsBooked() * b.getTrain().getFare())
                .sum();

        model.addAttribute("revenue", revenue);

        return "admin/reports";
    }
}