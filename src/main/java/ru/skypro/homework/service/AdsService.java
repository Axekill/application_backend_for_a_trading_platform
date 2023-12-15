package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.io.IOException;
import java.util.Collection;

public interface AdsService {

    //ADS


    //Ads
    AdDTO createAd(CreateOrUpdateAdDTO createOrUpdateAdDTO,
                   MultipartFile image,
                   Authentication authentication) throws IOException;

    AdDTO updateAd(long id, CreateOrUpdateAdDTO updateAdDTO,
                   Authentication authentication);

    AdsDTO getAllAds();

    ExtendedAdDTO findByIdAd(long id);

    AdsDTO getAdInfoAuthorizedUser(String email);

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
