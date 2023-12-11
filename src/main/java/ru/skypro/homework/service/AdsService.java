package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.util.Collection;
import java.util.List;

public interface AdsService {

    //ADS

    AdDTO createAd(CreateOrUpdateAdDTO createOrUpdateAdDTO,
                   MultipartFile image);

    AdDTO updateAd(long id, CreateOrUpdateAdDTO updateAdDTO,
                   Authentication authentication);

    AdsDTO getAllAds();

    AdDTO findByIdAd(long id);

    List<AdDTO> getAdInfoAuthorizedUser(Authentication authentication);

    void deleteAd(long id, Authentication authentication);

    void updatePhotoAd(Long id, MultipartFile imageFile,
                       Authentication authentication) throws Exception;
    //Comments

    Collection<CommentDTO> getCommentsForAd(long id);

    CommentDTO createComment(CreateOrUpdateCommentDTO createOrUpdateCommentDTO,
                             long id, Authentication authentication);

    CreateOrUpdateCommentDTO updateComment(CreateOrUpdateCommentDTO updateCommentDTO, long adId,
                                           long commentId, Authentication authentication);

    void deleteComment(long adId, long commentId);

}
