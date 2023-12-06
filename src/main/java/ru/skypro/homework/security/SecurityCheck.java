package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repostitory.AdRepository;
import ru.skypro.homework.repostitory.CommentRepository;
import ru.skypro.homework.repostitory.UserRepository;

import java.util.Optional;

@Component
@Log
@RequiredArgsConstructor
public class SecurityCheck {
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;


    /**
     * Проверка ролей
     */

    public boolean checkRole(User user) {
        log.info("проверка роли");
        return user.getRole().equals(Role.ADMIN);
    }


    /**
     * Проверка автора объявления
     */
    public boolean checkAuthorAd(User user, Ad ad) {
        log.info("проверка автора");
        return ad.getUser().equals(user);
    }

    /**
     * Проверка автора коментария
     */
    public boolean checkAuthorComment(User user, Comment comment) {
        log.info("проверка автора коментария");
        return comment.getUser().equals(user);
    }

    /**
     * Проверка на наличие объявления
     */
    public Ad chekedAd(long id) {
        Optional<Ad> ad = adRepository.findById(id);
        if (ad.isEmpty()) {
            log.info("объявление не существует");
            throw new NullPointerException();
        } else {
            return ad.get();
        }
    }


    /**
     * Прорверка  на наличие комментария
     */
    public Comment checkedComment(long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            log.info("Комментарий не существует");
            throw new NullPointerException();
        } else {
            return comment.get();
        }
    }

    /**
     * проверка авторизирован ли пользователь
     */
    public User checkedUser(Authentication authentication) {
        Optional<User> user = userRepository.findByUserName(authentication.getName());
        if (user.isEmpty()) {
            log.info("пользователь не авторизирован");
            throw new NullPointerException();
        } else {
            return user.get();
        }
    }
}
