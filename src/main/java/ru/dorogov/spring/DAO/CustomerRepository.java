package ru.dorogov.spring.DAO;

import org.springframework.data.repository.CrudRepository;
import ru.dorogov.spring.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
