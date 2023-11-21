package ru.skypro.homework.service;

import ru.skypro.homework.dto.*;

import java.util.List;

public interface AdsService {

    //ADS
    CreateOrUpdateAdDTO createAd(CreateOrUpdateAdDTO createOrUpdateAdDTO);

    CreateOrUpdateAdDTO updateAd(CreateOrUpdateAdDTO createOrUpdateAdDTO,AdDTO adDTO);


    List<AdDTO> getAllAds();

    AdDTO findByIdAd(long id);

    ExtendedAdDTO getAdInfo(long id);

    void deleteAd(long id);

    AdDTO updatePhotoAd(Long id);


    //Comments

    CommentsDTO getCommentsForAd(long id);

    CreateOrUpdateCommentDTO createComment(long id);

    CommentDTO deleteComment(long adId, long commentId);

    CreateOrUpdateCommentDTO updateComment(long adId, long commentId);










}
