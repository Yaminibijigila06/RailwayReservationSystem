package com.railway.RailwayReservationSystem.service.impl;

import com.railway.RailwayReservationSystem.dto.TrainDTO;
import com.railway.RailwayReservationSystem.entity.Train;
import com.railway.RailwayReservationSystem.mapper.TrainMapper;
import com.railway.RailwayReservationSystem.repository.TrainRepository;
import com.railway.RailwayReservationSystem.service.TrainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;

    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public Train saveTrain(TrainDTO trainDTO) {

        if (trainRepository.existsByTrainNumber(trainDTO.getTrainNumber())) {
            throw new RuntimeException("Train Number already exists");
        }

        Train train = TrainMapper.toEntity(trainDTO);
        train.setAvailableSeats(train.getTotalSeats());

        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainById(Long id) {
        return trainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Train not found"));
    }

    @Override
    public void deleteTrain(Long id) {
        trainRepository.deleteById(id);
    }

    @Override
    public Train updateTrain(TrainDTO dto) {

        Train train = trainRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Train not found"));

        train.setTrainNumber(dto.getTrainNumber());
        train.setTrainName(dto.getTrainName());
        train.setSource(dto.getSource());
        train.setDestination(dto.getDestination());
        train.setDepartureTime(dto.getDepartureTime());
        train.setArrivalTime(dto.getArrivalTime());
        train.setTotalSeats(dto.getTotalSeats());
        train.setFare(dto.getFare());

        return trainRepository.save(train);
    }

    @Override
    public List<Train> searchTrains(String source, String destination) {

        return trainRepository.findBySourceAndDestination(source, destination);

    }
}