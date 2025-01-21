package com.forumhub.api.forumhubChallenge3.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

//tabelas e entidades do banco de dados
@Entity(name = "Usuario")
@Table(name = "usuarios")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nome;

    String email;

    String senha;

    @Enumerated(EnumType.STRING)
    UsuarioStatus status;
}