package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.style.ToStringStyler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/user")
public class UsersController {
    private UserService userService;
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
    public ResponseEntity setPassword(@RequestBody NewPasswordDTO newPasswordDto,
                                      Authentication authentication) throws Exception {
        userService.setPassword(newPasswordDto, authentication);
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
    public ResponseEntity<UserDTO> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserInfo(authentication));
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
    @PatchMapping("/update")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDto, Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(updateUserDto, authentication));
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
    public ResponseEntity<UserDTO> updateUserImage(@RequestPart("image") MultipartFile image,
                                                   Authentication authentication) throws IOException {

        userService.setPhoto(image, authentication);
        return ResponseEntity.ok().build();
    }


}
