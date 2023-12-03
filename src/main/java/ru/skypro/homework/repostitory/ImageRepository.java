package ru.skypro.homework.repostitory;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Image;

public interface ImageRepository extends CrudRepository<Image,Long> {
}
