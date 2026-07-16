package com.railway.RailwayReservationSystem.controller;

import com.railway.RailwayReservationSystem.dto.TrainDTO;
import com.railway.RailwayReservationSystem.service.TrainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.railway.RailwayReservationSystem.mapper.TrainMapper;

@Controller
@RequestMapping("/admin/trains")
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping
    public String viewTrains(Model model) {

        model.addAttribute("trains", trainService.getAllTrains());

        return "admin/trains";
    }

    @GetMapping("/add")
    public String showAddTrainPage(Model model) {

        model.addAttribute("train", new TrainDTO());

        return "admin/add-train";
    }

    @PostMapping("/save")
    public String saveTrain(@ModelAttribute("train") TrainDTO trainDTO) {

        trainService.saveTrain(trainDTO);

        return "redirect:/admin/trains";
    }

    @GetMapping("/edit/{id}")
    public String editTrain(@PathVariable Long id,
                            Model model){

        model.addAttribute("train",
                TrainMapper.toDTO(trainService.getTrainById(id)));

        return "admin/edit-train";
    }

    @PostMapping("/update")
    public String updateTrain(@ModelAttribute TrainDTO trainDTO){

        trainService.updateTrain(trainDTO);

        return "redirect:/admin/trains";
    }

    @GetMapping("/delete/{id}")
    public String deleteTrain(@PathVariable Long id) {

        trainService.deleteTrain(id);

        return "redirect:/admin/trains";
    }
}