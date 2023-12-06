package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.NewPasswordMapper;
import ru.skypro.homework.mapper.UpdateUserMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repostitory.UserRepository;
import ru.skypro.homework.security.SecurityCheck;
import ru.skypro.homework.security.SecurityUserService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
@AllArgsConstructor
@Log
public class UserServiceImpl implements UserService {

    @Autowired
    private final SecurityCheck securityCheck;
    private final SecurityUserService securityUserService;
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private UserMapper userMapper;
    private UpdateUserMapper updateUserMapper;
    private NewPasswordMapper newPasswordMapper;
    private ImageService imageService;

    public User findUser(Authentication authentication) {
        return repository.findByUserName(authentication.getName()).orElseThrow();
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO, Authentication authentication) {
        log.info("Изменение данных авторизированного пользователя");
        User user = findUser(authentication);
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        user.setPhone(updateUserDTO.getPhone());
        return updateUserMapper.toDTO(repository.save(user));
    }

    @Override
    public void setPassword(NewPasswordDTO newPasswordDto, Authentication authentication) throws Exception {
        log.info("создайте новый пароль");
        User user = repository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        if (encoder.matches(newPasswordDto.getCurrentPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(newPasswordDto.getNewPassword()));
            repository.save(user);
            log.info("Пароль обновлен!");
            securityUserService.loadUserByUsername(user.getUserName());
        }

    }

    @Override
    public UserDTO getUserInfo(Authentication authentication) {
        User user = findUser(authentication);
        return userMapper.toDTO(user);

    }

    @Override
    public void setPhoto(MultipartFile image, Authentication authentication) throws IOException {
        User user = securityCheck.checkedUser(authentication);
        user.setImage(imageService.uploadImage(image));
        repository.save(user);
    }


}
