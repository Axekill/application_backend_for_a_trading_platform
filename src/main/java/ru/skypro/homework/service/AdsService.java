package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.*;

public interface AdsService {

    //ADS
    CreateOrUpdateAdDTO createAd(CreateOrUpdateAdDTO createOrUpdateAdDTO);


    CreateOrUpdateAdDTO updateAd(CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                 AdDTO adDTO, long id);

    AdsDTO getAllAds();

    AdDTO findByIdAd(long id);

    AdsDTO getAdInfoAuthorizedUser(Authentication authentication, AdsDTO adsDTO);

    void deleteAd(long id);

    AdDTO updatePhotoAd(Long id);


    //Comments

    CommentsDTO getCommentsForAd(long id);

    CreateOrUpdateCommentDTO createComment(long id);

    CommentDTO deleteComment(long adId, long commentId);

    CreateOrUpdateCommentDTO updateComment(long adId, long commentId);










}
