package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.CreateOrUpdateAdMapper;
import ru.skypro.homework.mapper.CreateOrUpdateCommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repostitory.AdRepository;
import ru.skypro.homework.repostitory.CommentRepository;
import ru.skypro.homework.repostitory.ImageRepository;
import ru.skypro.homework.repostitory.UserRepository;
import ru.skypro.homework.security.SecurityCheck;
import ru.skypro.homework.service.AdsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AdsServiceImpl implements AdsService {
    @Autowired
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final AdMapper adMapper;
    private final CreateOrUpdateAdMapper createOrUpdateAdMapper;
    private final CreateOrUpdateCommentMapper createOrUpdateCommentMapper;
    private final ImageRepository imageRepository;
    private final SecurityCheck securityCheck;
    private final CommentMapper commentMapper;

    //Ads


    @Override
    public CreateOrUpdateAdDTO createOrUpdateAd(CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                                AdDTO adDTO, long id) {
        userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        Ad ad = createOrUpdateAdMapper.toEntity(createOrUpdateAdDTO);
        if (adRepository.findById(id).isEmpty()) {
            Ad savedAd = adRepository.save(ad);
            return createOrUpdateAdMapper.toDTO(savedAd);
        } else {
            ad.setId(ad.getId());
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
        Users users = userRepository.findByUserName(authentication.getName()).orElseThrow();
        return adRepository.findAllAdByUsersId(users.getId()).stream()
                .map(adMapper::adDtoToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAd(long id) {
        userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        adRepository.deleteById(id);
    }

    @Override
    public void updatePhotoAd(Long id, MultipartFile imageFile,
                              Authentication authentication) throws Exception {
        Users users = userRepository.findByUserName(authentication.getName()).orElseThrow();
        Ad ad = adRepository.findById(id).orElseThrow();
        if (securityCheck.checkRole(users) || securityCheck.checkAuthorAd(users, ad)) {
            Image image = imageRepository.findById(ad.getImage().getId()).orElseThrow();
            image.setData(imageFile.getBytes());
            image.setFileSize(imageFile.getSize());
            imageRepository.save(image);
        }
    }


    //Comments
    @Override
    public Collection<CommentDTO> getCommentsForAd(long id) {

        return commentRepository.getAllCommentsByAdId(id).stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CreateOrUpdateCommentDTO createOrUpdateComment(CreateOrUpdateCommentDTO createOrUpdateCommentDTO,
                                                          long adId, long commentId) {
        userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        adRepository.findById(adId).orElseThrow();

        Comment comment = createOrUpdateCommentMapper.toEntity(createOrUpdateCommentDTO);
        if (commentRepository.findById(commentId).isEmpty()) {
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
        userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        commentRepository.deleteCommentByIdAndByCommentId(adId, commentId);
    }

}
