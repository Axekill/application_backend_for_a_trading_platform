package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.CommentService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class CommentsController {
    private CommentService commentService;

    @GetMapping("/ads/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/ads/{id}/comments")
    public ResponseEntity<?> addComment(@PathVariable Long id,
                                        @RequestParam String textComment) {
        return null;
    }

    @DeleteMapping("/ads/{adId}/comments/{commentsId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long adId,
                                           @PathVariable Long commentId) {
        return null;
    }
    @PatchMapping("/ads/{adId}/comments/{commentsId}")
    public ResponseEntity<?> updateComment(@PathVariable Long adId,
                                           @PathVariable Long commentId,
                                           @RequestParam String textComment) {
        return null;
    }


}
