package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdsService;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private AdsService adsService;

    //создаем или обновляем объявление
    @PostMapping
    @PatchMapping("{id}")
    public ResponseEntity<CreateOrUpdateAdDTO> createOrUpdateAd(@PathVariable Long id,
                                                                @RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                                                @RequestBody AdDTO adDTO) {
        return ResponseEntity.ok(adsService.createOrUpdateAd(createOrUpdateAdDTO, adDTO, id));
    }

    // получаем все объявления
    @GetMapping
    public ResponseEntity<AdsDTO> getAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    // найти объявление по id
    @GetMapping("{id}")
    public ResponseEntity<AdDTO> getByIdAd(@PathVariable Long id) {
        AdDTO adDTO = adsService.findByIdAd(id);
        if (adDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adDTO);
    }

    // удалить объявление
    @DeleteMapping("{id}")
    public ResponseEntity deleteAd(@PathVariable Long id) {
        adsService.deleteAd(id);
        return ResponseEntity.ok().build();
    }

    // получить все объявления авторезированного пользователя
    @GetMapping("/me")
    public ResponseEntity<AdsDTO> getAdAuthorizedUser(Authentication authentication) {
        List<AdDTO> adDTOList = adsService.getAdInfoAuthorizedUser(authentication);
        return ResponseEntity.ok((AdsDTO) adDTOList);
    }

    // обновить картинрку объявления
    @PatchMapping("{id}/image")
    public ResponseEntity<?> updateImage(@PathVariable Long id,
                                         @RequestParam String image) {
        return null;
    }

    //Коментарии

    //получаем все коментарии объявления
    @GetMapping("/{id}/comments")
    public ResponseEntity<Collection<CommentsDTO>> getComments(@PathVariable Long id) {

        return ResponseEntity.ok(adsService.getCommentsForAd(id));
    }

    //создать или изменить коментарий
    @PostMapping("/{adId}/comments")
    @PatchMapping("{adId}/comments/{commentsId}")
    public ResponseEntity<CreateOrUpdateCommentDTO> addComment(@PathVariable Long adId,
                                                               @PathVariable Long commentId,
                                                               @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO
    ) {
        return ResponseEntity.ok(adsService.createComment(createOrUpdateCommentDTO, adId, commentId));
    }

    //удаление коментария из объявления
    @DeleteMapping("{adId}/comments/{commentsId}")
    public ResponseEntity deleteComment(@PathVariable Long adId,
                                        @PathVariable Long commentId) {
        adsService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }


}
