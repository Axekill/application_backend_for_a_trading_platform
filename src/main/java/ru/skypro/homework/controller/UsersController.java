package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUsersDTO;
import ru.skypro.homework.dto.UsersDTO;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UsersService;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final ImageService imageService;

    @Operation(
            summary = "Смена пароля",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            }
    )
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPasswordDTO newPasswordDto,
                                      Authentication authentication) throws Exception {
        usersService.setPassword(newPasswordDto, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Получение информации о пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
            }
    )
    @GetMapping("/me")
    public ResponseEntity<UsersDTO> getUser(Authentication authentication) {
        return ResponseEntity.ok(usersService.getUserInfo(authentication));
    }

    @Operation(
            summary = "Обновление данных пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
            }
    )
    @PatchMapping("/me")
    public ResponseEntity<UpdateUsersDTO> updateUser(@RequestBody UpdateUsersDTO updateUsersDto, Authentication authentication) {
        return ResponseEntity.ok(usersService.updateUser(updateUsersDto, authentication));
    }

    @Operation(
            summary = "Обновление аватара пользовтеля",
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
                            description = "Unauthorized"
                    ),
            }
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UsersDTO> updateUserImage(@RequestPart("image") MultipartFile image,
                                                    Authentication authentication) throws IOException {

        usersService.setPhoto(image, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/image/{id}")
    @Operation(summary = "Получение изображения пользователя", responses = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.getImage(id));
    }

}
