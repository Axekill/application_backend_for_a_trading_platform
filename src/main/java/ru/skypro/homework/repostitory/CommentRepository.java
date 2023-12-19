package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Comment;

import java.util.Collection;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    @Query(value = "delete from Comment where id=:commentId and ad=:adId",nativeQuery = true)
    void deleteCommentByIdAndByCommentId(long adId, long commentId);

    Optional<Comment> findByAdIdAndId(long adId, long commentId);


    void deleteByAdId(Long id);

    Collection<Comment> getAllCommentsByAdId(long id);
}
