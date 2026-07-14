package com.railway.RailwayReservationSystem.repository;

import com.railway.RailwayReservationSystem.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {

    List<Train> findBySourceAndDestination(String source, String destination);

    boolean existsByTrainNumber(String trainNumber);
}