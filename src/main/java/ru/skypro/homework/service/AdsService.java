package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.*;

import java.util.Collection;
import java.util.List;

public interface AdsService {

    //ADS


    CreateOrUpdateAdDTO createOrUpdateAd(CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                         AdDTO adDTO, long id);

    AdsDTO getAllAds();

    AdDTO findByIdAd(long id);


    List<AdDTO> getAdInfoAuthorizedUser(Authentication authentication);

    void deleteAd(long id);

    AdDTO updatePhotoAd(Long id, AdDTO adDTO);


    //Comments

    Collection<CommentsDTO> getCommentsForAd(long id);

    CreateOrUpdateCommentDTO createComment(CreateOrUpdateCommentDTO createOrUpdateCommentDTO, long adId, long commentId);

    void deleteComment(long adId, long commentId);


}
