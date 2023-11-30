package com.develop.springboot.service.security;

import com.develop.springboot.dto.CustomOAuth2User;
import com.develop.springboot.model.entities.Role;
import com.develop.springboot.model.entities.User;
import com.develop.springboot.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * CustomOAuth2UserService class is a service for customizing OAuth2 user service.
 * It provides methods for loading OAuth2 user and mapping roles to authorities.
 * <p> It extends DefaultOAuth2UserService class, which means it will be used for loading OAuth2 user.
 */
@Slf4j
@Service
@Lazy
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Loads OAuth2 user from the given OAuth2 user request.
     * It is used for loading OAuth2 user and mapping roles to authorities.
     * Authorities is combined from OAuth2 user and user from DataBase.
     *
     * @param userRequest the OAuth2 user request to load user from
     * @return the OAuth2 user with combined authorities
     * @throws OAuth2AuthenticationException in case of an OAuth2 authentication exception
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("Loading user for OAuth2 request");
        OAuth2User oauth2User = super.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        log.debug("Received email attribute from OAuth2User: {}", email);

        User user = userRepository.findByUsername(email);

        if (user == null) {
            log.info("User with email {} not found. Auto-registering.", email);
            String name = oauth2User.getAttribute("name");

            // Auto-register the user
            user = new User();
            user.setUsername(email);
            user.setPassword(passwordEncoder.encode("defaultPassword"));
            user.setRoles(Collections.singleton(new Role("ROLE_USER")));
            user.setEmail(email);
            user.setName(name);

            userRepository.save(user);
            log.info("User with email {} successfully registered.", email);
        } else {
            log.info("User with email {} already exists. No action taken.", email);
        }

        Collection<? extends GrantedAuthority> userAuthorities = mapRolesToAuthorities(user.getRoles());
        OAuth2User combinedUser = new CustomOAuth2User(oauth2User, userAuthorities);

        log.info("Returning OAuth2User with combined authorities for email: {}", email);
        return combinedUser;
    }

    /**
     * Maps roles to authorities.
     * It is used for mapping roles to authorities.
     *
     * @param roles the roles to map
     * @return the authorities mapped from roles
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getConstantCode()))
                .collect(Collectors.toList());
    }

}
