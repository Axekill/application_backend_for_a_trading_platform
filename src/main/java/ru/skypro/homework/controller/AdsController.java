package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @Operation(
            summary = "Обновление информации об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = AdsDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = " Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            }
    )
    //создаем или обновляем объявление
    @PostMapping
    @PatchMapping("{id}")
    public ResponseEntity<CreateOrUpdateAdDTO> createOrUpdateAd(@PathVariable Long id,
                                                                @RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                                                @RequestBody AdDTO adDTO) {
        return ResponseEntity.ok(adsService.createOrUpdateAd(createOrUpdateAdDTO, adDTO, id));
    }

    @Operation(
            summary = "Получение всех объявлений",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = AdsDTO.class)
                                    )
                            }
                    )
            }
    )
    @GetMapping
    public ResponseEntity<AdsDTO> getAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    // найти объявление по id
    @Operation(
            summary = "Получение информации об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ExtendedAdDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = " Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<AdDTO> getByIdAd(@PathVariable Long id) {
        AdDTO adDTO = adsService.findByIdAd(id);
        if (adDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adDTO);
    }
    @Operation(
            summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = " Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            }
    )
    // удалить объявление
    @DeleteMapping("{id}")
    public ResponseEntity deleteAd(@PathVariable Long id) {
        adsService.deleteAd(id);
        return ResponseEntity.ok().build();
    }

    // получить все объявления авторезированного пользователя
    @Operation(
            summary = "получить все объявления авторезированного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = AdsDTO.class)
                                    )
                            }
                    )
            }
    )
    @GetMapping("/me")
    public ResponseEntity<AdsDTO> getAdAuthorizedUser(Authentication authentication) {
        List<AdDTO> adDTOList = adsService.getAdInfoAuthorizedUser(authentication);
        return ResponseEntity.ok((AdsDTO) adDTOList);
    }

    // обновить картинрку объявления
    @Operation(
            summary = "обновить картинрку объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = {
                                    @Content(
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = {
                                    @Content(
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = {
                                    @Content(
                                    )
                            }
                    )
            }
    )
    @PatchMapping("{id}/image")
    public ResponseEntity<String> updateImage(@PathVariable Long id,
                                              Authentication authentication,
                                              @RequestParam MultipartFile image) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        adsService.updatePhotoAd(id, authentication, image, userName );
        return ResponseEntity.ok().build();
    }


    //Коментарии

    @Operation(
            summary = "Получение всех комментариев объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = CommentsDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = " Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            }
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<Collection<CommentsDTO>> getComments(@PathVariable Long id) {

        return ResponseEntity.ok(adsService.getCommentsForAd(id));
    }

    @Operation(
            summary = "Добавление комментария ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = CommentDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = " Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            }
    )

    @PostMapping("/{adId}/comments")
    @PatchMapping("{adId}/comments/{commentsId}")
    public ResponseEntity<CreateOrUpdateCommentDTO> addComment(@PathVariable Long adId,
                                                               @PathVariable Long commentId,
                                                               @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO,
                                                               @PathVariable String commentsId) {
        return ResponseEntity.ok(adsService.createComment(createOrUpdateCommentDTO, adId, commentId));
    }

    @Operation(
            summary = "Удаление комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = " Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            }
    )
    @DeleteMapping("{adId}/comments/{commentsId}")
    public ResponseEntity deleteComment(@PathVariable Long adId,
                                        @PathVariable Long commentId) {
        adsService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }


}
