package com.railway.RailwayReservationSystem.service.impl;

import com.railway.RailwayReservationSystem.dto.BookingDTO;
import com.railway.RailwayReservationSystem.entity.Booking;
import com.railway.RailwayReservationSystem.entity.Train;
import com.railway.RailwayReservationSystem.entity.User;
import com.railway.RailwayReservationSystem.repository.BookingRepository;
import com.railway.RailwayReservationSystem.repository.TrainRepository;
import com.railway.RailwayReservationSystem.repository.UserRepository;
import com.railway.RailwayReservationSystem.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TrainRepository trainRepository;
    private final UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              TrainRepository trainRepository,
                              UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.trainRepository = trainRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Booking bookTicket(BookingDTO dto, String email) {

        Train train = trainRepository.findById(dto.getTrainId())
                .orElseThrow(() -> new RuntimeException("Train not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (train.getAvailableSeats() < dto.getSeatsBooked()) {
            throw new RuntimeException("Seats not available");
        }

        train.setAvailableSeats(
                train.getAvailableSeats() - dto.getSeatsBooked());

        trainRepository.save(train);

        Booking booking = Booking.builder()
                .passengerName(dto.getPassengerName())
                .passengerAge(dto.getPassengerAge())
                .gender(dto.getGender())
                .seatsBooked(dto.getSeatsBooked())
                .bookingDate(LocalDateTime.now())
                .pnr(UUID.randomUUID().toString().substring(0,8).toUpperCase())
                .train(train)
                .user(user)
                .build();

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> myBookings(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return bookingRepository.findByUser(user);
    }

    @Override
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Train train = booking.getTrain();

        train.setAvailableSeats(
                train.getAvailableSeats() + booking.getSeatsBooked()
        );

        trainRepository.save(train);

        bookingRepository.delete(booking);
    }
}
