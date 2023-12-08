package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.repostitory.UserRepository;
import ru.skypro.homework.security.SecurityUserService;
import ru.skypro.homework.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Autowired
    private final PasswordEncoder encoder;
    private final SecurityUserService manager;
    private final UserRepository repository;


    @Override
    public boolean login(String username, String password) {
        if (!manager.userExists(username)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(username);
        return encoder.matches(password, userDetails.getPassword());
      /*  return repository.findByEmail(username)
                .map(users -> encoder.matches(password, users.getPassword()))
                .orElse(false);*/
    }

    @Override
    public boolean register(RegisterDTO register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        manager.createUser(register);
        return true;
    }

}
