package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Login;

public interface LoginRepostitory extends JpaRepository<Long, Login> {
}
