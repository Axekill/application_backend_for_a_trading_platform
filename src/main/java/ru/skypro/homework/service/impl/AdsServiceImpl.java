package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service

@Data
public class AdsServiceImpl implements AdsService {

    @Autowired
    private AdRepository adRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;
    private AdMapper adMapper;
    private CreateOrUpdateAdMapper createOrUpdateAdMapper;
    private CreateOrUpdateCommentMapper createOrUpdateCommentMapper;
    private final UserServiceImpl userServiceImpl;

    @Value("${file.path.image}")
    private String filePath;




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
    public void updatePhotoAd(Long id, Authentication authentication, MultipartFile image, String userName) {
        User user = userServiceImpl.findUser(authentication);
        Ad ad = adRepository.findById(id).orElseThrow();
        if (ad == null) {

            throw new IllegalArgumentException();
        }
        if (ad.getImage() != null) {
            try {
                Files.delete(Path.of(System.getProperty("user.dir") + "/" + filePath + ad.getImage().replaceAll("/ads/get", "")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String imageName = uploadImage(image, userName);
        ad.setImage(getUrlImage(imageName));
        ad.setUser(user);
        adRepository.save(ad);

    }


    //Comments
    @Override
    public Collection<CommentsDTO> getCommentsForAd(long id) {
        return adRepository.getAllCommentsByAdId(id);
    }

    @Override
    public CreateOrUpdateCommentDTO createComment(CreateOrUpdateCommentDTO createOrUpdateCommentDTO, long adId, long commentId ) {
        adRepository.findById(adId).orElseThrow();
        Comment comment = createOrUpdateCommentMapper.toEntity(createOrUpdateCommentDTO);
        if (commentRepository.findById(commentId) == null) {
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


    private String uploadImage(MultipartFile image, String userName) {
        String dir = System.getProperty("user.dir") + "/" + filePath;
        String imageName = nameFile(userName, image);
        try {
            Files.createDirectories(Path.of(dir));
            image.transferTo(new File(dir + "/" + imageName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageName;
    }

    private String nameFile(String userName, MultipartFile image) {
        return String.format("image%s_%s.%s", userName, UUID.randomUUID(), extension(image.getOriginalFilename()));
    }

    private String getUrlImage(String fileName) {
        return "/ads/get/" + fileName;
    }

    private String extension(String fileName) {
        return StringUtils.getFilenameExtension(fileName);
    }

}


