package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

}
