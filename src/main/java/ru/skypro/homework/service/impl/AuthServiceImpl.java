package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.mapper.RegisterMapper;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.security.SecurityUserService;
import ru.skypro.homework.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Autowired
    private final PasswordEncoder encoder;
    private final SecurityUserService manager;
    private final RegisterMapper mapper;


    @Override
    public boolean login(String username, String password) {
        if (!manager.userExists(username)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(username);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDTO register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        Users users = mapper.toEntity(register);
        users.setPassword(encoder.encode(register.getPassword()));
        manager.createUser(users);
        return true;
    }

}
