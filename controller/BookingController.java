package com.railway.RailwayReservationSystem.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.railway.RailwayReservationSystem.dto.BookingDTO;
import com.railway.RailwayReservationSystem.entity.Booking;
import com.railway.RailwayReservationSystem.service.BookingService;
import jakarta.servlet.http.HttpServletResponse;
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

    // ================= Book Ticket =================

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

        Booking booking =
                bookingService.bookTicket(dto, authentication.getName());

        return "redirect:/user/payment/" + booking.getId();
    }

    // ================= Payment =================

    @GetMapping("/payment/{id}")
    public String paymentPage(@PathVariable Long id,
                              Model model) {

        model.addAttribute("booking",
                bookingService.getBookingById(id));

        return "user/payment";
    }

    @PostMapping("/payment/success")
    public String paymentSuccess(@RequestParam Long bookingId) {

        bookingService.paymentSuccess(bookingId);

        return "redirect:/user/ticket/" + bookingId;
    }

    // ================= View Ticket =================

    @GetMapping("/ticket/{id}")
    public String viewTicket(@PathVariable Long id,
                             Model model) {

        model.addAttribute("booking",
                bookingService.getBookingById(id));

        return "user/ticket";
    }

    // ================= Download Ticket =================

    @GetMapping("/ticket/download/{id}")
    public void downloadTicket(@PathVariable Long id,
                               HttpServletResponse response) throws Exception {

        Booking booking = bookingService.getBookingById(id);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=Railway_Ticket.pdf");

        Document document = new Document();

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font title =
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);

        document.add(new Paragraph("RAILWAY RESERVATION SYSTEM", title));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("PNR Number : " + booking.getPnr()));
        document.add(new Paragraph("Passenger Name : " + booking.getPassengerName()));
        document.add(new Paragraph("Age : " + booking.getPassengerAge()));
        document.add(new Paragraph("Gender : " + booking.getGender()));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Train Number : " + booking.getTrain().getTrainNumber()));
        document.add(new Paragraph("Train Name : " + booking.getTrain().getTrainName()));
        document.add(new Paragraph("Source : " + booking.getTrain().getSource()));
        document.add(new Paragraph("Destination : " + booking.getTrain().getDestination()));
        document.add(new Paragraph("Departure : " + booking.getTrain().getDepartureTime()));
        document.add(new Paragraph("Arrival : " + booking.getTrain().getArrivalTime()));
        document.add(new Paragraph("Seats Booked : " + booking.getSeatsBooked()));
        document.add(new Paragraph("Payment Status : " + booking.getPaymentStatus()));
        document.add(new Paragraph("Booking Date : " + booking.getBookingDate()));
        document.add(new Paragraph("Total Fare : ₹" +
                (booking.getTrain().getFare() * booking.getSeatsBooked())));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("-------------------------------------------"));
        document.add(new Paragraph("Thank You For Booking With Us"));
        document.add(new Paragraph("Have a Safe Journey"));
        document.add(new Paragraph("-------------------------------------------"));

        document.close();
    }

    // ================= My Bookings =================

    @GetMapping("/bookings")
    public String myBookings(Authentication authentication,
                             Model model) {

        model.addAttribute("bookings",
                bookingService.myBookings(authentication.getName()));

        return "user/my-bookings";
    }

    // ================= Cancel Booking =================

    @GetMapping("/booking/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {

        bookingService.cancelBooking(id);

        return "redirect:/user/bookings";
    }

}