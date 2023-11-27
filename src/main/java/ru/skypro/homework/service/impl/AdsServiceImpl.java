package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CreateOrUpdateAdMapper;
import ru.skypro.homework.mapper.CreateOrUpdateCommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repostitory.AdRepository;
import ru.skypro.homework.repostitory.CommentRepository;
import ru.skypro.homework.service.AdsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class AdsServiceImpl implements AdsService {
    @Autowired
    private AdRepository adRepository;
    private CommentRepository commentRepository;
    private AdMapper adMapper;
    private CreateOrUpdateAdMapper createOrUpdateAdMapper;
    private CreateOrUpdateCommentMapper createOrUpdateCommentMapper;

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
                    Ad updateAd = adRepository.save(ad);
                    return createOrUpdateAdMapper.toDTO(updateAd);
                }).orElse(null);

    }

    @Override
    public AdsDTO getAllAds() {
        List<AdDTO> result = new ArrayList<>();
        var find = adRepository.findAll();
        for (Ad ad : find) {
            result.add(adMapper.adDtoToDTO(ad));
        }
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setResult(result);
        adsDTO.setCount(result.size());

        return adsDTO;
    }

    @Override
    public AdDTO findByIdAd(long id) {
        return adRepository.findById(id)
                .map(adMapper::adDtoToDTO)
                .orElse(null);
    }

    @Override
    public AdsDTO getAdInfoAuthorizedUser(Authentication authentication, AdsDTO adsDTO) {
//authentication.getName()
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
    public Collection<CommentsDTO> getCommentsForAd(long id) {
        return adRepository.getAllCommentsByAdId(id);
    }

    @Override
    public CreateOrUpdateCommentDTO createComment(CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {

        Comment comment = createOrUpdateCommentMapper.toEntity(createOrUpdateCommentDTO);
        Comment saveCom = commentRepository.save(comment);
        return createOrUpdateCommentMapper.toDTO(saveCom);
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
