package com.railway.RailwayReservationSystem.controller;

import com.railway.RailwayReservationSystem.dto.BookingDTO;
import com.railway.RailwayReservationSystem.service.BookingService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/book/{trainId}")
    public String bookingPage(@PathVariable Long trainId,
                              Model model) {

        BookingDTO dto = new BookingDTO();
        dto.setTrainId(trainId);

        model.addAttribute("booking", dto);

        return "user/book-ticket";
    }

    @PostMapping("/book")
    public String confirmBooking(@ModelAttribute("booking") BookingDTO dto,
                                 Authentication authentication) {

        bookingService.bookTicket(dto, authentication.getName());

        return "redirect:/user/bookings";
    }

    @GetMapping("/bookings")
    public String myBookings(Authentication authentication,
                             Model model) {

        model.addAttribute("bookings",
                bookingService.myBookings(authentication.getName()));

        return "user/my-bookings";
    }
    @GetMapping("/booking/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {

        bookingService.cancelBooking(id);

        return "redirect:/user/bookings";
    }
}
