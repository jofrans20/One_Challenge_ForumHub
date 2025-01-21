package com.forumhub.api.forumhubChallenge3.domain.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PedidoLoginDTO(


        @Email
        String email,
        @NotBlank
        String senha


) {
}
