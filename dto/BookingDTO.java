package com.railway.RailwayReservationSystem.dto;

import lombok.Data;

@Data
public class BookingDTO {

    private Long trainId;

    private String passengerName;

    private Integer passengerAge;

    private String gender;

    private Integer seatsBooked;
}
