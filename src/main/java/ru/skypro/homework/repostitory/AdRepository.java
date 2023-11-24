package ru.skypro.homework.repostitory;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;

import java.util.Collection;

public interface AdRepository extends CrudRepository<Ad,Long> {

    Collection<CommentsDTO> getAllCommentsByAdId(long id);
}
