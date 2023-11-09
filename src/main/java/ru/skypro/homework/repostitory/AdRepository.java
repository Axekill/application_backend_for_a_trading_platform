package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Ad;

public interface AdRepository extends JpaRepository<Long, Ad> {
}
