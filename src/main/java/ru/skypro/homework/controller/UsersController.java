package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.style.ToStringStyler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/user")
public class UsersController {
    private UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDTO> setPassword(@RequestBody NewPasswordDTO newPasswordDto, Authentication authentication) throws Exception {
        return ResponseEntity.ok(userService.setPassword(newPasswordDto, authentication));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserInfo(authentication));
    }

    @PatchMapping("/update")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDto, Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(updateUserDto, authentication));
    }

    @PatchMapping("/me/image")
    public ResponseEntity<String> updateUserImage(MultipartFile image, Authentication authentication) {
        return ResponseEntity.ok(userService.setPhoto(image, authentication));
    }





}
