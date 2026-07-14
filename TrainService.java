package com.railway.RailwayReservationSystem.service;

import com.railway.RailwayReservationSystem.dto.TrainDTO;
import com.railway.RailwayReservationSystem.entity.Train;

import java.util.List;

public interface TrainService {

    Train saveTrain(TrainDTO trainDTO);

    Train updateTrain(TrainDTO trainDTO);

    List<Train> getAllTrains();
    List<Train> searchTrains(String source, String destination);

    Train getTrainById(Long id);

    void deleteTrain(Long id);
}