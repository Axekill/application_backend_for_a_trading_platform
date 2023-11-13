package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.AdsService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private AdsService adsService;

    @PostMapping
    public ResponseEntity<?> add(Ads ads) {
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
                                       @RequestParam String description){
        return null;
    }
    @GetMapping("/me")
    public ResponseEntity<?> getMeAds(){
        return null;
    }
    @PatchMapping("{id}/image")
    public ResponseEntity<?> updateImage(@PathVariable Long id,
                                         @RequestParam String image){
        return null;
    }

}
