package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Comment;

public interface CommentRepository extends JpaRepository<Long, Comment> {
}
