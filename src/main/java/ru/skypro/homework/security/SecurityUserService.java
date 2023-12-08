package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.mapper.RegisterMapper;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repostitory.UserRepository;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

    private final UserRepository repository;
    private final RegisterMapper mapper;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = repository.findByEmail(email).orElseThrow();
        return new UserSecurity(mapper.toDTO(users));
    }

    public boolean userExists(String user) {
        return repository.findByEmail(user).isPresent();
    }

    public void createUser(Users users) {
        repository.save(users);
    }

}
