package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Comment;

public interface CommentRepository extends CrudRepository<Comment,Long> {
}
