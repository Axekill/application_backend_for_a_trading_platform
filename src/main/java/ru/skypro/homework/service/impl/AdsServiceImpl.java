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
import ru.skypro.homework.model.User;
import ru.skypro.homework.repostitory.AdRepository;
import ru.skypro.homework.repostitory.CommentRepository;
import ru.skypro.homework.repostitory.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AdsServiceImpl implements AdsService {
    @Autowired
    private AdRepository adRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;
    private AdMapper adMapper;
    private CreateOrUpdateAdMapper createOrUpdateAdMapper;
    private CreateOrUpdateCommentMapper createOrUpdateCommentMapper;

    //Ads


    @Override
    public CreateOrUpdateAdDTO createOrUpdateAd(CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                                AdDTO adDTO, long id) {
        Ad ad = createOrUpdateAdMapper.toEntity(createOrUpdateAdDTO);
        if (adRepository.findById(id) == null) {
            Ad savedAd = adRepository.save(ad);
            return createOrUpdateAdMapper.toDTO(savedAd);
        } else {
            ad.setTitle(ad.getTitle());
            ad.setPrice(ad.getPrice());
            ad.setDescription(ad.getDescription());
            Ad updateAd = adRepository.save(ad);
            return createOrUpdateAdMapper.toDTO(updateAd);
        }

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
    public List<AdDTO> getAdInfoAuthorizedUser(Authentication authentication) {
        User user = userRepository.findByUserName(authentication.getName()).orElseThrow();
        return adRepository.findAllAdByUserId(user.getId()).stream()
                .map(adMapper::adDtoToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAd(long id) {
        adRepository.deleteById(id);
    }

    @Override
    public AdDTO updatePhotoAd(Long id, AdDTO adDTO) {
        Ad ad = adMapper.adToEntity(adDTO);
        return adRepository.findById(id)
                .map(i -> {
                    i.setImage(i.getImage());
                    Ad updateImage = adRepository.save(ad);
                    return adMapper.adDtoToDTO(updateImage);
                }).orElse(null);
    }


    //Comments
    @Override
    public Collection<CommentsDTO> getCommentsForAd(long id) {
        return adRepository.getAllCommentsByAdId(id);
    }

    @Override
    public CreateOrUpdateCommentDTO createComment(CreateOrUpdateCommentDTO createOrUpdateCommentDTO, long id) {

        Comment comment = createOrUpdateCommentMapper.toEntity(createOrUpdateCommentDTO);
        if (commentRepository.findById(id) == null) {
            Comment saveCom = commentRepository.save(comment);
            return createOrUpdateCommentMapper.toDTO(saveCom);
        } else {
            comment.setTextComment(comment.getTextComment());
            Comment updateComment = commentRepository.save(comment);
            return createOrUpdateCommentMapper.toDTO(updateComment);
        }
    }

    @Override
    public void deleteComment(long adId, long commentId) {
        adRepository.deleteCommentByIdInFindIdAd(adId, commentId);
    }

}
