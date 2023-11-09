package ru.skypro.homework.repostitory;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Comments;

public interface CommentsRepository extends JpaRepository<Long, Comments> {
}
