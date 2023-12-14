package ru.skypro.homework.repostitory;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Image;

import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image, Long> {
    Optional<Image> findByUsersId(Long userId);
}
