package com.railway.RailwayReservationSystem.mapper;

import com.railway.RailwayReservationSystem.dto.TrainDTO;
import com.railway.RailwayReservationSystem.entity.Train;

public class TrainMapper {

    public static Train toEntity(TrainDTO dto) {

        return Train.builder()
                .id(dto.getId())
                .trainNumber(dto.getTrainNumber())
                .trainName(dto.getTrainName())
                .source(dto.getSource())
                .destination(dto.getDestination())
                .departureTime(dto.getDepartureTime())
                .arrivalTime(dto.getArrivalTime())
                .totalSeats(dto.getTotalSeats())
                .availableSeats(dto.getTotalSeats())
                .fare(dto.getFare())
                .build();
    }

    public static TrainDTO toDTO(Train train){

        TrainDTO dto = new TrainDTO();

        dto.setId(train.getId());
        dto.setTrainNumber(train.getTrainNumber());
        dto.setTrainName(train.getTrainName());
        dto.setSource(train.getSource());

        dto.setDestination(train.getDestination());
        dto.setDepartureTime(train.getDepartureTime());
        dto.setArrivalTime(train.getArrivalTime());
        dto.setTotalSeats(train.getTotalSeats());
        dto.setFare(train.getFare());

        return dto;
    }
}