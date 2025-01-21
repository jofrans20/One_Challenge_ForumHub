package com.forumhub.api.forumhubChallenge3.infra.security;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Getter
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final String token;
    private final Long userId;



    public CustomAuthenticationToken(String token, Long userId, Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.token = token;
        this.userId = userId;
    }
}
