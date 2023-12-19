package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.*;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repostitory.AdRepository;
import ru.skypro.homework.repostitory.CommentRepository;
import ru.skypro.homework.repostitory.ImageRepository;
import ru.skypro.homework.repostitory.UsersRepository;
import ru.skypro.homework.security.SecurityCheck;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UsersService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class AdsServiceImpl implements AdsService {
    @Autowired
    private final AdRepository adRepository;
    private final UsersRepository usersRepository;
    private final CommentRepository commentRepository;
    private final AdMapper adMapper;
    private final CreateOrUpdateCommentMapper createOrUpdateCommentMapper;
    private final ImageRepository imageRepository;
    private final SecurityCheck securityCheck;
    private final CommentMapper commentMapper;
    private final ImageService imageService;
    private final UsersService usersService;


    //Ads
    @Override
    @Transactional
    public AdDTO createAd(CreateOrUpdateAdDTO createOrUpdateAdDTO,
                          MultipartFile image,
                          Authentication authentication) throws IOException {
        Users currentUser = usersRepository.findByEmail(authentication.getName()).orElseThrow();
        Ad ad = adMapper.CreateOrUpdateAdDTOToAd(createOrUpdateAdDTO);
        ad.setUsers(currentUser);
        Ad saveAd = adRepository.save(ad);
        Image imageAd = imageService.createImage(image, saveAd);
        saveAd.setImage(imageAd);
        return adMapper.toAdDTO(saveAd);
    }

    @Override
    public AdDTO updateAd(long id, CreateOrUpdateAdDTO updateAdDTO,
                          Authentication authentication) {
        Ad ad = adRepository.findById(id).orElseThrow();
        Users user = usersRepository.findByEmail(authentication.getName()).orElseThrow();
        Ad updateAd = adMapper.CreateOrUpdateAdDTOToAd(updateAdDTO);
        if (securityCheck.checkRole(user) || securityCheck.checkAuthorAd(user, ad)) {
            ad.setTitle(ad.getTitle());
            ad.setPrice(ad.getPrice());
            ad.setDescription(ad.getDescription());
            adRepository.save(ad);
        }
        return adMapper.toAdDTO(ad);
    }

    @Override
    public AdsDTO getAllAds() {
        List<Ad> adList = (List<Ad>) adRepository.findAll();
        return adMapper.adsListToAdsDTO(adList.size(), adList);
       /* List<AdDTO> result = new ArrayList<>();
        var find = adRepository.findAll();
        for (Ad ad : find) {
            result.add(adMapper.toAdDTO(ad));
        }
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setResult(result);
        adsDTO.setCount(result.size());

        return adsDTO;*/
    }

    @Override
    public ExtendedAdDTO findByIdAd(long id) {
        Ad ad = adRepository.findById(id).orElseThrow();
        return adMapper.AdToExtendedDTO(ad);
    }

    @Override
    public AdsDTO getAdInfoAuthorizedUser(String email) {
        List<Ad> usersAdlist = adRepository.findAllAdByUsersEmail(email);
        return adMapper.adsListToAdsDTO(usersAdlist.size(), usersAdlist);
    }

    @Override
    public void deleteAd(long id, Authentication authentication) {
        Ad ad = adRepository.findById(id).orElseThrow();
        usersService.checkPermission(authentication, ad.getUsers().getEmail());
        adRepository.delete(ad);
    }

    @Override
    public byte[] updatePhotoAd(Long id, MultipartFile imageFile,
                                Authentication authentication) throws IOException {
        Ad ad = adRepository.findById(id).orElseThrow();
        Image image = imageRepository.findByAdId(ad.getId()).orElseThrow();
        usersService.checkPermission(authentication, image.getAd().getUsers().getEmail());
        image.setData(imageFile.getBytes());
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        image.setAd(ad);
        return imageRepository.save(image).getData();

    }


    //Comments
    @Override
    public CommentsDTO getCommentsForAd(long id) {
        Ad adById = adRepository.findById(id).orElseThrow();
        List<Comment> comments = adById.getComments();
        return commentMapper.commentsListToComments(comments.size(), comments);

       /* return commentRepository.getAllCommentsByAdId(id).stream()
                .map((Comment comment) -> commentMapper.toDTO(comment, comment.getUsers()))
                .collect(Collectors.toList());*/
    }

    @Override
    public CommentDTO createComment(CreateOrUpdateCommentDTO createOrUpdateCommentDTO,
                                    long id, Authentication authentication) {
        Users user = usersRepository.findByEmail(authentication.getName()).orElseThrow();
        Ad ad = adRepository.findById(id).orElseThrow();
        log.info("вызман метод создания коментария к объявлению");
        Comment comment = createOrUpdateCommentMapper.toEntity(createOrUpdateCommentDTO);
        comment.setAd(ad);
        comment.setUsers(user);
        comment.setTextComment(createOrUpdateCommentDTO.getText());
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        return commentMapper.toDTO(comment, user);
    }

    @Override
    public CreateOrUpdateCommentDTO updateComment(CreateOrUpdateCommentDTO updateCommentDTO, long adId,
                                                  long commentId, Authentication authentication) {
        Comment comment = commentRepository.findByAdIdAndId(adId, commentId).orElseThrow();
        Users user = usersRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        if (securityCheck.checkRole(user) || securityCheck.checkAuthorComment(user, comment)) {
            comment.setTextComment(updateCommentDTO.getText());
        } else {
            throw new RuntimeException();
        }
        return createOrUpdateCommentMapper.toDTO(comment);
    }

    @Override
    public void deleteComment(long adId, long commentId, Authentication authentication) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        usersService.checkPermission(authentication, comment.getUsers().getEmail());
        commentRepository.delete(comment);
    }

}
