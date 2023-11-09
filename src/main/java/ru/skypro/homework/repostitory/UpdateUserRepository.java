package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.UpdateUser;

public interface UpdateUserRepository extends JpaRepository<Long, UpdateUser> {
}
