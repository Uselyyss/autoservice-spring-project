package ru.dorogov.spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dorogov.spring.DAO.CarRepository;
import ru.dorogov.spring.DAO.CustomerRepository;
import ru.dorogov.spring.models.Car;
import ru.dorogov.spring.models.Customer;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public String index(Model model){
        Iterable<Customer> clients = customerRepository.findAll();
        model.addAttribute("customers", clients);
        return "customers";
    }

    @GetMapping("/newCustomer")
    public String newClient(Model model) {
        model.addAttribute("customer", new Customer());
        return "newCustomerForm";
    }

    @GetMapping("/{id}")
    public String showClient(@PathVariable("id") long id, Model model) {
        Customer client = customerRepository.findById(id).stream().findAny().orElse(null);
        model.addAttribute("customer", client);
        Iterable<Car> biuldings = carRepository.findAll();
        model.addAttribute("cars",biuldings);
        return "customerForm";
    }

    @PostMapping()
    public String processCreate(@ModelAttribute("customer") @Valid Customer client,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "newCustomerForm";

        customerRepository.save(client);

        return "redirect:/customers";
    }

}
