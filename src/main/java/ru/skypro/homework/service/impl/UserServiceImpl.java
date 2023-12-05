package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.NewPasswordMapper;
import ru.skypro.homework.mapper.UpdateUserMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repostitory.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Data
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private UserMapper userMapper;
    private UpdateUserMapper updateUserMapper;
    private NewPasswordMapper newPasswordMapper;

    public User findUser(Authentication authentication) {
        return repository.findByUserName(authentication.getName()).orElseThrow();
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO, Authentication authentication) {
        User user = findUser(authentication);
        updateUserMapper.toEntity(updateUserDTO);
        repository.save(user);
        return updateUserMapper.toDTO(user);
    }

    @Override
    public NewPasswordDTO setPassword(NewPasswordDTO newPasswordDto, Authentication authentication) throws Exception {
        User user = findUser(authentication);
        String currentPassword = user.getPassword();
        if (encoder.matches(newPasswordDto.getCurrentPassword(), currentPassword)) {
            user.setPassword(encoder.encode(newPasswordDto.getNewPassword()));
            repository.save(user);
            newPasswordMapper.toDTO(user);
            return newPasswordDto;
        } else {
            throw new Exception("ошибка изменения пароля");
        }
    }

    @Override
    public UserDTO getUserInfo(Authentication authentication) {
        User user = findUser(authentication);
        return userMapper.toDTO(user);

    }

    @Override
    public void setPhoto(MultipartFile image, Authentication authentication, String userName) {
        User user = findUser(authentication);
        String dir = System.getProperty("user.dir") + "/" + "file.path.avatar";
        try {
            Files.createDirectories(Path.of(dir));
            String fileName = String.format("avatar%s.%s", user.getEmail(),
                    StringUtils.getFilenameExtension(image.getOriginalFilename()));
            image.transferTo(new File(dir + "/" + fileName));
            user.setImage("/users/get/" + fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        repository.save(user);
    }
    }



