package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repostitory.AdRepository;
import ru.skypro.homework.repostitory.CommentRepository;
import ru.skypro.homework.repostitory.UsersRepository;

import java.util.Optional;

@Component
@Log
@RequiredArgsConstructor
public class SecurityCheck {
    private final AdRepository adRepository;
    private final UsersRepository usersRepository;
    private final CommentRepository commentRepository;


    /**
     * Проверка ролей
     */

    public boolean checkRole(Users users) {
        log.info("проверка роли");
        return users.getRole().equals(Role.ADMIN);
    }


    /**
     * Проверка автора объявления
     */
    public boolean checkAuthorAd(Users users, Ad ad) {
        log.info("проверка автора");
        return ad.getUsers().equals(users);
    }

    /**
     * Проверка автора коментария
     */
    public boolean checkAuthorComment(Users users, Comment comment) {
        log.info("проверка автора коментария");
        return comment.getUsers().equals(users);
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
    public Users checkedUser(Authentication authentication) {
        Optional<Users> user = usersRepository.findByEmail(authentication.getName());
        if (user.isEmpty()) {
            log.info("пользователь не авторизирован");
            throw new NullPointerException();
        } else {
            return user.get();
        }
    }
}
