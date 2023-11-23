package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CreateOrUpdateAdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repostitory.AdRepository;
import ru.skypro.homework.repostitory.CommentRepository;
import ru.skypro.homework.service.AdsService;

@Component
@AllArgsConstructor
public class AdsServiceImpl implements AdsService {
    @Autowired
    private AdRepository adRepository;
    private CommentRepository commentRepository;
    private AdMapper adMapper;
    private CreateOrUpdateAdMapper createOrUpdateAdMapper;

    //Ads

    public CreateOrUpdateAdDTO createAd(CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        Ad ad = createOrUpdateAdMapper.toEntity(createOrUpdateAdDTO);
        Ad savedAd = adRepository.save(ad);
        return createOrUpdateAdMapper.toDTO(savedAd);
    }

    @Override
    public CreateOrUpdateAdDTO updateAd(CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                        AdDTO adDTO, long id) {
       return adRepository.findById(id)
                .map(ad -> {
                    ad.setTitle(ad.getTitle());
                    ad.setPrice(ad.getPrice());
                    ad.setDescription(ad.getDescription());
                    return createAd(createOrUpdateAdDTO);
                }).orElse(null);

    }

    @Override
    public AdsDTO getAllAds() {
        return (AdsDTO) adRepository.findAll();
    }

    @Override
    public AdDTO findByIdAd(long id) {
        return adRepository.findById(id)
                .map(adMapper::adDtoToDTO)
                .orElse(null);
    }

    @Override
    public AdsDTO getAdInfoAuthorizedUser(Authentication authentication, AdsDTO adsDTO) {

        return null;
    }

    @Override
    public void deleteAd(long id) {
        adRepository.deleteById(id);
    }

    @Override
    public AdDTO updatePhotoAd(Long id) {
        return null;
    }


    //Comments
    @Override
    public CommentsDTO getCommentsForAd(long id) {
        return null;
    }

    @Override
    public CreateOrUpdateCommentDTO createComment(long id) {
        return null;
    }

    @Override
    public CommentDTO deleteComment(long adId, long commentId) {
        return null;
    }

    @Override
    public CreateOrUpdateCommentDTO updateComment(long adId, long commentId) {
        return null;
    }
}
