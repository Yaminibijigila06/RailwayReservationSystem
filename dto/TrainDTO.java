package com.railway.RailwayReservationSystem.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalTime;

@Data
public class TrainDTO {
    private Long id;

    private String trainNumber;
    private String trainName;
    private String source;
    private String destination;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime departureTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime arrivalTime;
    private Integer totalSeats;
    private Double fare;
}