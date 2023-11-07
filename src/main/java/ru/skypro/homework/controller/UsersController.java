package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.style.ToStringStyler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UsersController {
    private UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestParam ToStringStyler currentPassword,
                                         @RequestParam ToStringStyler newPassword) {
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUser(@RequestBody UserDTO userDTO) {
        return null;
    }

    @PatchMapping("/me")
    public ResponseEntity<?> updateUser(@RequestParam String firstName,
                                        @RequestParam String lastName,
                                        @RequestParam int phone) {
        return null;
    }

    @PatchMapping("/me/image")
    public ResponseEntity<?> updatePhoto(@RequestBody String image) {
        return null;
    }

}
