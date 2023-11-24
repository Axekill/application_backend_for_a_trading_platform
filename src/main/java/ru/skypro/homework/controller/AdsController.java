package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdsService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private AdsService adsService;

    @PostMapping

    public ResponseEntity<?> add(Ad ad) {
        return null;
    }

    @GetMapping
    public ResponseEntity<?> getAds() {
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getByIdAds(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAds(@PathVariable Long id) {
        return null;
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateAds(@PathVariable Long id,
                                       @RequestParam String title,
                                       @RequestParam int price,
                                       @RequestParam String description) {
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMeAds() {
        return null;
    }

    @PatchMapping("{id}/image")
    public ResponseEntity<?> updateImage(@PathVariable Long id,
                                         @RequestParam String image) {
        return null;
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComment(@PathVariable Long id,
                                        @RequestParam String textComment) {
        return null;
    }

    @DeleteMapping("{adId}/comments/{commentsId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long adId,
                                           @PathVariable Long commentId) {
        return null;
    }

    @PatchMapping("{adId}/comments/{commentsId}")
    public ResponseEntity<?> updateComment(@PathVariable Long adId,
                                           @PathVariable Long commentId,
                                           @RequestParam String textComment) {
        return null;
    }

}
