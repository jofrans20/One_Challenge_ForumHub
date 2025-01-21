package com.forumhub.api.forumhubChallenge3.infra.security;

import org.springframework.security.core.context.SecurityContextHolder;



public interface SecurityContexHolderAccess {
    CustomAuthenticationToken authContext = (CustomAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    String authContextPrincipal = authContext.getName();
    String authContextToken = authContext.getToken();
    Long authContextUserId = authContext.getUserId();
}
