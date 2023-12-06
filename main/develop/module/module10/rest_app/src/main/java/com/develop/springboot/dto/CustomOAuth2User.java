package com.develop.springboot.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CustomOAuth2User class is a DTO for OAuth2User.
 * It provides methods for getting attributes, authorities and name.
 * It is used for adding additional authorities to OAuth2User.
 *
 * @see com.develop.springboot.service.security.CustomOAuth2UserService
 */
public class CustomOAuth2User implements OAuth2User {

    private final OAuth2User oauth2User;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomOAuth2User(OAuth2User oauth2User, Collection<? extends GrantedAuthority> additionalAuthorities) {
        this.oauth2User = oauth2User;
        this.authorities = Stream.concat(oauth2User.getAuthorities().stream(), additionalAuthorities.stream())
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return oauth2User.getName();
    }
}
