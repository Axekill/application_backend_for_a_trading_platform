package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Register;

public interface RegisterRepository extends JpaRepository<Long, Register> {
}
