package com.develop.springboot.service.security;

import com.develop.springboot.model.entities.Role;
import com.develop.springboot.model.entities.User;
import com.develop.springboot.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * UserDetailsServiceImpl class is a service for customizing user details service.
 * It provides methods for loading user by username and mapping roles to authorities.
 * <p> It implements UserDetailsService interface, which means it will be used for loading user by username.
 */
@Slf4j
@org.springframework.stereotype.Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads user by username.
     * It is used for loading user and mapping roles to authorities.
     * This method is used by Spring Security to load user from the DataBase
     * and check if the endpoint is accessible for the current User.
     *
     * @param username the username to load user by
     * @return the user details with mapped authorities
     * @throws UsernameNotFoundException in case of a username not found exception
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    /**
     * Maps roles to authorities.
     * It is used for mapping roles to authorities.
     *
     * @param roles the roles to map to authorities
     * @return the authorities mapped from roles
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getConstantCode()))
                .collect(Collectors.toList());
    }
}
