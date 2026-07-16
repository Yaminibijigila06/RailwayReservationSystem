package com.railway.RailwayReservationSystem.service;

import com.railway.RailwayReservationSystem.dto.BookingDTO;
import com.railway.RailwayReservationSystem.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking bookTicket(BookingDTO dto, String email);

    List<Booking> myBookings(String email);

    void cancelBooking(Long bookingId);


    Booking getBookingById(Long id);


    void paymentSuccess(Long bookingId);
}