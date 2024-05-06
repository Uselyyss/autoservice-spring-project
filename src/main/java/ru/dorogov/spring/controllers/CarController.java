package ru.dorogov.spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dorogov.spring.DAO.CarRepository;
import ru.dorogov.spring.models.Car;

@Controller
@RequestMapping("/request")
public class CarController {

    private Long workId;

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/new")
    public String newCar(@RequestParam Long usId, Model model){
        workId = usId;
        model.addAttribute("newCar", new Car());
        return "request";
    }

    @GetMapping("/{id}")
    public String showCar(@PathVariable("id") long id, Model model) {
        Car biulding = carRepository.findById(id).stream().findAny().orElse(null);
        model.addAttribute("car", biulding);
        return "car";
    }

    @PostMapping
    public String processRequest(@ModelAttribute("newCar") @Valid Car biulding,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "request";
        biulding.setClientId(workId);

        carRepository.save(biulding);

        return "redirect:/";
    }


}
