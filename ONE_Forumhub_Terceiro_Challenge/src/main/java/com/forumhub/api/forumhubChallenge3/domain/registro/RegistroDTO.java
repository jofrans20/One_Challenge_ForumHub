package com.forumhub.api.forumhubChallenge3.domain.registro;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record RegistroDTO(
        //registro do nome
        @Pattern(regexp = "\\S.*\\S", message = "O nome não pode começar ou terminar com espaços em branco.")
        @Range(min = 5, max = 200, message = "O nome deve possuir no minimo 5 caracteres e no maximo 200")
        String nome,

        //registro do email
        @Pattern(regexp = "\\S.*\\S", message = "O email não pode começar ou terminar com espaços em branco.")
        @Email
        @Size(max = 100, message = "O email só pode ter no maximo 100 caracteres")
        String email,

        //registrando a senha
        @Pattern(regexp = ".*[A-Z].*", message = "A senha deve possuir pelo menos 1 letra maiúscula")
        @Range(min = 8, max = 200, message = "A senha deve possuir entre 8 e 200 caracteres")
        String senha
) {
}
