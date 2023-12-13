package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUsersDTO;
import ru.skypro.homework.dto.UsersDTO;

import java.io.IOException;

@Service
public interface UsersService {


    UpdateUsersDTO updateUser(UpdateUsersDTO updateUsersDTO, Authentication authentication);

    void setPassword(NewPasswordDTO newPasswordDto, Authentication authentication) throws Exception;

    UsersDTO getUserInfo(Authentication authentication);

    void setPhoto(MultipartFile image, Authentication authentication) throws IOException;
}
