package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Ads;

public interface AdsRepository extends JpaRepository<Long, Ads> {
}
