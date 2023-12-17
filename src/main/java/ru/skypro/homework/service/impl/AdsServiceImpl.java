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
   // private final CreateOrUpdateAdMapper createOrUpdateAdMapper;
    private final CreateOrUpdateCommentMapper createOrUpdateCommentMapper;
    private final ImageRepository imageRepository;
    private final SecurityCheck securityCheck;
    private final CommentMapper commentMapper;
    private final ImageService imageService;
 //   private final ExtendedAdMapper extendedAdMapper;

    //Ads
    @Override
    @Transactional
    public AdDTO createAd(CreateOrUpdateAdDTO createOrUpdateAdDTO,
                          MultipartFile image,
                          Authentication authentication) throws IOException {
        Users currentUser = usersRepository.findByEmail(authentication.getName()).orElseThrow();
        Ad ad = adMapper.toEntity(createOrUpdateAdDTO);
        ad.setUsers(currentUser);
        Ad saveAd = adRepository.save(ad);

        Image imageAd = imageService.createImage(image, saveAd);
        /*try {
            imageAd = new Image();
            imageAd.setData(image.getBytes());
            imageAd.setFileSize(image.getSize());
            imageRepository.save(imageAd);
        } catch (IOException e) {
            log.error("ошибка при загрузки картинки", e);
        }*/
        saveAd.setImage(imageAd);
        return adMapper.toAdDTO(adRepository.save(saveAd));
    }

    @Override
    public AdDTO updateAd(long id, CreateOrUpdateAdDTO updateAdDTO,
                          Authentication authentication) {
        Ad ad = adRepository.findById(id).orElseThrow();
        Users user = usersRepository.findByEmail(authentication.getName()).orElseThrow();
        Ad updateAd = adMapper.toEntity(updateAdDTO);
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
        List<Ad> usersAdlist=adRepository.findAllAdByUsersEmail(email);
       /* return adRepository.findAllAdByUsersId(users.getId()).stream()
                .map(adMapper::toAdDTO)
                .collect(Collectors.toList());*/
        return adMapper.adsListToAdsDTO(usersAdlist.size(),usersAdlist);
    }

    @Override
    public void deleteAd(long id, Authentication authentication) {
        Users user = usersRepository.findByEmail(authentication.getName()).orElseThrow();
        Ad ad = adRepository.findById(id).orElseThrow();
        if ((securityCheck.checkRole(user) || securityCheck.checkAuthorAd(user, ad))) {
            commentRepository.deleteById(id);
            if (ad.getImage() != null) {
                imageRepository.deleteById(ad.getImage().getId());
            }
        }
        adRepository.deleteById(id);
    }

    @Override
    public void updatePhotoAd(Long id, MultipartFile imageFile,
                              Authentication authentication) throws Exception {
        Users users = usersRepository.findByEmail(authentication.getName()).orElseThrow();
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
        Users user = usersRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        Ad ad = adRepository.findById(id).orElseThrow();
        log.info("вызман метод создания коментария к объявлению");
        Comment comment = createOrUpdateCommentMapper.toEntity(createOrUpdateCommentDTO);
        Comment newComment = new Comment();
        newComment.setAd(ad);
        newComment.setUsers(user);
        newComment.setTextComment(createOrUpdateCommentDTO.getText());
        newComment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(newComment);
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
    public void deleteComment(long adId, long commentId) {
        usersRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        commentRepository.deleteCommentByIdAndByCommentId(adId, commentId);
    }

}
