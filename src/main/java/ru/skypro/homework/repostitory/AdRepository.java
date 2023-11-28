package ru.skypro.homework.repostitory;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;

import java.util.Collection;
import java.util.List;

public interface AdRepository extends CrudRepository<Ad,Long> {

    Collection<CommentsDTO> getAllCommentsByAdId(long id);
    List<Ad> findAllAdByUserId(long id);
    Collection<AdDTO>deleteCommentByIdInFindIdAd(long adId,long commentId);

   // delete from Comment where Ad.id=adId & commentId=Comment.id
}
