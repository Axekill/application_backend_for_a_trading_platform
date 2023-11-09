package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.NewPassword;

public interface NewPasswordRepository extends JpaRepository<Long, NewPassword> {
}
