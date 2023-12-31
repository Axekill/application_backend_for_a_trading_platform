package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.UpdateUsersMapper;
import ru.skypro.homework.mapper.UsersMapper;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repostitory.ImageRepository;
import ru.skypro.homework.repostitory.UsersRepository;
import ru.skypro.homework.security.SecurityCheck;
import ru.skypro.homework.security.SecurityUserService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UsersService;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    @Autowired
    private final SecurityCheck securityCheck;
    private final SecurityUserService securityUserService;
    private final UsersRepository repository;
    private final PasswordEncoder encoder;
    private UsersMapper usersMapper;
    private UpdateUsersMapper updateUsersMapper;
    private ImageService imageService;
    private ImageRepository imageRepository;

    @Override
    public UsersListDTO getAllUsers() {
        List<Users> usersList = (List<Users>) repository.findAll();
        return usersMapper.toUsersListDTO(usersList.size(), usersList);
    }

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
        Image avatar = imageRepository.findByUsersId(users.getId()).orElse(new Image());
        try {
            avatar.setData(image.getBytes());
        } catch (IOException e) {
            log.error("File '{}' has some problems and cannot be read.", image.getOriginalFilename());
            throw new RuntimeException("Problems with uploaded image");
        }
        avatar.setUsers(users);
        imageRepository.save(avatar);
    }

    @Override
    public void checkPermission(Authentication authentication, String email) {
        boolean user = authentication.getName().equals(email);
        boolean userIsAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().contains(Role.ADMIN.name()));
        if (!(userIsAdmin || user)) {
            log.warn("текущий пользователь не имеет прав на выполнение данной операции");
            throw new RuntimeException("текущий пользователь не имеет прав на выполнение данной операции");
        }
    }

}



