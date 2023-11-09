package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.CreateOrUpdateComment;

public interface CreateOrUpdateCommentRepository  extends JpaRepository<Long, CreateOrUpdateComment> {
}
