package ru.skypro.homework.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.dto.Role;

import java.util.*;

/**
 *  реализация интерфейса UserDetails
 */

@RequiredArgsConstructor
public class UserSecurity implements UserDetails {

    private final RegisterDTO register;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = Collections.singleton(register.getRole());
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for (Role role : roles) {
            authorityList.add(new SimpleGrantedAuthority("ROLE " + role.name()));
        }
        return authorityList;
    }

    @Override
    public String getPassword() {
        return register.getPassword();
    }

    @Override
    public String getUsername() {
        return register.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
