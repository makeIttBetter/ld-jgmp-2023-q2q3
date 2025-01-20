package com.example.auth.service;

import com.example.auth.dto.RoleDto;
import com.example.auth.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        log.info("Loading user by identifier: {}", identifier);
        UserDto userDto = userService.findByIdentifier(identifier);
        return new User(
                userDto.getUsername(),
                userDto.getPassword(),
                mapRolesToAuthorities(userDto.getRoles()) // Ensure roles are mapped
        );
    }

    /**
     * Maps roles to authorities.
     * It is used for mapping roles to authorities.
     *
     * @param roles the roles to map to authorities
     * @return the authorities mapped from roles
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleDto> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getConstantCode()))
                .collect(Collectors.toList());
    }
}
