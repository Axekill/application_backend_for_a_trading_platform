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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    private final ImageService imageService;


    //создаем объявление
    @Operation(
            summary = "Добавление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = AdDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = " Unauthorized"
                    )
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> createAd(@RequestPart("properties") CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                          @RequestPart("image") @Valid MultipartFile image) {
        return ResponseEntity.ok(adsService.createAd(createOrUpdateAdDTO, image));
    }

    // update Ad
    @Operation(
            summary = "Обновление информации об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = AdDTO.class)
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
    @PatchMapping("/{id}")
    public ResponseEntity<AdDTO> updateAd(@PathVariable long id,
                                          @RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                          Authentication authentication) {
        return ResponseEntity.ok(adsService.updateAd(id, createOrUpdateAdDTO, authentication));
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
    public ResponseEntity<?> deleteAd(@PathVariable Long id, Authentication authentication) {
        adsService.deleteAd(id, authentication);
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
    public ResponseEntity<List<AdDTO>> getAdAuthorizedUser(Authentication authentication) {
        List<AdDTO> ads = adsService.getAdInfoAuthorizedUser(authentication);
        return ResponseEntity.ok(ads);
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
    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable Long id,
                                              Authentication authentication,
                                              @RequestParam MultipartFile image) throws Exception {
        adsService.updatePhotoAd(id, image, authentication);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "getAdsImage", description = "Запрос на получение картинки объявления",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    @GetMapping(value = "/image/{imageId}")
    public ResponseEntity<byte[]> getAdsImage(@PathVariable Long imageId) throws IOException {
        byte[] image = imageService.getImage(imageId);
        return ResponseEntity.ok(image);
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
    public ResponseEntity<Collection<CommentDTO>> getComments(@PathVariable Long id) {

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
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long adId,
                                                 @RequestBody @Valid CreateOrUpdateCommentDTO createOrUpdateCommentDTO,
                                                 Authentication authentication) {
        return ResponseEntity.ok(adsService.createComment(createOrUpdateCommentDTO, adId, authentication));
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CreateOrUpdateCommentDTO> updateComment(@PathVariable long adId,
                                                                  @PathVariable long commentId,
                                                                  @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO,
                                                                  Authentication authentication) {
        return ResponseEntity.ok(adsService.updateComment(createOrUpdateCommentDTO, adId, commentId, authentication));
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
