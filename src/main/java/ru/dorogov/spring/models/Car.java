package ru.dorogov.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Make is required")
    private String brand;

    @NotEmpty(message = "Model is required")
    private String model;

    @NotNull
    private String typeOfWork;

    @NotNull
    private String description;

    private Long clientId;

    // Дополнительные поля могут быть добавлены
}
