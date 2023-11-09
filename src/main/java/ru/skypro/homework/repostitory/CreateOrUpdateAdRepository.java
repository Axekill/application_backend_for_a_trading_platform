package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.CreateOrUpdateAd;

public interface CreateOrUpdateAdRepository extends JpaRepository<Long, CreateOrUpdateAd> {
}
