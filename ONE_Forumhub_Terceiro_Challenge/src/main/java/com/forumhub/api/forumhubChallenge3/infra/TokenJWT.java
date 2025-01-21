package com.forumhub.api.forumhubChallenge3.infra;

public record TokenJWT(
        String token
) {
    public TokenJWT(String token) {
        this.token = token;
    }
}
