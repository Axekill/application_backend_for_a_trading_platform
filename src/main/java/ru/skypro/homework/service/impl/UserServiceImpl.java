package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
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
import ru.skypro.homework.service.UserService;
@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;



    public User findUser(Authentication authentication){
        return  repository.findByUserName(authentication.getName()).orElseThrow();
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO, Authentication authentication) {
        User user = findUser(authentication);
        UpdateUserMapper.INSTANCE.toEntity(updateUserDTO);
        repository.save(user);
        return UpdateUserMapper.INSTANCE.toDTO(user);
    }

    @Override
    public NewPasswordDTO setPassword(NewPasswordDTO newPasswordDto, Authentication authentication) throws Exception {
        User user = findUser(authentication);
        String currentPassword = user.getPassword();
        if (encoder.matches(newPasswordDto.getCurrentPassword(), currentPassword)) {
            user.setPassword(encoder.encode(newPasswordDto.getNewPassword()));
            repository.save(user);
            NewPasswordMapper.INSTANCE.toDTO(user);
            return newPasswordDto;
        }
        else{
            throw new Exception("ошибка изменения пароля");
        }
    }

    @Override
    public UserDTO getUserInfo(Authentication authentication) {
        User user = findUser(authentication);
        return UserMapper.INSTANCE.toDTO(user);

    }

    @Override
    public void setPhoto(MultipartFile image, Authentication authentication) {
        User user = findUser(authentication);
        user.setImage(image.getName());

    }


}
