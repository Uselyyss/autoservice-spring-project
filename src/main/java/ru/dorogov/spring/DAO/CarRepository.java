package ru.dorogov.spring.DAO;

import org.springframework.data.repository.CrudRepository;
import ru.dorogov.spring.models.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
}
