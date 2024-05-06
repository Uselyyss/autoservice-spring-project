package ru.dorogov.spring.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dorogov.spring.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByLogin(String login);
    boolean existsByLogin(String login);
}