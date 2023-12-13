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
import ru.skypro.homework.dto.UpdateUsersDTO;
import ru.skypro.homework.dto.UsersDTO;
import ru.skypro.homework.mapper.UpdateUsersMapper;
import ru.skypro.homework.mapper.UsersMapper;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repostitory.UsersRepository;
import ru.skypro.homework.security.SecurityCheck;
import ru.skypro.homework.security.SecurityUserService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UsersService;

import java.io.IOException;

@Service
@AllArgsConstructor
@Log
public class UsersServiceImpl implements UsersService {

    @Autowired
    private final SecurityCheck securityCheck;
    private final SecurityUserService securityUserService;
    private final UsersRepository repository;
    private final PasswordEncoder encoder;
    private UsersMapper usersMapper;
    private UpdateUsersMapper updateUsersMapper;
    private ImageService imageService;


    @Override
    public UpdateUsersDTO updateUser(UpdateUsersDTO updateUsersDTO, Authentication authentication) {
        log.info("Изменение данных авторизированного пользователя");
        Users users = repository.findByEmail(authentication.getName()).orElseThrow();
        updateUsersMapper.toEntity(updateUsersDTO);
        users.setFirstName(updateUsersDTO.getFirstName());
        users.setLastName(updateUsersDTO.getLastName());
        users.setPhone(updateUsersDTO.getPhone());
        repository.save(users);
        return updateUsersMapper.toDTO(users);
    }

    @Override
    public void setPassword(NewPasswordDTO newPasswordDto, Authentication authentication) throws Exception {
        log.info("создайте новый пароль");
        Users users = repository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        if (encoder.matches(newPasswordDto.getCurrentPassword(), users.getPassword())) {
            users.setPassword(encoder.encode(newPasswordDto.getNewPassword()));
            repository.save(users);
            log.info("Пароль обновлен!");
            securityUserService.loadUserByUsername(users.getEmail());
        }

    }

    @Override
    public UsersDTO getUserInfo(Authentication authentication) {
        Users users = repository.findByEmail(authentication.getName()).orElseThrow();
        return usersMapper.toDTO(users);

    }

    @Override
    public void setPhoto(MultipartFile image, Authentication authentication) throws IOException {
        Users users = securityCheck.checkedUser(authentication);
        users.setImage(imageService.uploadImage(image));
        repository.save(users);
    }


}
