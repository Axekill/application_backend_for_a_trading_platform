package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.User;

public interface UserRepository extends JpaRepository<Long, User> {
}
