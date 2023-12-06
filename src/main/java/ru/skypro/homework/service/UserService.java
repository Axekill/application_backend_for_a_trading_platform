package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
@Service
public interface UserService {


    UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO, Authentication authentication);

    void setPassword(NewPasswordDTO newPasswordDto, Authentication authentication) throws Exception;

    UserDTO getUserInfo(Authentication authentication);

    void setPhoto(MultipartFile image, Authentication authentication,String userName);
}
