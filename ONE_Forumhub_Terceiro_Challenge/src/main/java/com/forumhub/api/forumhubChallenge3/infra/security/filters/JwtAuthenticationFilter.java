package com.forumhub.api.forumhubChallenge3.infra.security.filters;

import com.forumhub.api.forumhubChallenge3.infra.security.CustomAuthenticationToken;
import com.forumhub.api.forumhubChallenge3.infra.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
//pega a senha das propriedades
    @Value("${api.security.token.secret}")
    private String SECRET_KEY;



    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;



    @Autowired
    public JwtAuthenticationFilter(TokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;


    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = tokenService.extractJwtFromRequest(request);



        if (token != null && tokenService.validateJwtToken(token)) {
            Long userId = tokenService.getUserIdFromJwtToken(token);
            String subject = tokenService.getSubjectFromJwtToken(token);

            if (subject != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(subject); // Load user details here if needed
                CustomAuthenticationToken authentication = new CustomAuthenticationToken(token, userId,
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }



        chain.doFilter(request, response); // Continue with the filter chain
    }


}