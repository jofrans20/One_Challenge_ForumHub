package com.forumhub.api.forumhubChallenge3.infra.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //    TODO: replace secret from JWT token gen
    @Value("${api.security.token.secret}")
    private String SECRET_KEY;
    private Algorithm algorithm;
    private final String ISSUER = "ForumHub API";


    @PostConstruct
    public void init() {
        this.algorithm = Algorithm.HMAC256(SECRET_KEY);
    }

    public String gerarToken(String email, Long idUsuario) {


        try {
            return JWT.create()
                    .withIssuer("ForumHub API")
                    .withSubject(email)
                    .withClaim("userId", idUsuario)
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }



    public Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubjectFromJwtToken(String token) {
        return JWT.require(algorithm).withIssuer(ISSUER).build().verify(token).getSubject();
    }


    public long getUserIdFromJwtToken(String token) {
        return JWT.require(algorithm).withIssuer(ISSUER).build().verify(token).getClaim("userId").asLong();
    }


    public String extractJwtFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }


    public boolean validateJwtToken(String token) {
        try {
            JWTVerifier verifier = JWT
                    .require(com.auth0.jwt.algorithms.Algorithm.HMAC256(SECRET_KEY))
                    .withIssuer(ISSUER)
                    .build();
            verifier.verify(token); // This will throw an exception if the token is invalid
            return true;
        } catch (JWTVerificationException e) {
            throw e;
        }
    }
}
